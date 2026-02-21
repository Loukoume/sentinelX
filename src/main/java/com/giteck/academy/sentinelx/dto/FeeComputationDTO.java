package com.giteck.academy.sentinelx.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class FeeComputationDTO {
    private Long id;

    @NotNull(message = "Le montant d'entrée est obligatoire")
    @PositiveOrZero(message = "Le montant ne peut pas être négatif")
    private BigDecimal inputAmount;

    @NotNull(message = "Le montant des frais est obligatoire")
    @PositiveOrZero(message = "Les frais ne peuvent pas être négatifs")
    private BigDecimal feeAmount;

    private String algorithm;
    private LocalDateTime computedAt;
    private String computedBy;
}