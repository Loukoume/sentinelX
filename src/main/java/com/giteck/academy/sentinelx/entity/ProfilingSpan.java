// src/main/java/com/giteck/academy/sentinelx/entity/ProfilingSpan.java
package com.giteck.academy.sentinelx.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;

@Entity(name = "t_profiling_span")
@Data @AllArgsConstructor @NoArgsConstructor @Builder @ToString
public class ProfilingSpan implements Serializable {

    @Id
    @Column(name = "spanId", nullable = false, unique = true)
    private String spanId;

    @Column(name = "parentSpanId")
    private String parentSpanId;

    @ManyToOne
    @JoinColumn(name = "trace_id", nullable = false)
    private ProfilingTrace trace;

    @Column(name = "operation", nullable = false)
    private String operation;

    @Column(name = "depth", nullable = false)
    private int depth;

    @Column(name = "startedAt", nullable = false)
    private Instant startedAt;

    @Column(name = "durationMs", nullable = false)
    private long durationMs;

    @Column(name = "orderIndex", nullable = false)
    private int orderIndex;

    @PrePersist
    public void prePersist() {
        if (startedAt == null) startedAt = Instant.now();
    }
}