package com.giteck.academy.sentinelx.entity;

import com.giteck.academy.sentinelx.nume.AuditStatus;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;

@Entity(name = "t_audit_event")
@Data @AllArgsConstructor @NoArgsConstructor @Builder @ToString
public class AuditEvent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "timestamp", nullable = false)
    private Instant timestamp;

    @Column(name = "action", nullable = false)
    private String action;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "methodSignature", nullable = false)
    private String methodSignature;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private AuditStatus status;

    @Column(name = "durationMs", nullable = false)
    private long durationMs;

    @Column(name = "argsLogged")
    private boolean argsLogged;

    @Column(name = "argsMasked")
    private boolean argsMasked;

    @Column(name = "argsSummary", length = 2000)
    private String argsSummary;

    @Column(name = "resultSummary", length = 1000)
    private String resultSummary;

    @Column(name = "exceptionClass")
    private String exceptionClass;

    @Column(name = "exceptionMessage", length = 1000)
    private String exceptionMessage;

    @Column(name = "traceId")
    private String traceId;

    @PrePersist
    public void prePersist() {
        if(timestamp == null) timestamp = Instant.now();
    }
}