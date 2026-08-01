package com.lab.product_service.repository;

import com.lab.product_service.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {

    Product findProductById(String productId);
}