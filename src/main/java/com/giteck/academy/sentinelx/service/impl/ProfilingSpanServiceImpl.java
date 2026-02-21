// src/main/java/com/giteck/academy/sentinelx/service/impl/ProfilingSpanServiceImpl.java
package com.giteck.academy.sentinelx.service.impl;

import com.giteck.academy.sentinelx.dto.ProfilingSpanDTO;
import com.giteck.academy.sentinelx.entity.ProfilingSpan;
import com.giteck.academy.sentinelx.mapping.ProfilingSpanMapper;
import com.giteck.academy.sentinelx.repository.ProfilingSpanRepository;
import com.giteck.academy.sentinelx.repository.ProfilingTraceRepository;
import com.giteck.academy.sentinelx.service.ProfilingSpanService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfilingSpanServiceImpl implements ProfilingSpanService {
    private final ProfilingSpanRepository repository;
    private final ProfilingSpanMapper mapper;
    private final ProfilingTraceRepository traceRepository;


    @Override
    public ProfilingSpanDTO save(ProfilingSpanDTO dto) {
        ProfilingSpan entity = mapper.toEntity(dto);
        if (dto.getTraceId() != null) {
            entity.setTrace(traceRepository.findById(dto.getTraceId()).orElse(null));
        }
        return mapper.toDto(repository.save(entity));
    }
    @Override
    public ProfilingSpanDTO update(ProfilingSpanDTO dto) {
        ProfilingSpan entity = mapper.toEntity(dto);
        if (dto.getTraceId() != null) {
            entity.setTrace(traceRepository.findById(dto.getTraceId()).orElse(null));
        }
        return mapper.toDto(repository.save(entity));
    }
    @Override
    public ProfilingSpanDTO findById(String id) { return repository.findById(id).map(mapper::toDto).orElse(null); }
    @Override
    public List<ProfilingSpanDTO> findAll() { return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList()); }
    @Override
    public void deleteById(String id) { repository.deleteById(id); }
}