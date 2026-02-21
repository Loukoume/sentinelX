package com.giteck.academy.sentinelx.service;
import com.giteck.academy.sentinelx.dto.NotificationDTO;

import org.springframework.data.domain.Page;
import java.util.List;

public interface NotificationService {
    NotificationDTO save(NotificationDTO dto);
    NotificationDTO update(NotificationDTO dto);
    NotificationDTO findById(Long id);
    void deleteById(Long id);
    List<NotificationDTO> findAll();
}