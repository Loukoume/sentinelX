package com.giteck.academy.sentinelx.controller;

import com.giteck.academy.sentinelx.dto.FeeComputationDTO;
import com.giteck.academy.sentinelx.service.impl.FeeComputationServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fee-computations")
@CrossOrigin("*")
@RequiredArgsConstructor
public class FeeComputationController {
    private final FeeComputationServiceImpl service;

    @GetMapping("/all")
    public List<FeeComputationDTO> getAll() {
        return service.findAll();
    }

    @GetMapping("/find_by_id/{id}")
    public FeeComputationDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping("/save")
    public FeeComputationDTO save(@Valid @RequestBody FeeComputationDTO dto) {
        return service.save(dto);
    }

    @PostMapping("/update")
    public FeeComputationDTO update(@Valid @RequestBody FeeComputationDTO dto) {
        return service.update(dto);
    }

    @GetMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}
