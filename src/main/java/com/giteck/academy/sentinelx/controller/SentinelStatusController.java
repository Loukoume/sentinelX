// src/main/java/com/giteck/academy/sentinelx/controller/SentinelStatusController.java
package com.giteck.academy.sentinelx.controller;

import com.giteck.academy.sentinelx.dto.SentinelFeatureStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("/api/sentinel")
@CrossOrigin("*")
public class SentinelStatusController {

    @Value("${sentinel.enabled:false}")
    private boolean enabled;

    @Value("${sentinel.audit.enabled:false}")
    private boolean auditEnabled;

    @Value("${sentinel.cache.enabled:false}")
    private boolean cacheEnabled;

    @Value("${sentinel.retry.enabled:false}")
    private boolean retryEnabled;

    @Value("${sentinel.profiling.enabled:false}")
    private boolean profilingEnabled;

    private final Environment env;

    public SentinelStatusController(Environment env) {
        this.env = env;
    }

    @GetMapping("/status")
    public SentinelFeatureStatus getStatus() {
        String[] activeProfiles = env.getActiveProfiles();
        String profile = activeProfiles.length > 0 ? activeProfiles[0] : "default";

        return SentinelFeatureStatus.builder()
                .enabled(enabled)
                .auditEnabled(auditEnabled)
                .cacheEnabled(cacheEnabled)
                .retryEnabled(retryEnabled)
                .profilingEnabled(profilingEnabled)
                .activeProfile(profile)
                .timestamp(Instant.now())
                .build();
    }
}