package com.giteck.academy.sentinelx.service;
import com.giteck.academy.sentinelx.dto.AuditEventDTO;
import org.springframework.data.domain.Page;
import java.util.List;

public interface AuditEventService {
    AuditEventDTO save(AuditEventDTO dto);
    AuditEventDTO update(AuditEventDTO dto);
    AuditEventDTO findById(Long id);
    void deleteById(Long id);
    List<AuditEventDTO> findAll();
}
