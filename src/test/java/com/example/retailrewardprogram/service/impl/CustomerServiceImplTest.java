package com.example.retailrewardprogram.service.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.example.retailrewardprogram.pojo.Customer;
import com.example.retailrewardprogram.repository.CustomerRepository;
import com.example.retailrewardprogram.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

/**
 * Unit tests for the CustomerServiceImpl class.
 */
class CustomerServiceImplTest {

    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerServiceImpl(customerRepository);
    }

    /**
     * Test case for creating a customer.
     */
    @Test
    void createCustomerTest() {
        Customer customer = new Customer();
        customerService.createCustomer(customer);
        // Verify that the save method of the repository is called once
        verify(customerRepository, times(1)).save(customer);
    }

    /**
     * Test case for deleting a customer by ID.
     */
    @Test
    void deleteCustomerByIdTest() {
        int customerId = 123;
        customerService.deleteCustomerById(customerId);

        // Verify that the deleteById method of the repository is called once with the specified ID
        verify(customerRepository, times(1)).deleteById(customerId);
    }

    /**
     * Test case for updating a customer by ID.
     */
    @Test
    void updateCustomerByIdTest() {
        int customerId = 123;
        Customer customer = new Customer();
        customerService.updateCustomerById(customerId, customer);

        // Verify that the save method of the repository is called once with the updated customer
        verify(customerRepository, times(1)).save(customer);
        assertEquals(customerId, customer.getCustomerId());
    }

    /**
     * Test case for retrieving a customer by ID.
     */
    @Test
    void getCustomerByIdTest() {
        // Create a sample customer
        int customerId = 123;
        Customer customer = new Customer();

        // Mock repository method
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        Customer result = customerService.getCustomerById(customerId);

        // Verify the repository method invocation
        verify(customerRepository, times(1)).findById(customerId);

        // Perform assertions
        assertNotNull(result);
        assertEquals(customer, result);
    }

    /**
     * Test case for retrieving a non-existing customer by ID.
     */
    @Test
    void getCustomerByIdNotFoundTest() {
        int customerId = 123;
        // Mock repository method to return null
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        // Invoke the method under test and assert that it throws the expected exception
        assertThrows(RuntimeException.class, () -> customerService.getCustomerById(customerId));

        // Verify the repository method invocation
        verify(customerRepository, times(1)).findById(customerId);
    }

    /**
     * Test case for retrieving all customers.
     */
    @Test
    void getAllCustomersTest() {
        // Create a sample list of customers
        List<Customer> customers = new ArrayList<>();
        Customer customer1 = new Customer();
        customers.add(customer1);
        Customer customer2 = new Customer();
        customers.add(customer2);

        // Mock repository method
        when(customerRepository.findAll()).thenReturn(customers);
        List<Customer> result = customerService.getAllCustomers();

        // Verify the repository method invocation
        verify(customerRepository, times(1)).findAll();

        // Perform assertions
        assertNotNull(result);
        assertEquals(customers, result);
    }

}