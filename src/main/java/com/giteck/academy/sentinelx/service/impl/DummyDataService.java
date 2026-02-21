// src/main/java/com/giteck/academy/sentinelx/service/impl/DummyDataService.java
package com.giteck.academy.sentinelx.service.impl;

import com.giteck.academy.sentinelx.aspect.cache.CustomCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class DummyDataService {

    // 1. INJECTION DU PROXY DE LUI-MÊME
    @Autowired
    @Lazy // @Lazy est absolument crucial ici pour éviter la dépendance circulaire
    private DummyDataService selfProxy;

    private static final Logger log = LoggerFactory.getLogger(DummyDataService.class);

    // Méthode publique appelée par le Controller
    public String getDonnees() {
        log.info("--> getDonnees() appelé (Appel Externe, passe par le Proxy)");

        // LE PIÈGE EST ICI : Appel interne via "this". 
        // On contourne le proxy Spring, donc l'aspect @CustomCache ne sera pas lu !
       // return this.calculLong();

        // 2. LA CORRECTION : On remplace "this.calculLong()" par "selfProxy.calculLong()"
        // On force l'appel à repasser par le pare-feu du Proxy !
        return selfProxy.calculLong();
    }

    // Méthode annotée qui devrait être mise en cache
    @CustomCache(cacheName = "dataCache")
    public String calculLong() {
        log.info("--> calculLong() exécute un traitement lourd...");
        try {
            // Simulation d'une tâche lente (1 seconde)
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "Données secrètes calculées !";
    }
}