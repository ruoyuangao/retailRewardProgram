package com.example.retailrewardprogram.service.impl;

import com.example.retailrewardprogram.exception.ResourceNotFoundException;
import com.example.retailrewardprogram.pojo.Customer;
import com.example.retailrewardprogram.pojo.RewardInfo;
import com.example.retailrewardprogram.pojo.Transaction;
import com.example.retailrewardprogram.repository.CustomerRepository;
import com.example.retailrewardprogram.repository.TransactionRepository;
import com.example.retailrewardprogram.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class RewardServiceImpl implements RewardService {
    private final TransactionRepository transactionRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public RewardServiceImpl (TransactionRepository transactionRepository, CustomerRepository customerRepository) {
        this.transactionRepository = transactionRepository;
        this.customerRepository = customerRepository;
    }

    /**
     * Calculates the reward points based on the transaction amount.
     *
     * @param amount the transaction amount
     * @return the calculated reward points
     */
    @Override
    public double calculateRewardPoints(double amount) {
        double rewardPoints = 0;
        //receives 1 points for every dollar spent over $50 in each transaction
        if (amount > 50) {
            rewardPoints += Math.min(amount, 100) - 50;
        }
        //receives 2 points for every dollar spent over $100 in each transaction
        if (amount > 100) {
            rewardPoints += (amount - 100) * 2;
        }
        //(e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points).
        return rewardPoints;
    }

    /**
     * Retrieves the reward information for a customer.
     *
     * @param customerId the ID of the customer
     * @return the reward information for the customer
     * @throws ResourceNotFoundException if the customer is not found
     */
    @Override
    public RewardInfo getRewardInfo(int customerId) {
        // use rewardInfo to store the latest 3 month reward information for a specific customer
        RewardInfo rewardInfo = new RewardInfo();

        // first see if we could find the customer
        Optional<Customer> customer = this.customerRepository.findById(customerId);
        if (customer.isEmpty()) {
            throw new ResourceNotFoundException("Customer not found");
        }
        rewardInfo.setCustomer(customer.get());

        // Assumption: three month is on a rolling basis -> allows for a continuous and ongoing accumulation of rewards.
        // Example: if today is 05-25-2023, then the latest three month means: 02-26-2023 - 05-25-2023. It is not based on the nature month.
        // TODO: In the real scenario, double check the request.
        int monthDuration = 3;
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minus(monthDuration, ChronoUnit.MONTHS);

        // get the list of transaction satisfy the customerId and time range
        List<Transaction> transactions = transactionRepository.findByCustomerIdAndCreatedDateBetween(customerId, startDate, endDate);

        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        // Calculate reward points by month with stream api
        Map<String, Double> rewardByMonth = transactions.stream()
                .collect(Collectors.groupingBy(
                        transaction -> transaction.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM")),
                        Collectors.summingDouble(transaction -> calculateRewardPoints(transaction.getAmount()))
                ))
                .entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> Double.parseDouble(decimalFormat.format(entry.getValue()))
                ));

        // Calculate total reward points with stream api
        double totalReward = transactions.stream()
                .mapToDouble(transaction -> calculateRewardPoints(transaction.getAmount()))
                .sum();
        totalReward = Double.parseDouble(decimalFormat.format(totalReward));


        rewardInfo.setRewardByMonth(rewardByMonth);
        rewardInfo.setTotalReward(totalReward);

        return rewardInfo;
    }
}
