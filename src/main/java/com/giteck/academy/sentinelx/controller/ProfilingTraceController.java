// src/main/java/com/giteck/academy/sentinelx/controller/ProfilingTraceController.java
package com.giteck.academy.sentinelx.controller;

import com.giteck.academy.sentinelx.dto.ProfilingTraceDTO;
import com.giteck.academy.sentinelx.service.ProfilingTraceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profiling-traces")
@CrossOrigin("*")
@RequiredArgsConstructor
public class ProfilingTraceController {
    private final ProfilingTraceService service;

    @GetMapping("/all")
    public List<ProfilingTraceDTO> getAll() {
        return service.findAll();
    }

    @GetMapping("/find_by_id/{id}")
    public ProfilingTraceDTO findById(@PathVariable String id) {
        return service.findById(id);
    }

    @PostMapping("/save")
    public ProfilingTraceDTO save(@Valid @RequestBody ProfilingTraceDTO dto) {
        return service.save(dto);
    }

    @PostMapping("/update")
    public ProfilingTraceDTO update(@Valid @RequestBody ProfilingTraceDTO dto) {
        return service.update(dto);
    }

    @GetMapping("/delete/{id}")
    public void deleteById(@PathVariable String id) {
        service.deleteById(id);
    }
}