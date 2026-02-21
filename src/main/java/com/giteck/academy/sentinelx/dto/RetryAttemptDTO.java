// src/main/java/com/giteck/academy/sentinelx/dto/RetryAttemptDTO.java
package com.giteck.academy.sentinelx.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class RetryAttemptDTO {
    private Long id;

    @NotNull(message = "L'exécution liée est obligatoire")
    private Long retryExecutionId;

    @Min(value = 1, message = "Le numéro de tentative doit être au moins 1")
    private int attemptNo;

    private Instant timestamp;

    @Min(value = 0, message = "La durée ne peut pas être négative")
    private long durationMs;

    private String exceptionClass;
    private String exceptionMessage;
    private boolean success;
}