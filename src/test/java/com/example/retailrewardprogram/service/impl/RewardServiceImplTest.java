package com.example.retailrewardprogram.service.impl;


import com.example.retailrewardprogram.pojo.Customer;
import com.example.retailrewardprogram.pojo.RewardInfo;
import com.example.retailrewardprogram.pojo.Transaction;
import com.example.retailrewardprogram.repository.CustomerRepository;
import com.example.retailrewardprogram.repository.TransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class RewardServiceImplTest {
    private RewardServiceImpl rewardService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        rewardService = new RewardServiceImpl(transactionRepository, customerRepository);
    }

    /**
     * Test if the calculation method satisfy the request
     */
    @Test
    public void calculateRewardPointsTest() {
        double amount = 120;
        double expectedRewardPoints = 90; // (2 * 20) + (1 * 50)
        double actualRewardPoints = rewardService.calculateRewardPoints(amount);
        assertEquals(expectedRewardPoints, actualRewardPoints, 0.01); // Specify a delta for double comparison
    }

    /**
     * Test if the rewrad info is retrived properly
     */
    @Test
    void getRewardInfoTest() {
        // Create a sample customer
        Customer customer = new Customer();
        customer.setCustomerId(1);
        customer.setCustomerName("Kelly");

        // Create sample transactions
        List<Transaction> transactions = new ArrayList<>();
        Transaction transaction1 = new Transaction();
        transaction1.setCustomerId(1);
        transaction1.setAmount(120.0);
        transaction1.setCreatedDate(LocalDate.now().minus(1, ChronoUnit.MONTHS));
        transactions.add(transaction1);

        Transaction transaction2 = new Transaction();
        transaction2.setCustomerId(1);
        transaction2.setAmount(80.0);
        transaction2.setCreatedDate(LocalDate.now());
        transactions.add(transaction2);

        // Mock repository methods
        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
        when(transactionRepository.findByCustomerIdAndCreatedDateBetween(eq(1), any(LocalDate.class), any(LocalDate.class)))
                .thenReturn(transactions);

        // Invoke the method under test
        RewardInfo rewardInfo = rewardService.getRewardInfo(1);

        // Perform assertions
        Assertions.assertEquals(customer, rewardInfo.getCustomer());
        Assertions.assertEquals(2, rewardInfo.getRewardByMonth().size());
        Assertions.assertEquals(Optional.of(90.0), Optional.of(rewardInfo.getRewardByMonth().get(transaction1.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM")))));
        Assertions.assertEquals(Optional.of(30.0), Optional.of(rewardInfo.getRewardByMonth().get(transaction2.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM")))));
        Assertions.assertEquals(120.0, rewardInfo.getTotalReward());


        // Verify the repository method invocations
        verify(customerRepository, times(1)).findById(1);
        verify(transactionRepository, times(1)).findByCustomerIdAndCreatedDateBetween(eq(1), any(LocalDate.class), any(LocalDate.class));
        verifyNoMoreInteractions(customerRepository, transactionRepository);
    }

}