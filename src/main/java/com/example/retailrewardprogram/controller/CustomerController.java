package com.example.retailrewardprogram.controller;

import com.example.retailrewardprogram.pojo.Customer;
import com.example.retailrewardprogram.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController (CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/customers")
    public ResponseEntity<Void> createCustomer(@RequestBody Customer customer) {
        this.customerService.createCustomer(customer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<Void> updateCustomerById(@PathVariable int id, @RequestBody Customer customer) {
        this.customerService.updateCustomerById(id, customer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable int id) {
        return new ResponseEntity<>(this.customerService.getCustomerById(id), HttpStatus.OK);
    }

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return new ResponseEntity<>(this.customerService.getAllCustomers(), HttpStatus.OK);
    }
}
