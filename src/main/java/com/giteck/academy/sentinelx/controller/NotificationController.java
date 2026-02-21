// src/main/java/com/giteck/academy/sentinelx/controller/NotificationController.java
package com.giteck.academy.sentinelx.controller;

import com.giteck.academy.sentinelx.dto.NotificationDTO;
import com.giteck.academy.sentinelx.service.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin("*")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService service;

    @GetMapping("/all")
    public List<NotificationDTO> getAll() {
        return service.findAll();
    }

    @GetMapping("/find_by_id/{id}")
    public NotificationDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping("/save")
    public NotificationDTO save(@Valid @RequestBody NotificationDTO dto) {
        return service.save(dto);
    }

    @PostMapping("/update")
    public NotificationDTO update(@Valid @RequestBody NotificationDTO dto) {
        return service.update(dto);
    }

    @GetMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}


