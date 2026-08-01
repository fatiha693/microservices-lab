package com.lab.product_service.service;

import com.lab.product_service.entity.Product;
import com.lab.product_service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Product findProductById(String productId) {
        return productRepository.findProductById(productId);
    }
}