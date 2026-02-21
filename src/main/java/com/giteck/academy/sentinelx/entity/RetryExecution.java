// src/main/java/com/giteck/academy/sentinelx/entity/RetryExecution.java
package com.giteck.academy.sentinelx.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;

@Entity(name = "t_retry_execution")
@Data @AllArgsConstructor @NoArgsConstructor @Builder @ToString
public class RetryExecution implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "operation", nullable = false)
    private String operation;

    @Column(name = "maxAttempts", nullable = false)
    private int maxAttempts;

    @Column(name = "delayMs", nullable = false)
    private long delayMs;

    @Column(name = "retryOn")
    private String retryOn;

    @Column(name = "startedAt", nullable = false)
    private Instant startedAt;

    @Column(name = "endedAt")
    private Instant endedAt;

    @Column(name = "success")
    private boolean success;

    @Column(name = "finalExceptionClass")
    private String finalExceptionClass;

    @Column(name = "finalExceptionMessage", length = 1000)
    private String finalExceptionMessage;

    @Column(name = "traceId")
    private String traceId;

    @PrePersist
    public void prePersist() {
        if (startedAt == null) startedAt = Instant.now();
    }
}