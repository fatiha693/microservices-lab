package com.lab.order_service.repository;

import com.lab.order_service.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {

    Order findOrderById(String orderId);
}