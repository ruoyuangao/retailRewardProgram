package com.example.retailrewardprogram.repository;

import com.example.retailrewardprogram.pojo.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing customer data in the database.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
