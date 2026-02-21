// src/main/java/com/giteck/academy/sentinelx/aspect/retry/RetryOnFailure.java
package com.giteck.academy.sentinelx.aspect.retry;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RetryOnFailure {
    int maxAttempts() default 3;
    long delayMs() default 1000;
    Class<? extends Throwable>[] retryOn() default {Exception.class};
    boolean logEachAttempt() default true;
}