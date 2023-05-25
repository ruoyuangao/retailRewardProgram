package com.example.retailrewardprogram.service.impl;

import com.example.retailrewardprogram.exception.ResourceNotFoundException;
import com.example.retailrewardprogram.pojo.Customer;
import com.example.retailrewardprogram.pojo.Transaction;
import com.example.retailrewardprogram.repository.CustomerRepository;
import com.example.retailrewardprogram.repository.TransactionRepository;
import com.example.retailrewardprogram.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImpl (TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void createTransaction(Transaction transaction) {
        this.transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getTransactionByCustomerId(int id) {
        List<Transaction> list = this.transactionRepository.findByCustomerId(id);
        if (list == null) {
            throw new ResourceNotFoundException("Transaction list not found");
        }
        return list;
    }
}
