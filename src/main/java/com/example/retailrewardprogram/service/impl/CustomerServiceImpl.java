package com.example.retailrewardprogram.service.impl;

import com.example.retailrewardprogram.exception.ResourceNotFoundException;
import com.example.retailrewardprogram.pojo.Customer;
import com.example.retailrewardprogram.repository.CustomerRepository;
import com.example.retailrewardprogram.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl (CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void createCustomer(Customer customer) {
        this.customerRepository.save(customer);
    }
    @Override
    public void deleteCustomerById(int id) {
        this.customerRepository.deleteById(id);
    }

    @Override
    public void updateCustomerById(int id, Customer customer) {
        customer.setCustomerId(id);
        this.customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerById(int id) {
        Optional<Customer> customer = this.customerRepository.findById(id);
        if (customer.isEmpty()) {
            throw new ResourceNotFoundException("Customer not found");
        }
        return customer.get();
    }
    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> list = this.customerRepository.findAll();
        if (list == null) {
            throw new ResourceNotFoundException("Customer list not found");
        }
        return list;
    }
}
