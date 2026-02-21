// src/main/java/com/giteck/academy/sentinelx/aspect/audit/AuditAspect.java
package com.giteck.academy.sentinelx.aspect.audit;

import com.giteck.academy.sentinelx.dto.AuditEventDTO;
import com.giteck.academy.sentinelx.nume.AuditStatus;
import com.giteck.academy.sentinelx.security.SimulatedSecurityContextHolder;
import com.giteck.academy.sentinelx.service.AuditEventService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
//@ConditionalOnProperty(name = "sentinel.audit.enabled", havingValue = "true")
@Component
@RequiredArgsConstructor
public class AuditAspect {

    private static final Logger log = LoggerFactory.getLogger(AuditAspect.class);
    private final AuditEventService auditEventService;

    @Around("@annotation(secureAudit)")
    public Object auditMethod(ProceedingJoinPoint joinPoint, SecureAudit secureAudit) throws Throwable {
        long start = System.currentTimeMillis();
        AuditStatus status = AuditStatus.SUCCESS;
        String exceptionClass = null;
        String exceptionMessage = null;
        Object result = null;

        try {
            // Laisse la vraie méthode s'exécuter
            result = joinPoint.proceed();
            return result;

        } catch (Throwable ex) {
            // Capture l'échec sans casser le flux métier
            status = AuditStatus.FAILURE;
            exceptionClass = ex.getClass().getSimpleName();
            exceptionMessage = ex.getMessage();
            throw ex;

        } finally {
            // Le bloc finally s'exécute TOUJOURS, en succès comme en échec
            long durationMs = System.currentTimeMillis() - start;
            String username = SimulatedSecurityContextHolder.getCurrentUsername();
            String traceId = SimulatedSecurityContextHolder.getCurrentTraceId();
            String methodSignature = joinPoint.getSignature().toShortString();

            // Gestion du masquage des arguments sensibles
            String argsSummary = null;
            if (secureAudit.logArgs()) {
                argsSummary = formatArgs(joinPoint.getArgs(), secureAudit.sensitive());
            }

            // Gestion du résultat
            String resultSummary = null;
            if (secureAudit.logResult() && status == AuditStatus.SUCCESS) {
                resultSummary = (result != null) ? result.toString() : "null";
                if (secureAudit.sensitive()) {
                    resultSummary = "[MASKED SENSITIVE DATA]";
                }
            }

            // 1. Log console structuré (facilite l'exploitation par un outil comme ELK/Kibana)
            log.info("[AUDIT] action={}, user={}, status={}, method={}, durationMs={}ms, args={}, traceId={}",
                    secureAudit.action(), username, status, methodSignature, durationMs, argsSummary, traceId);

            if (status == AuditStatus.FAILURE) {
                log.error("[AUDIT FAILURE DETAILS] exception={}, message={}", exceptionClass, exceptionMessage);
            }

            // 2. Sauvegarde asynchrone (idéalement) en Base de Données
            saveEventToDatabase(secureAudit, username, methodSignature, status, durationMs,
                    argsSummary, resultSummary, exceptionClass, exceptionMessage, traceId);
        }
    }

    private String formatArgs(Object[] args, boolean sensitive) {
        if (args == null || args.length == 0) return "[]";
        // Si l'annotation indique que c'est sensible, on ne tente même pas de faire un toString()
        if (sensitive) return "[MASKED SENSITIVE DATA]";

        return Arrays.toString(args);
    }

    private void saveEventToDatabase(SecureAudit secureAudit, String username, String methodSignature,
                                     AuditStatus status, long durationMs, String argsSummary,
                                     String resultSummary, String exceptionClass,
                                     String exceptionMessage, String traceId) {
        try {
            AuditEventDTO eventDTO = AuditEventDTO.builder()
                    .action(secureAudit.action())
                    .username(username)
                    .methodSignature(methodSignature)
                    .status(status)
                    .durationMs(durationMs)
                    .argsLogged(secureAudit.logArgs())
                    .argsMasked(secureAudit.sensitive())
                    .argsSummary(argsSummary)
                    .resultSummary(resultSummary)
                    .exceptionClass(exceptionClass)
                    .exceptionMessage(exceptionMessage)
                    .traceId(traceId)
                    .build();
            auditEventService.save(eventDTO);
        } catch (Exception e) {
            log.error("Échec de la sauvegarde de l'événement d'audit en BDD", e);
        }
    }
}