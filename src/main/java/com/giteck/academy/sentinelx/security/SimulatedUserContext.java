// src/main/java/com/giteck/academy/sentinelx/security/SimulatedUserContext.java
package com.giteck.academy.sentinelx.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimulatedUserContext {
    private String username;
    private List<String> roles;
    private String traceId;     // Utile plus tard pour le profiling
    private Instant setAt;
}