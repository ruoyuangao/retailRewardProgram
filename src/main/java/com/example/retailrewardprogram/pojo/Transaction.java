package com.example.retailrewardprogram.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


import javax.persistence.*;

@Entity
@Table(name="transactions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionId;

    @Column(name="customer_id")
    private int customerId;

    @Column(name = "amount")
    private double amount;

    @Column(name = "created_date")
    private LocalDate createdDate;
}
