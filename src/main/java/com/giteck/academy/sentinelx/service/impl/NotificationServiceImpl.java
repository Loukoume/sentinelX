// NotificationServiceImpl.java
package com.giteck.academy.sentinelx.service.impl;

import com.giteck.academy.sentinelx.dto.NotificationDTO;
import com.giteck.academy.sentinelx.entity.Notification;
import com.giteck.academy.sentinelx.mapping.NotificationMapper;
import com.giteck.academy.sentinelx.repository.NotificationRepository;
import com.giteck.academy.sentinelx.repository.TransferRepository;
import com.giteck.academy.sentinelx.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository repository;
    private final NotificationMapper mapper;
    private final TransferRepository transferRepository;



    @Override
    public NotificationDTO save(NotificationDTO dto) {
        Notification entity = mapper.toEntity(dto);
        if (dto.getTransferId() != null) {
           // entity.setTransfer(transferRepository.findById(dto.getTransferId()).orElse(null));
        }
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public NotificationDTO update(NotificationDTO dto) {
        Notification entity = mapper.toEntity(dto);
        if (dto.getTransferId() != null) {
           // entity.setTransfer(transferRepository.findById(dto.getTransferId()).orElse(null));
        }
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public NotificationDTO findById(Long id) {
        return repository.findById(id).map(mapper::toDto).orElseThrow(() -> new RuntimeException("Notification introuvable"));
    }

    @Override
    public List<NotificationDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) { repository.deleteById(id); }
}