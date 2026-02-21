// src/main/java/com/giteck/academy/sentinelx/controller/DummyDataController.java
package com.giteck.academy.sentinelx.controller;

import com.giteck.academy.sentinelx.service.impl.DummyDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dummy")
@CrossOrigin("*")
@RequiredArgsConstructor
public class DummyDataController {

    private final DummyDataService dummyDataService;

    @GetMapping("/test-self-invocation")
    public String testCache() {
        return dummyDataService.getDonnees();
    }
}