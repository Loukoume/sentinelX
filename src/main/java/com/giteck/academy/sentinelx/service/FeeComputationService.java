package com.giteck.academy.sentinelx.service;
import com.giteck.academy.sentinelx.dto.FeeComputationDTO;
import org.springframework.data.domain.Page;
import java.util.List;

public interface FeeComputationService {
    FeeComputationDTO save(FeeComputationDTO dto);
    FeeComputationDTO update(FeeComputationDTO dto);
    FeeComputationDTO findById(Long id);
    void deleteById(Long id);
    List<FeeComputationDTO> findAll();
}