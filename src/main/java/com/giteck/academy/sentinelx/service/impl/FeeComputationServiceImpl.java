// FeeComputationServiceImpl.java
package com.giteck.academy.sentinelx.service.impl;

import com.giteck.academy.sentinelx.dto.FeeComputationDTO;
import com.giteck.academy.sentinelx.mapping.FeeComputationMapper;
import com.giteck.academy.sentinelx.repository.FeeComputationRepository;
import com.giteck.academy.sentinelx.service.FeeComputationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeeComputationServiceImpl implements FeeComputationService {
    private final FeeComputationRepository repository;
    private final FeeComputationMapper mapper;


    @Override
    public FeeComputationDTO save(FeeComputationDTO dto) { return mapper.toDto(repository.save(mapper.toEntity(dto))); }
    @Override
    public FeeComputationDTO update(FeeComputationDTO dto) { return mapper.toDto(repository.save(mapper.toEntity(dto))); }
    @Override
    public FeeComputationDTO findById(Long id) { return repository.findById(id).map(mapper::toDto).orElse(null); }
    @Override
    public List<FeeComputationDTO> findAll() { return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList()); }
    @Override
    public void deleteById(Long id) { repository.deleteById(id); }
}