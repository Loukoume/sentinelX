// src/main/java/com/giteck/academy/sentinelx/security/SimulatedSecurityContextHolder.java
package com.giteck.academy.sentinelx.security;

public class SimulatedSecurityContextHolder {

    // Le ThreadLocal garantit que chaque thread a sa propre instance de SimulatedUserContext
    private static final ThreadLocal<SimulatedUserContext> contextHolder = new ThreadLocal<>();

    public static void setContext(SimulatedUserContext context) {
        contextHolder.set(context);
    }

    public static SimulatedUserContext getContext() {
        return contextHolder.get();
    }

    // Méthode de commodité très utile pour nos futurs aspects
    public static String getCurrentUsername() {
        SimulatedUserContext context = getContext();
        return (context != null && context.getUsername() != null) ? context.getUsername() : "anonymous";
    }

    public static String getCurrentTraceId() {
        SimulatedUserContext context = getContext();
        return (context != null) ? context.getTraceId() : null;
    }

    // CRUCIAL : Évite les fuites de mémoire (Memory Leaks) dans les serveurs web
    public static void clear() {
        contextHolder.remove();
    }
}