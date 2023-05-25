package com.example.retailrewardprogram.service.impl;

import com.example.retailrewardprogram.pojo.Transaction;
import com.example.retailrewardprogram.repository.TransactionRepository;
import com.example.retailrewardprogram.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.*;

class TransactionServiceImplTest {
    private TransactionService transactionService;

    @Mock
    private TransactionRepository transactionRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        transactionService = new TransactionServiceImpl(transactionRepository);
    }

    /**
     * Test case for creating a sample transaction
     */
    @Test
    void createTransactionTest() {
        // Create a sample transaction
        Transaction transaction = new Transaction();
        transaction.setAmount(100.0);
        transaction.setCustomerId(1);

        // Invoke the method under test
        transactionService.createTransaction(transaction);

        // Verify that the save method of the repository was called once with the correct transaction object
        verify(transactionRepository, times(1)).save(transaction);
        verifyNoMoreInteractions(transactionRepository);
    }

    /**
     * Test case for retrieving trasaction list from a normal customer by ID.
     */
    @Test
    void getTransactionByCustomerIdTest() {
        int customerId = 123;

        // Create a sample list of transactions
        List<Transaction> transactions = new ArrayList<>();
        Transaction transaction1 = new Transaction();
        transactions.add(transaction1);

        // Mock repository method
        when(transactionRepository.findByCustomerId(customerId)).thenReturn(transactions);
        List<Transaction> result = transactionService.getTransactionByCustomerId(customerId);

        // Verify the repository method invocation
        verify(transactionRepository, times(1)).findByCustomerId(customerId);

        // Perform assertions
        assertNotNull(result);
        assertEquals(transactions, result);
    }

    /**
     * Test case for retrieving trasaction list from a non-existing customer by ID.
     */
    @Test
    void getTransactionByCustomerIdNotFoundTest() {
        int customerId = 123;
        // Mock repository method to return null
        when(transactionRepository.findByCustomerId(customerId)).thenReturn(null);
        // Invoke the method under test and assert that it throws the expected exception
        assertThrows(RuntimeException.class, () -> transactionService.getTransactionByCustomerId(customerId));
        // Verify the repository method invocation
        verify(transactionRepository, times(1)).findByCustomerId(customerId);
    }
}