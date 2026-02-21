package com.giteck.academy.sentinelx.service;

import com.giteck.academy.sentinelx.dto.RetryExecutionDTO;
import java.util.List;

public interface RetryExecutionService {
    RetryExecutionDTO save(RetryExecutionDTO dto);
    RetryExecutionDTO update(RetryExecutionDTO dto);
    RetryExecutionDTO findById(Long id);
    void deleteById(Long id);
    List<RetryExecutionDTO> findAll();
}