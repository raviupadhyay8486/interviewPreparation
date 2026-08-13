package com.learn.optional;

import com.learn.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Optional best practices: ofNullable, map, flatMap, orElseGet.
 */
@Service
public class OptionalDemo {

    public Object demo() {
        List<Employee> employees = Employee.sampleList();

        Optional<Employee> found = findByEmail(employees, "amit@company.com");
        Optional<Employee> missing = findByEmail(employees, "missing@company.com");

        String amitDept = found.map(Employee::getDepartment).orElse("UNKNOWN");
        String missingDept = missing.map(Employee::getDepartment).orElseGet(() -> "DEFAULT_DEPT");

        // flatMap style chaining
        Optional<String> upperEmail = found
                .map(Employee::getEmail)
                .flatMap(this::normalizeEmail);

        return java.util.Arrays.asList(
                "amitDept=" + amitDept,
                "missingDept=" + missingDept,
                "upperEmail=" + upperEmail.orElse("n/a"),
                "foundPresent=" + found.isPresent()
        );
    }

    private Optional<Employee> findByEmail(List<Employee> employees, String email) {
        return employees.stream()
                .filter(e -> e.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    private Optional<String> normalizeEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(email.trim().toUpperCase());
    }
}
