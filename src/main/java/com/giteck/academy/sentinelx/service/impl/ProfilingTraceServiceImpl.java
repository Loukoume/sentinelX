// src/main/java/com/giteck/academy/sentinelx/service/impl/ProfilingTraceServiceImpl.java
package com.giteck.academy.sentinelx.service.impl;

import com.giteck.academy.sentinelx.dto.ProfilingTraceDTO;
import com.giteck.academy.sentinelx.entity.ProfilingTrace;
import com.giteck.academy.sentinelx.mapping.ProfilingTraceMapper;
import com.giteck.academy.sentinelx.repository.ProfilingTraceRepository;
import com.giteck.academy.sentinelx.service.ProfilingTraceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfilingTraceServiceImpl implements ProfilingTraceService {
    private final ProfilingTraceRepository repository;
    private final ProfilingTraceMapper mapper;


    @Override
    public ProfilingTraceDTO save(ProfilingTraceDTO dto) { return mapper.toDto(repository.save(mapper.toEntity(dto))); }
    @Override
    public ProfilingTraceDTO update(ProfilingTraceDTO dto) { return mapper.toDto(repository.save(mapper.toEntity(dto))); }
    @Override
    public ProfilingTraceDTO findById(String id) { return repository.findById(id).map(mapper::toDto).orElse(null); }
    @Override
    public List<ProfilingTraceDTO> findAll() { return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList()); }
    @Override
    public void deleteById(String id) { repository.deleteById(id); }
}