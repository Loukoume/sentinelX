// src/main/java/com/giteck/academy/sentinelx/security/SimulatedSecurityFilter.java
package com.giteck.academy.sentinelx.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Instant;
import java.util.UUID;

@Component
public class SimulatedSecurityFilter extends OncePerRequestFilter {

    private static final String USER_HEADER = "X-User";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 1. Extraction de l'utilisateur depuis le header HTTP
        String username = request.getHeader(USER_HEADER);

        // Si le header n'est pas fourni, on applique la règle du cahier des charges
        if (username == null || username.trim().isEmpty()) {
            username = "anonymous";
        }

        // Anticipation pour le Profiling (Objectif 4) : on génère un traceId unique par requête
        String traceId = UUID.randomUUID().toString();

        // 2. Création du contexte
        SimulatedUserContext context = SimulatedUserContext.builder()
                .username(username)
                .traceId(traceId)
                .setAt(Instant.now())
                .build();

        try {
            // 3. On place le contexte dans le ThreadLocal
            SimulatedSecurityContextHolder.setContext(context);

            // On ajoute le traceId dans la réponse pour faciliter le debug côté client
            response.addHeader("X-Trace-Id", traceId);

            // 4. On passe la main au reste de l'application (Controllers, Services...)
            filterChain.doFilter(request, response);

        } finally {
            // 5. NETTOYAGE OBLIGATOIRE (Même en cas d'exception dans le controller)
            // Comme les serveurs d'application (Tomcat) recyclent les threads, 
            // ne pas vider le ThreadLocal ferait fuiter les identités d'une requête à l'autre.
            SimulatedSecurityContextHolder.clear();
        }
    }
}