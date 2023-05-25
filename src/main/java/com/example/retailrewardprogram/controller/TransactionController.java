package com.example.retailrewardprogram.controller;

import com.example.retailrewardprogram.pojo.Customer;
import com.example.retailrewardprogram.pojo.Transaction;
import com.example.retailrewardprogram.service.CustomerService;
import com.example.retailrewardprogram.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    public TransactionController (TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/transactions")
    public ResponseEntity<Void> createTransaction(@RequestBody Transaction transaction) {
        this.transactionService.createTransaction(transaction);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping("/transactions/{id}")
    public ResponseEntity<List<Transaction>> getTransactionByCustomerId(@PathVariable int id) {
        return new ResponseEntity<>(this.transactionService.getTransactionByCustomerId(id), HttpStatus.OK);
    }
}
