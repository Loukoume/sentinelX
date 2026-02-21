// src/main/java/com/giteck/academy/sentinelx/dto/TransferDTO.java
package com.giteck.academy.sentinelx.dto;

import com.giteck.academy.sentinelx.nume.Currency;
import com.giteck.academy.sentinelx.nume.TransferStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class TransferDTO {
    private Long id;
    private String reference;

    @NotNull(message = "Le compte source est obligatoire")
    private Long fromAccountId;

    @NotNull(message = "Le compte destinataire est obligatoire")
    private Long toAccountId;

    @NotNull(message = "Le montant est obligatoire")
    @Positive(message = "Le montant transféré doit être strictement positif")
    private BigDecimal amount;

    @NotNull(message = "La devise est obligatoire")
    private Currency currency;

    private String label;
    private TransferStatus status;
    private LocalDateTime requestedAt;
    private LocalDateTime executedAt;

    @NotBlank(message = "L'utilisateur créateur est obligatoire")
    private String createdBy;

    private String failureCode;
    private String failureMessage;
    private LocalDateTime createdAt;
}