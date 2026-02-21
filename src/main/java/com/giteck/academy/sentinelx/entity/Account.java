// src/main/java/com/giteck/academy/sentinelx/entity/Account.java
package com.giteck.academy.sentinelx.entity;

import com.giteck.academy.sentinelx.nume.AccountStatus;
import com.giteck.academy.sentinelx.nume.Currency;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "t_account")
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "accountNumber")})
@Data @AllArgsConstructor @NoArgsConstructor @Builder @ToString
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "accountNumber", nullable = false, unique = true)
    private String accountNumber;

    @Column(name = "ownerName", nullable = false)
    private String ownerName;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency", nullable = false)
    private Currency currency;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private AccountStatus status;

    @Column(name = "dailyTransferLimit")
    private BigDecimal dailyTransferLimit;

    @Version // Verrouillage optimiste exigé par le cahier des charges
    private Long version;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if(balance == null) balance = BigDecimal.ZERO;
        if(status == null) status = AccountStatus.ACTIVE;
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}