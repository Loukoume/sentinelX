// src/main/java/com/giteck/academy/sentinelx/dto/RetryExecutionDTO.java
package com.giteck.academy.sentinelx.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.Instant;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class RetryExecutionDTO {
    private Long id;

    @NotBlank(message = "L'opération est obligatoire")
    private String operation;

    @Min(value = 1, message = "Le nombre maximal de tentatives doit être au moins 1")
    private int maxAttempts;

    @Min(value = 0, message = "Le délai ne peut pas être négatif")
    private long delayMs;

    private String retryOn;
    private Instant startedAt;
    private Instant endedAt;
    private boolean success;
    private String finalExceptionClass;
    private String finalExceptionMessage;
    private String traceId;
}