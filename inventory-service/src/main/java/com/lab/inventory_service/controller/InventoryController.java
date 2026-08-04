package com.lab.inventory_service.controller;

import com.lab.inventory_service.Constants;
import com.lab.inventory_service.entity.Product;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class InventoryController {

    private final Map<String, Product> inventory = new HashMap<>();

    public InventoryController() {
        inventory.put("P1", new Product("P1", "Wireless Mouse", 50));
        inventory.put("P2", new Product("P2", "Mechanical Keyboard", 20));
        inventory.put("P3", new Product("P3", "USB-C Hub", 5));
    }

    @RabbitListener(queues = Constants.QUEUE)
    public void consumeMessageFromQueue(Map<String, Object> orderEvent) {
        Object orderId = orderEvent.get("orderId");
        String productId = String.valueOf(orderEvent.get("productId"));
        int requestedQuantity = ((Number) orderEvent.get("quantity")).intValue();

        Product product = inventory.get(productId);

        if (product == null) {
            System.out.println("Order " + orderId + " could not be fulfilled: no product found with id " + productId);
            return;
        }

        if (requestedQuantity > product.getQuantity()) {
            System.out.println("Order " + orderId + " could not be fulfilled: requested " + requestedQuantity
                    + " of " + product.getName() + " but only " + product.getQuantity() + " in stock");
            return;
        }

        product.setQuantity(product.getQuantity() - requestedQuantity);
        System.out.println("Order " + orderId + " fulfilled: " + requestedQuantity + " x " + product.getName()
                + " reserved, " + product.getQuantity() + " remaining in stock");
    }
}
