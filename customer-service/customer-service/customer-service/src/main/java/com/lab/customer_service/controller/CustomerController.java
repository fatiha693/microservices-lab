package com.lab.customer_service.controller;

import com.lab.customer_service.entity.Customer;
import com.lab.customer_service.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    public CustomerService customerService;

    @PostMapping("/")
    public Customer saveCustomer(@RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }

    @GetMapping("/{id}")
    public Customer findCustomerById(@PathVariable("id") String customerId) {
        return customerService.findCustomerById(customerId);
    }
}