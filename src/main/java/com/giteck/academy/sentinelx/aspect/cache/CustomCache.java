// src/main/java/com/giteck/academy/sentinelx/aspect/cache/CustomCache.java
package com.giteck.academy.sentinelx.aspect.cache;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomCache {
    // Le nom du cache
    String cacheName();
}