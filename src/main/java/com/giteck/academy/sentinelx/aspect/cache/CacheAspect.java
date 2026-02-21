// src/main/java/com/giteck/academy/sentinelx/aspect/cache/CacheAspect.java
package com.giteck.academy.sentinelx.aspect.cache;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
//@ConditionalOnProperty(name = "sentinel.cache.enabled", havingValue = "true")
@Component
public class CacheAspect {

    private static final Logger log = LoggerFactory.getLogger(CacheAspect.class);

    // Notre cache en mémoire : Map<CacheName, Map<CacheKey, Value>>
    private final Map<String, Map<String, Object>> cacheStore = new ConcurrentHashMap<>();

    @Around("@annotation(customCache)")
    public Object handleCache(ProceedingJoinPoint joinPoint, CustomCache customCache) throws Throwable {
        String cacheName = customCache.cacheName();
        // On génère une clé basée sur le nom de la méthode et ses arguments
        String key = joinPoint.getSignature().getName() + "-" + Arrays.toString(joinPoint.getArgs());

        // Initialise le sous-cache s'il n'existe pas
        cacheStore.putIfAbsent(cacheName, new ConcurrentHashMap<>());
        Map<String, Object> specificCache = cacheStore.get(cacheName);

        // 1. Vérifie si la valeur est déjà en cache
        if (specificCache.containsKey(key)) {
            log.info("[CACHE HIT] cacheName={}, key={} -> Retour de la valeur en cache", cacheName, key);
            return specificCache.get(key);
        }

        // 2. Si pas en cache (MISS), on exécute la vraie méthode
        log.info("[CACHE MISS] cacheName={}, key={} -> Exécution de la méthode...", cacheName, key);
        Object result = joinPoint.proceed();

        // 3. On stocke le résultat pour les prochains appels
        if (result != null) {
            specificCache.put(key, result);
        }

        return result;
    }
}