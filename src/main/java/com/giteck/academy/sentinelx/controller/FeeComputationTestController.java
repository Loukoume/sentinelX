// src/main/java/com/giteck/academy/sentinelx/controller/FeeComputationTestController.java
package com.giteck.academy.sentinelx.controller;

import com.giteck.academy.sentinelx.service.impl.FinalFeeEngine;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/fees")
@RequiredArgsConstructor
public class FeeComputationTestController {

    private final FinalFeeEngine feeEngine;

    @GetMapping("/test-retry")
    public String testRetry() {
        BigDecimal fee = feeEngine.computeFee(new BigDecimal("1000"));
        return "Frais calculés avec succès : " + fee;
    }
}