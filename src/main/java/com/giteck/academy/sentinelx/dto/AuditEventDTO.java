package com.giteck.academy.sentinelx.dto;

import com.giteck.academy.sentinelx.nume.AuditStatus;
import lombok.*;

import java.time.Instant;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class AuditEventDTO {
    private Long id;
    private Instant timestamp;
    private String action;
    private String username;
    private String methodSignature;
    private AuditStatus status;
    private long durationMs;
    private boolean argsLogged;
    private boolean argsMasked;
    private String argsSummary;
    private String resultSummary;
    private String exceptionClass;
    private String exceptionMessage;
    private String traceId;
}