// src/main/java/com/giteck/academy/sentinelx/service/RetryAttemptService.java
package com.giteck.academy.sentinelx.service;

import com.giteck.academy.sentinelx.dto.RetryAttemptDTO;
import org.springframework.data.domain.Page;
import java.util.List;

public interface RetryAttemptService {
    RetryAttemptDTO save(RetryAttemptDTO dto);
    RetryAttemptDTO update(RetryAttemptDTO dto);
    RetryAttemptDTO findById(Long id);
    void deleteById(Long id);
    List<RetryAttemptDTO> findAll();
}