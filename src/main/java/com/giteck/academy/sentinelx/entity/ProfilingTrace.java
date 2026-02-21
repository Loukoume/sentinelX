package com.giteck.academy.sentinelx.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;

@Entity(name = "t_profiling_trace")
@Data @AllArgsConstructor @NoArgsConstructor @Builder @ToString
public class ProfilingTrace implements Serializable {

    @Id
    @Column(name = "traceId", nullable = false, unique = true)
    private String traceId;

    @Column(name = "startedAt", nullable = false)
    private Instant startedAt;

    @Column(name = "rootOperation", nullable = false)
    private String rootOperation;

    @Column(name = "totalDurationMs", nullable = false)
    private long totalDurationMs;

    @Column(name = "threadName")
    private String threadName;

    @PrePersist
    public void prePersist() {
        if (startedAt == null) startedAt = Instant.now();
    }
}