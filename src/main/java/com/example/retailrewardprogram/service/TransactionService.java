package com.example.retailrewardprogram.service;

import com.example.retailrewardprogram.pojo.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service interface for managing transaction-related operations.
 */
@Service
public interface TransactionService {

    void createTransaction(Transaction transaction);

    List<Transaction> getTransactionByCustomerId(int id);
}
