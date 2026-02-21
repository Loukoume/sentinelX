// src/main/java/com/giteck/academy/sentinelx/aspect/retry/RetryAspect.java
package com.giteck.academy.sentinelx.aspect.retry;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class RetryAspect {

    private static final Logger log = LoggerFactory.getLogger(RetryAspect.class);

    @Around("@annotation(retryConfig)")
    public Object handleRetry(ProceedingJoinPoint joinPoint, RetryOnFailure retryConfig) throws Throwable {
        int maxAttempts = retryConfig.maxAttempts();
        long delay = retryConfig.delayMs();
        int attempt = 0;
        Throwable lastException = null;

        while (attempt < maxAttempts) {
            attempt++;
            try {
                if (retryConfig.logEachAttempt()) {
                    log.info("[RETRY ASPECT] Exécution tentative {}/{} pour {}", attempt, maxAttempts, joinPoint.getSignature().getName());
                }

                // On tente d'exécuter la méthode cible
                return joinPoint.proceed();

            } catch (Throwable ex) {
                lastException = ex;
                if (!isRetryable(ex, retryConfig.retryOn())) {
                    log.error("[RETRY ASPECT] Exception non éligible au retry : {}", ex.getClass().getSimpleName());
                    throw ex;
                }

                log.warn("[RETRY ASPECT] Échec de la tentative {}/{} : {}", attempt, maxAttempts, ex.getMessage());

                if (attempt < maxAttempts) {
                    log.info("[RETRY ASPECT] Attente de {} ms avant la prochaine tentative...", delay);
                    Thread.sleep(delay);
                }
            }
        }

        log.error("[RETRY ASPECT] Toutes les tentatives ont échoué (Max: {})", maxAttempts);
        throw lastException;
    }

    private boolean isRetryable(Throwable ex, Class<? extends Throwable>[] retryableExceptions) {
        for (Class<? extends Throwable> retryable : retryableExceptions) {
            if (retryable.isAssignableFrom(ex.getClass())) {
                return true;
            }
        }
        return false;
    }
}