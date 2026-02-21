package com.giteck.academy.sentinelx.mapping;

import com.giteck.academy.sentinelx.entity.Notification;
import com.giteck.academy.sentinelx.dto.NotificationDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {
    public NotificationDTO toDto(Notification entity) {
        if (entity == null) return null;
        NotificationDTO dto = new NotificationDTO();
        BeanUtils.copyProperties(entity, dto);
        if (entity.getTransfer() != null) {
            dto.setTransferId(entity.getTransfer().getId());
        }
        return dto;
    }
    public Notification toEntity(NotificationDTO dto) {
        if (dto == null) return null;
        Notification entity = new Notification();
        BeanUtils.copyProperties(dto, entity);
        // La gestion du Transfer se fera dans le ServiceImpl
        return entity;
    }
}