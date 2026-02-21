// src/main/java/com/giteck/academy/sentinelx/controller/RetryExecutionController.java
package com.giteck.academy.sentinelx.controller;

import com.giteck.academy.sentinelx.dto.RetryExecutionDTO;
import com.giteck.academy.sentinelx.service.RetryExecutionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/retry-executions")
@CrossOrigin("*")
@RequiredArgsConstructor
public class RetryExecutionController {
    private final RetryExecutionService service;

    @GetMapping("/all")
    public List<RetryExecutionDTO> getAll() {
        return service.findAll();
    }

    @GetMapping("/find_by_id/{id}")
    public RetryExecutionDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping("/save")
    public RetryExecutionDTO save(@Valid @RequestBody RetryExecutionDTO dto) {
        return service.save(dto);
    }

    @PostMapping("/update")
    public RetryExecutionDTO update(@Valid @RequestBody RetryExecutionDTO dto) {
        return service.update(dto);
    }

    @GetMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}