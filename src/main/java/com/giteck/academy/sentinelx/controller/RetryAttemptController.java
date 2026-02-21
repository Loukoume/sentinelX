// src/main/java/com/giteck/academy/sentinelx/controller/RetryAttemptController.java
package com.giteck.academy.sentinelx.controller;

import com.giteck.academy.sentinelx.dto.RetryAttemptDTO;
import com.giteck.academy.sentinelx.service.RetryAttemptService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/retry-attempts")
@CrossOrigin("*")
@RequiredArgsConstructor
public class RetryAttemptController {
    private final RetryAttemptService service;

    @GetMapping("/all")
    public List<RetryAttemptDTO> getAll() {
        return service.findAll();
    }

    @GetMapping("/find_by_id/{id}")
    public RetryAttemptDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping("/save")
    public RetryAttemptDTO save(@Valid @RequestBody RetryAttemptDTO dto) {
        return service.save(dto);
    }

    @PostMapping("/update")
    public RetryAttemptDTO update(@Valid @RequestBody RetryAttemptDTO dto) {
        return service.update(dto);
    }

    @GetMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}