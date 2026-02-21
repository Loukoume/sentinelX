package com.giteck.academy.sentinelx.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;

@Entity(name = "t_retry_attempt")
@Data @AllArgsConstructor @NoArgsConstructor @Builder @ToString
public class RetryAttempt implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "retry_execution_id", nullable = false)
    private RetryExecution retryExecution;

    @Column(name = "attemptNo", nullable = false)
    private int attemptNo;

    @Column(name = "timestamp", nullable = false)
    private Instant timestamp;

    @Column(name = "durationMs", nullable = false)
    private long durationMs;

    @Column(name = "exceptionClass")
    private String exceptionClass;

    @Column(name = "exceptionMessage", length = 1000)
    private String exceptionMessage;

    @Column(name = "success", nullable = false)
    private boolean success;

    @PrePersist
    public void prePersist() {
        if (timestamp == null) timestamp = Instant.now();
    }
}