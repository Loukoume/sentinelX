// src/main/java/com/giteck/academy/sentinelx/dto/AccountDTO.java
package com.giteck.academy.sentinelx.dto;

import com.giteck.academy.sentinelx.nume.AccountStatus;
import com.giteck.academy.sentinelx.nume.Currency;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class AccountDTO {
    private Long id;

    @NotBlank(message = "Le numéro de compte est obligatoire")
    private String accountNumber;

    @NotBlank(message = "Le nom du propriétaire est obligatoire")
    private String ownerName;

    @NotNull(message = "La devise est obligatoire")
    private Currency currency;

    @NotNull(message = "Le solde initial est obligatoire")
    @PositiveOrZero(message = "Le solde ne peut pas être négatif")
    private BigDecimal balance;

    private AccountStatus status;

    @PositiveOrZero(message = "La limite doit être positive")
    private BigDecimal dailyTransferLimit;

    private Long version;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}