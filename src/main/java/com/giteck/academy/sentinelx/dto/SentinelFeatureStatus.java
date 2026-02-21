package com.giteck.academy.sentinelx.dto;

import lombok.Builder;
import lombok.Data;
import java.time.Instant;

@Data
@Builder
public class SentinelFeatureStatus {
    private boolean enabled;
    private boolean auditEnabled;
    private boolean cacheEnabled;
    private boolean retryEnabled;
    private boolean profilingEnabled;
    private String activeProfile;
    private Instant timestamp;
}