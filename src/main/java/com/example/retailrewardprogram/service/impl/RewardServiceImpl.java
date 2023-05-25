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

    @Override
    public double calculateRewardPoints(double amount) {
        double rewardPoints = 0;
        if (amount > 50) {
            rewardPoints += Math.min(amount, 100) - 50;
        }
        if (amount > 100) {
            rewardPoints += (amount - 100) * 2;
        }
        return rewardPoints;
    }


    @Override
    public RewardInfo getRewardInfo(int customerId) {
        RewardInfo rewardInfo = new RewardInfo();

        Optional<Customer> customer = this.customerRepository.findById(customerId);
        if (customer.isEmpty()) {
            throw new ResourceNotFoundException("Customer not found");
        }

        rewardInfo.setCustomer(customer.get());

        int monthDuration = 3;
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minus(monthDuration, ChronoUnit.MONTHS);

        List<Transaction> transactions = transactionRepository.findByCustomerIdAndCreatedDateBetween(customerId, startDate, endDate);

        DecimalFormat decimalFormat = new DecimalFormat("#.##");

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

        double totalReward = transactions.stream()
                .mapToDouble(transaction -> calculateRewardPoints(transaction.getAmount()))
                .sum();
        totalReward = Double.parseDouble(decimalFormat.format(totalReward));


        rewardInfo.setRewardByMonth(rewardByMonth);
        rewardInfo.setTotalReward(totalReward);

        return rewardInfo;
    }
}
