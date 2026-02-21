package com.giteck.academy.sentinelx.aspect.audit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation déclenchant l'audit automatique d'une méthode métier.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SecureAudit {

    // Le nom de l'action métier (ex: "TRANSFER_CREATE", "ACCOUNT_CREATE")
    String action();

    // Si true, les arguments de la méthode seront masqués dans les logs et la BDD
    boolean sensitive() default false;

    // Permet de désactiver complètement le log des arguments
    boolean logArgs() default true;

    // Permet de journaliser un résumé du résultat de la méthode
    boolean logResult() default false;
}