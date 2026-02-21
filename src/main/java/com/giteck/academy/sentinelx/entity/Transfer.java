package com.giteck.academy.sentinelx.entity;

import com.giteck.academy.sentinelx.nume.Currency;
import com.giteck.academy.sentinelx.nume.TransferStatus;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "t_transfer")
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "reference")})
@Data @AllArgsConstructor @NoArgsConstructor @Builder @ToString
public class Transfer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reference", nullable = false, unique = true)
    private String reference;

    @ManyToOne
    @JoinColumn(name = "from_account_id", nullable = false)
    private Account fromAccount;

    @ManyToOne
    @JoinColumn(name = "to_account_id", nullable = false)
    private Account toAccount;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency", nullable = false)
    private Currency currency;

    @Column(name = "label")
    private String label;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TransferStatus status;

    @Column(name = "requestedAt", nullable = false)
    private LocalDateTime requestedAt;

    @Column(name = "executedAt")
    private LocalDateTime executedAt;

    @Column(name = "createdBy", nullable = false)
    private String createdBy; // Issu du header X-User simulé

    @Column(name = "failureCode")
    private String failureCode;

    @Column(name = "failureMessage")
    private String failureMessage;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        if(requestedAt == null) requestedAt = LocalDateTime.now();
        if(status == null) status = TransferStatus.PENDING;
    }
}