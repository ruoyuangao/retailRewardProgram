package com.example.retailrewardprogram.repository;

import com.example.retailrewardprogram.pojo.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;

/**
 * Repository interface for accessing transaction data in the database.
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    /**
     * Retrieves a list of transactions for a specific customer.
     *
     * @param customerId the ID of the customer
     * @return a list of transactions for the customer
     */
    List<Transaction> findByCustomerId(int customerId);
    /**
     * Retrieves a list of transactions for a specific customer within a given date range.
     *
     * @param customerId the ID of the customer
     * @param startDate  the start date of the range (inclusive)
     * @param endDate    the end date of the range (inclusive)
     * @return a list of transactions for the customer within the specified date range
     */
    List<Transaction> findByCustomerIdAndCreatedDateBetween(int customerId, LocalDate startDate, LocalDate endDate);

}
