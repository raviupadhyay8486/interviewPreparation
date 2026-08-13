package com.learn.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Prints available endpoints on startup.
 */
@Component
public class StartupBanner implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(StartupBanner.class);

    @Override
    public void run(String... args) {
        log.info("======================================================");
        log.info(" Java 8 Learn App started");
        log.info(" Open: http://localhost:8088/api/java8");
        log.info(" Topics: functional-interface, lambda, stream, collector,");
        log.info("         optional, method-reference, datetime, concurrency,");
        log.info("         default-static");
        log.info("======================================================");
    }
}
