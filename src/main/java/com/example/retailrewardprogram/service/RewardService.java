package com.example.retailrewardprogram.service;

import com.example.retailrewardprogram.pojo.RewardInfo;
import com.example.retailrewardprogram.pojo.Transaction;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public interface RewardService {
    public double calculateRewardPoints(double amount);

    RewardInfo getRewardInfo(int customerId);
}
