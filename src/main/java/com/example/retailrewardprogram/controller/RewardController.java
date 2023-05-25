package com.example.retailrewardprogram.controller;

import com.example.retailrewardprogram.pojo.RewardInfo;
import com.example.retailrewardprogram.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RewardController {

    private final RewardService rewardService;

    @Autowired
    public RewardController ( RewardService rewardService) {
        this.rewardService = rewardService;
    }

    /**
     * Retrieves the reward information for a specific customer by ID.
     *
     * @param customerId The ID of the customer to retrieve the reward information for.
     * @return ResponseEntity containing the reward information.
     */
    @GetMapping("/rewards/{customerId}")
    public ResponseEntity<RewardInfo> getRewardInfoByCustomerId(@PathVariable int customerId) {
        RewardInfo rewardInfo = rewardService.getRewardInfo(customerId);

        return ResponseEntity.ok(rewardInfo);
    }
}
