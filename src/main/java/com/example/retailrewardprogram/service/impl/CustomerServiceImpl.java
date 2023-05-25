package com.example.retailrewardprogram.service.impl;

import com.example.retailrewardprogram.exception.ResourceNotFoundException;
import com.example.retailrewardprogram.pojo.Customer;
import com.example.retailrewardprogram.repository.CustomerRepository;
import com.example.retailrewardprogram.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl (CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * Creates a new customer.
     *
     * @param customer the customer to create
     */
    @Override
    @Transactional
    public void createCustomer(Customer customer) {
        this.customerRepository.save(customer);
    }

    /**
     * Deletes a customer by ID.
     *
     * @param id the ID of the customer to delete
     */
    @Override
    @Transactional
    public void deleteCustomerById(int id) {
        this.customerRepository.deleteById(id);
    }

    /**
     * Updates a customer by ID with the provided customer data.
     *
     * @param id       the ID of the customer to update
     * @param customer the updated customer data
     */
    @Override
    @Transactional
    public void updateCustomerById(int id, Customer customer) {
        customer.setCustomerId(id);
        this.customerRepository.save(customer);
    }

    /**
     * Retrieves a customer by ID.
     *
     * @param id the ID of the customer to retrieve
     * @return the customer with the specified ID
     * @throws ResourceNotFoundException if the customer is not found
     */
    @Override
    public Customer getCustomerById(int id) {
        Optional<Customer> customer = this.customerRepository.findById(id);
        if (customer.isEmpty()) {
            throw new ResourceNotFoundException("Customer not found");
        }
        return customer.get();
    }
    /**
     * Retrieves a list of all customers.
     *
     * @return a list of all customers
     * @throws ResourceNotFoundException if the customer list is not found
     */
    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> list = this.customerRepository.findAll();
        if (list == null) {
            throw new ResourceNotFoundException("Customer list not found");
        }
        return list;
    }
}
