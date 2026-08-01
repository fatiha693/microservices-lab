package com.lab.customer_service.repository;

import com.lab.customer_service.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {

    Customer findCustomerById(String customerId);
}