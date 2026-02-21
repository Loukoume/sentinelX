// src/main/java/com/giteck/academy/sentinelx/service/impl/RetryAttemptServiceImpl.java
package com.giteck.academy.sentinelx.service.impl;

import com.giteck.academy.sentinelx.dto.RetryAttemptDTO;
import com.giteck.academy.sentinelx.entity.RetryAttempt;
import com.giteck.academy.sentinelx.mapping.RetryAttemptMapper;
import com.giteck.academy.sentinelx.repository.RetryAttemptRepository;
import com.giteck.academy.sentinelx.repository.RetryExecutionRepository;
import com.giteck.academy.sentinelx.service.RetryAttemptService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RetryAttemptServiceImpl implements RetryAttemptService {
    private final RetryAttemptRepository repository;
    private final RetryAttemptMapper mapper;
    private final RetryExecutionRepository executionRepository;


    @Override
    public RetryAttemptDTO save(RetryAttemptDTO dto) {
        RetryAttempt entity = mapper.toEntity(dto);
        if (dto.getRetryExecutionId() != null) {
            entity.setRetryExecution(executionRepository.findById(dto.getRetryExecutionId()).orElse(null));
        }
        return mapper.toDto(repository.save(entity));
    }
    @Override
    public RetryAttemptDTO update(RetryAttemptDTO dto) {
        RetryAttempt entity = mapper.toEntity(dto);
        if (dto.getRetryExecutionId() != null) {
            entity.setRetryExecution(executionRepository.findById(dto.getRetryExecutionId()).orElse(null));
        }
        return mapper.toDto(repository.save(entity));
    }
    @Override
    public RetryAttemptDTO findById(Long id) { return repository.findById(id).map(mapper::toDto).orElse(null); }
    @Override
    public List<RetryAttemptDTO> findAll() { return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList()); }
    @Override
    public void deleteById(Long id) { repository.deleteById(id); }
}