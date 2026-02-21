package com.giteck.academy.sentinelx.dto;

import com.giteck.academy.sentinelx.nume.NotificationChannel;
import com.giteck.academy.sentinelx.nume.NotificationStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class NotificationDTO {
    private Long id;
    private Long transferId;

    @NotNull(message = "Le canal est obligatoire")
    private NotificationChannel channel;

    @NotBlank(message = "Le destinataire est obligatoire")
    private String recipient;

    @NotBlank(message = "Le message est obligatoire")
    private String message;

    private NotificationStatus status;
    private int attemptCount;
    private String lastError;
    private LocalDateTime createdAt;
    private LocalDateTime sentAt;
}