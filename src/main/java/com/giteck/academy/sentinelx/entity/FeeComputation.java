package com.giteck.academy.sentinelx.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "t_fee_computation")
@Data @AllArgsConstructor @NoArgsConstructor @Builder @ToString
public class FeeComputation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "inputAmount", nullable = false)
    private BigDecimal inputAmount;

    @Column(name = "feeAmount", nullable = false)
    private BigDecimal feeAmount;

    @Column(name = "algorithm")
    private String algorithm;

    @Column(name = "computedAt", nullable = false)
    private LocalDateTime computedAt;

    @Column(name = "computedBy")
    private String computedBy;

    @PrePersist
    public void prePersist() {
        if(computedAt == null) computedAt = LocalDateTime.now();
    }
}