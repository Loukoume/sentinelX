// src/main/java/com/giteck/academy/sentinelx/dto/ProfilingSpanDTO.java
package com.giteck.academy.sentinelx.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.time.Instant;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class ProfilingSpanDTO {
    @NotBlank(message = "L'identifiant du span est obligatoire")
    private String spanId;

    private String parentSpanId;

    @NotBlank(message = "L'identifiant de trace lié est obligatoire")
    private String traceId;

    @NotBlank(message = "L'opération est obligatoire")
    private String operation;

    @Min(value = 0, message = "La profondeur ne peut pas être négative")
    private int depth;

    private Instant startedAt;

    @Min(value = 0, message = "La durée ne peut pas être négative")
    private long durationMs;

    @Min(value = 0, message = "L'index d'ordre ne peut pas être négatif")
    private int orderIndex;
}