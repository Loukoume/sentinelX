package com.giteck.academy.sentinelx.controller;

import com.giteck.academy.sentinelx.dto.AuditEventDTO;
import com.giteck.academy.sentinelx.service.impl.AuditEventServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/audit-events")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AuditEventController {
    private final AuditEventServiceImpl service;

    @GetMapping("/all")
    public List<AuditEventDTO> getAll() {
        return service.findAll();
    }

    @GetMapping("/find_by_id/{id}")
    public AuditEventDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping("/save")
    public AuditEventDTO save(@Valid @RequestBody AuditEventDTO dto) {
        return service.save(dto);
    }

    @PostMapping("/update")
    public AuditEventDTO update(@Valid @RequestBody AuditEventDTO dto) {
        return service.update(dto);
    }

    @GetMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}
