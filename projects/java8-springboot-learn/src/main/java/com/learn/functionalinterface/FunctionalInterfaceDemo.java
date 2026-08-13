package com.learn.functionalinterface;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Java 8 built-in functional interfaces: Predicate, Function, Consumer, Supplier.
 */
@Service
public class FunctionalInterfaceDemo {

    @FunctionalInterface
    public interface AccountValidator {
        boolean validate(String accountNumber);

        default void log(String msg) {
            System.out.println("[AccountValidator] " + msg);
        }

        static boolean isTenAlphanumeric(String value) {
            return value != null && value.matches("[A-Za-z0-9]{10}");
        }
    }

    public Object demo() {
        Predicate<String> isLongName = name -> name != null && name.length() > 5;
        Function<String, String> toUpper = String::toUpperCase;
        Consumer<String> printer = s -> System.out.println("Consumer: " + s);
        Supplier<String> defaultDept = () -> "GENERAL";

        List<String> names = Arrays.asList("Ravi", "Shaik Ishaq", "Amit", "Anjieny");
        List<String> filteredUpper = names.stream()
                .filter(isLongName)
                .map(toUpper)
                .peek(printer)
                .collect(Collectors.toList());

        AccountValidator validator = AccountValidator::isTenAlphanumeric;
        boolean validAccount = validator.validate("ABCDE12345");
        validator.log("account valid=" + validAccount);

        return Arrays.asList(
                "filteredUpper=" + filteredUpper,
                "defaultDept=" + defaultDept.get(),
                "validAccount=" + validAccount
        );
    }
}
