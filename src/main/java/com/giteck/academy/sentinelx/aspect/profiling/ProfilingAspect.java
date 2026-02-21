// src/main/java/com/giteck/academy/sentinelx/aspect/profiling/ProfilingAspect.java
package com.giteck.academy.sentinelx.aspect.profiling;

import com.giteck.academy.sentinelx.security.SimulatedSecurityContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Aspect
@Component
@ConditionalOnProperty(name = "sentinel.profiling.enabled", havingValue = "true")
public class ProfilingAspect {

    private static final Logger log = LoggerFactory.getLogger(ProfilingAspect.class);

    // Garde en mémoire la profondeur de l'appel pour l'indentation de l'arbre
    private static final ThreadLocal<Integer> depthHolder = ThreadLocal.withInitial(() -> 0);

    // Pointcut 1 : Tous les services SAUF ceux de l'audit et du profiling pour éviter les boucles infinies
    @Pointcut("execution(* com.giteck.academy.sentinelx.service.impl.*.*(..)) " +
            "&& !within(com.giteck.academy.sentinelx.service.impl.Profiling*) " +
            "&& !within(com.giteck.academy.sentinelx.service.impl.AuditEvent*)"+
            "&& !within(com.giteck.academy.sentinelx.service.impl.FinalFeeEngine)")
    public void serviceLayer() {}

    // Pointcut 2 : Tous les repositories
    @Pointcut("execution(* com.giteck.academy.sentinelx.repository.*.*(..)) " +
            "&& !within(com.giteck.academy.sentinelx.repository.Profiling*) " +
            "&& !within(com.giteck.academy.sentinelx.repository.AuditEvent*)")
    public void repositoryLayer() {}

    @Around("serviceLayer() || repositoryLayer()")
    public Object profile(ProceedingJoinPoint pjp) throws Throwable {
        int depth = depthHolder.get();
        String traceId = SimulatedSecurityContextHolder.getCurrentTraceId();
        if (traceId == null) traceId = "SYSTEM";

        // Si c'est le tout premier appel (la racine), on affiche l'en-tête de la trace
        if (depth == 0) {
            log.info("=== Profiling trace_id={} ===", traceId);
        }

        String indent = "  ".repeat(depth); // Génère des espaces selon la profondeur
        String operation = pjp.getSignature().getDeclaringType().getSimpleName() + "." + pjp.getSignature().getName();

        long start = System.currentTimeMillis();

        // On descend d'un niveau avant d'appeler la vraie méthode
        depthHolder.set(depth + 1);

        try {
            return pjp.proceed(); // Exécution de la vraie méthode (et potentiellement de ses sous-appels)
        } finally {
            // On remonte d'un niveau
            depthHolder.set(depth);
            long duration = System.currentTimeMillis() - start;

            // Affichage de la ligne de l'arbre
            log.info("{}{} [{} ms]", indent, operation, duration);

            // Si on est revenu à la racine, on nettoie le ThreadLocal
            if (depth == 0) {
                depthHolder.remove();
                log.info("======================================");
            }
        }
    }
}