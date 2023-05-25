package com.example.retailrewardprogram.controller;

import com.example.retailrewardprogram.pojo.Customer;
import com.example.retailrewardprogram.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * Creates a new customer.
     *
     * @param customer The customer to be created.
     * @return ResponseEntity representing the HTTP status of the operation.
     */
    @PostMapping("/customers")
    public ResponseEntity<Void> createCustomer(@RequestBody Customer customer) {
        this.customerService.createCustomer(customer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Updates an existing customer by ID.
     *
     * @param id       The ID of the customer to be updated.
     * @param customer The updated customer information.
     * @return ResponseEntity representing the HTTP status of the operation.
     */
    @PutMapping("/customers/{id}")
    public ResponseEntity<Void> updateCustomerById(@PathVariable int id, @RequestBody Customer customer) {
        this.customerService.updateCustomerById(id, customer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Retrieves a customer by ID.
     *
     * @param id The ID of the customer to be retrieved.
     * @return ResponseEntity containing the retrieved customer information.
     */
    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable int id) {
        return new ResponseEntity<>(this.customerService.getCustomerById(id), HttpStatus.OK);
    }

    /**
     * Retrieves all customers.
     *
     * @return ResponseEntity containing the list of all customers.
     */
    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return new ResponseEntity<>(this.customerService.getAllCustomers(), HttpStatus.OK);
    }
}
