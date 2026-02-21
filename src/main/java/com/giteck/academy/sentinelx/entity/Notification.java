// src/main/java/com/giteck/academy/sentinelx/entity/Notification.java
package com.giteck.academy.sentinelx.entity;

import com.giteck.academy.sentinelx.nume.NotificationChannel;
import com.giteck.academy.sentinelx.nume.NotificationStatus;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity(name = "t_notification")
@Data @AllArgsConstructor @NoArgsConstructor @Builder @ToString
public class Notification implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "transfer_id")
    private Transfer transfer;

    @Enumerated(EnumType.STRING)
    @Column(name = "channel", nullable = false)
    private NotificationChannel channel;

    @Column(name = "recipient", nullable = false)
    private String recipient;

    @Column(name = "message", nullable = false, length = 1000)
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private NotificationStatus status;

    @Column(name = "attemptCount", nullable = false)
    private int attemptCount;

    @Column(name = "lastError")
    private String lastError;

    @Column(name = "createdAt", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "sentAt")
    private LocalDateTime sentAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        if(status == null) status = NotificationStatus.PENDING;
        if(attemptCount == 0) attemptCount = 0;
    }
}