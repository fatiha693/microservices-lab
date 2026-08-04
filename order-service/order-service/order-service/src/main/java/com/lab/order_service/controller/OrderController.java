package com.lab.order_service.controller;

import com.lab.order_service.Constants;
import com.lab.order_service.entity.Order;
import com.lab.order_service.service.OrderService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    public OrderService orderService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/")
    public Order saveOrder(@RequestBody Order order) {
        Order savedOrder = orderService.saveOrder(order);

        Map<String, Object> orderEvent = new HashMap<>();
        orderEvent.put("orderId", savedOrder.getId());
        orderEvent.put("productId", savedOrder.getProductId());
        orderEvent.put("quantity", savedOrder.getQuantity());

        rabbitTemplate.convertAndSend(Constants.EXCHANGE, Constants.ROUTING_KEY, orderEvent);

        return savedOrder;
    }

    @GetMapping("/{id}")
    public Order findOrderById(@PathVariable("id") String orderId) {
        return orderService.findOrderById(orderId);
    }
}