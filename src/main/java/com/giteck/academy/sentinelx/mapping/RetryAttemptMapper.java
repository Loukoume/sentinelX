package com.giteck.academy.sentinelx.mapping;

import com.giteck.academy.sentinelx.entity.RetryAttempt;
import com.giteck.academy.sentinelx.dto.RetryAttemptDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class RetryAttemptMapper {
    public RetryAttemptDTO toDto(RetryAttempt entity) {
        if (entity == null) return null;
        RetryAttemptDTO dto = new RetryAttemptDTO();
        BeanUtils.copyProperties(entity, dto);
        if (entity.getRetryExecution() != null) {
            dto.setRetryExecutionId(entity.getRetryExecution().getId());
        }
        return dto;
    }
    public RetryAttempt toEntity(RetryAttemptDTO dto) {
        if (dto == null) return null;
        RetryAttempt entity = new RetryAttempt();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}