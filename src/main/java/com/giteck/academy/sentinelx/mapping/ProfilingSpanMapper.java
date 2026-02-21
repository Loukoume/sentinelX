package com.giteck.academy.sentinelx.mapping;

import com.giteck.academy.sentinelx.entity.ProfilingSpan;
import com.giteck.academy.sentinelx.dto.ProfilingSpanDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ProfilingSpanMapper {
    public ProfilingSpanDTO toDto(ProfilingSpan entity) {
        if (entity == null) return null;
        ProfilingSpanDTO dto = new ProfilingSpanDTO();
        BeanUtils.copyProperties(entity, dto);
        if (entity.getTrace() != null) {
            dto.setTraceId(entity.getTrace().getTraceId());
        }
        return dto;
    }
    public ProfilingSpan toEntity(ProfilingSpanDTO dto) {
        if (dto == null) return null;
        ProfilingSpan entity = new ProfilingSpan();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}