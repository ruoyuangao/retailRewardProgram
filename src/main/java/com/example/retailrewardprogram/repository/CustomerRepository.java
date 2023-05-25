package com.example.retailrewardprogram.repository;

import com.example.retailrewardprogram.pojo.Customer;
import com.example.retailrewardprogram.pojo.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findById(int customerId);

}
