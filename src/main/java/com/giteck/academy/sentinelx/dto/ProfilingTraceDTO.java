package com.giteck.academy.sentinelx.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.time.Instant;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class ProfilingTraceDTO {
    @NotBlank(message = "L'identifiant de trace est obligatoire")
    private String traceId;

    private Instant startedAt;

    @NotBlank(message = "L'opération racine est obligatoire")
    private String rootOperation;

    @Min(value = 0, message = "La durée ne peut pas être négative")
    private long totalDurationMs;

    private String threadName;
}