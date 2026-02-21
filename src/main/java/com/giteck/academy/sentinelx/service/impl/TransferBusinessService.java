// src/main/java/com/giteck/academy/sentinelx/service/impl/TransferBusinessService.java
package com.giteck.academy.sentinelx.service.impl;

import com.giteck.academy.sentinelx.dto.AccountDTO;
import com.giteck.academy.sentinelx.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransferBusinessService {

    private final AccountService accountService;

    // Cette méthode va déclencher une belle cascade d'appels (AccountService, puis AccountRepository)
    @Transactional
    public String executeTransfer(Long fromId, Long toId, BigDecimal amount) {

        // 1. Récupération des comptes (ça va déclencher accountService.findById -> accountRepository.findById)
        AccountDTO from = accountService.findById(fromId);
        AccountDTO to = accountService.findById(toId);

        // Simulation d'une petite latence métier (ex: vérification anti-fraude)
        simulateDelay(50);

        // 2. Mise à jour des soldes
        from.setBalance(from.getBalance().subtract(amount));
        to.setBalance(to.getBalance().add(amount));

        // 3. Sauvegarde (ça va déclencher accountService.update -> accountRepository.save)
        accountService.update(from);
        accountService.update(to);

        // 4. Notification
        sendNotification(from.getOwnerName());

        return "Virement de " + amount + " effectué avec succès !";
    }

    private void sendNotification(String owner) {
        simulateDelay(20);
    }

    private void simulateDelay(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) { }
    }
}