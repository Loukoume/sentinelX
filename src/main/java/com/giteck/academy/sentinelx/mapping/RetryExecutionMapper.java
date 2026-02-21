package com.giteck.academy.sentinelx.mapping;

import com.giteck.academy.sentinelx.entity.RetryExecution;
import com.giteck.academy.sentinelx.dto.RetryExecutionDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class RetryExecutionMapper {
    public RetryExecutionDTO toDto(RetryExecution entity) {
        if (entity == null) return null;
        RetryExecutionDTO dto = new RetryExecutionDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }
    public RetryExecution toEntity(RetryExecutionDTO dto) {
        if (dto == null) return null;
        RetryExecution entity = new RetryExecution();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}