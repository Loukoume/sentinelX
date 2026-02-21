// src/main/java/com/giteck/academy/sentinelx/service/impl/FinalFeeEngine.java
package com.giteck.academy.sentinelx.service.impl;

import com.giteck.academy.sentinelx.aspect.retry.RetryOnFailure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

// CLASSE FINAL : Impossible à proxifier par Spring (CGLIB)
@Component
public final class FinalFeeEngine {

    private static final Logger log = LoggerFactory.getLogger(FinalFeeEngine.class);
    private int attemptCounter = 0;

    @RetryOnFailure(maxAttempts = 3, delayMs = 500)
    public BigDecimal computeFee(BigDecimal amount) {
        attemptCounter++;
        log.info("Tentative {} de calcul des frais pour le montant {}", attemptCounter, amount);

        if (attemptCounter < 3) {
            log.error("Erreur réseau simulée lors du calcul !");
            throw new RuntimeException("Timeout connexion API de tarification");
        }

        log.info("Calcul réussi à la tentative {}", attemptCounter);
        attemptCounter = 0; // Reset pour le prochain test
        return amount.multiply(new BigDecimal("0.02")); // 2% de frais
    }
}