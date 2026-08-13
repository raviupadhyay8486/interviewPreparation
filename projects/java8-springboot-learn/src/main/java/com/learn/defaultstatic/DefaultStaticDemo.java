package com.learn.defaultstatic;

import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Default methods, static methods, diamond resolution.
 */
@Service
public class DefaultStaticDemo {

    interface LoggerA {
        default String log(String msg) {
            return "A:" + msg;
        }
    }

    interface LoggerB {
        default String log(String msg) {
            return "B:" + msg;
        }
    }

    interface Validator {
        boolean test(String value);

        default void info(String msg) {
            System.out.println("[Validator] " + msg);
        }

        static Validator accountRule() {
            return v -> v != null && v.matches("[A-Za-z0-9]{10}");
        }
    }

    static class AuditService implements LoggerA, LoggerB {
        @Override
        public String log(String msg) {
            // diamond resolution
            return LoggerA.super.log(msg) + " | " + LoggerB.super.log(msg);
        }
    }

    public Object demo() {
        Validator validator = Validator.accountRule();
        boolean ok = validator.test("ABCDE12345");
        validator.info("validated=" + ok);

        AuditService audit = new AuditService();
        String diamond = audit.log("lender-search");

        return Arrays.asList(
                "staticFactoryValid=" + ok,
                "diamondResolved=" + diamond
        );
    }
}
