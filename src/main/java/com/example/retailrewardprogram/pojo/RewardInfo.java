package com.example.retailrewardprogram.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RewardInfo {

    private Customer customer;

    private Map<String, Double> rewardByMonth;

    private double totalReward;

}
