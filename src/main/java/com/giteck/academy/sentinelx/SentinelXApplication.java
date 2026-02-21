package com.giteck.academy.sentinelx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableLoadTimeWeaving;

@SpringBootApplication
 public class SentinelXApplication {

    public static void main(String[] args) {
        SpringApplication.run(SentinelXApplication.class, args);
    }

}
