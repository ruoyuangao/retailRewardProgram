package com.example.retailrewardprogram.service;

import com.example.retailrewardprogram.pojo.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing customer-related operations.
 */
@Service
public interface CustomerService {
    void createCustomer(Customer customer);

    void deleteCustomerById(int id);

    void updateCustomerById(int id, Customer customer);

    Customer getCustomerById(int id);

    List<Customer> getAllCustomers();
}
