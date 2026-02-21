package com.giteck.academy.sentinelx.mapping;

import com.giteck.academy.sentinelx.entity.ProfilingTrace;
import com.giteck.academy.sentinelx.dto.ProfilingTraceDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ProfilingTraceMapper {
    public ProfilingTraceDTO toDto(ProfilingTrace entity) {
        if (entity == null) return null;
        ProfilingTraceDTO dto = new ProfilingTraceDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }
    public ProfilingTrace toEntity(ProfilingTraceDTO dto) {
        if (dto == null) return null;
        ProfilingTrace entity = new ProfilingTrace();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}