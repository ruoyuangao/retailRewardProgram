package com.example.retailrewardprogram.repository;

import com.example.retailrewardprogram.pojo.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    List<Transaction> findByCustomerId(int customerId);
    List<Transaction> findByCustomerIdAndCreatedDateBetween(int customerId, LocalDate startDate, LocalDate endDate);

}
