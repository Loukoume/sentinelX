// src/main/java/com/giteck/academy/sentinelx/controller/ProfilingSpanController.java
package com.giteck.academy.sentinelx.controller;

import com.giteck.academy.sentinelx.dto.ProfilingSpanDTO;
import com.giteck.academy.sentinelx.service.ProfilingSpanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profiling-spans")
@CrossOrigin("*")
@RequiredArgsConstructor
public class ProfilingSpanController {
    private final ProfilingSpanService service;

    @GetMapping("/all")
    public List<ProfilingSpanDTO> getAll() {
        return service.findAll();
    }

    @GetMapping("/find_by_id/{id}")
    public ProfilingSpanDTO findById(@PathVariable String id) {
        return service.findById(id);
    }

    @PostMapping("/save")
    public ProfilingSpanDTO save(@Valid @RequestBody ProfilingSpanDTO dto) {
        return service.save(dto);
    }

    @PostMapping("/update")
    public ProfilingSpanDTO update(@Valid @RequestBody ProfilingSpanDTO dto) {
        return service.update(dto);
    }

    @GetMapping("/delete/{id}")
    public void deleteById(@PathVariable String id) {
        service.deleteById(id);
    }
}