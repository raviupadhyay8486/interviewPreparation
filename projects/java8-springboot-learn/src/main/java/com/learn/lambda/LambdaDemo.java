package com.learn.lambda;

import com.learn.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Java 8 Lambda expressions and effectively-final capture.
 */
@Service
public class LambdaDemo {

    public Object demo() {
        List<Employee> employees = Employee.sampleList();
        final String prefix = "Emp:"; // effectively final

        List<String> labels = employees.stream()
                .sorted(Comparator.comparingInt(Employee::getSalary).reversed())
                .map(e -> prefix + " " + e.getName() + " => " + e.getSalary())
                .collect(Collectors.toList());

        // classic lambda comparator
        employees.sort((e1, e2) -> e1.getName().compareToIgnoreCase(e2.getName()));

        return ArraysSafe.of(
                "sortedBySalaryDesc=" + labels,
                "sortedByName=" + employees.stream().map(Employee::getName).collect(Collectors.toList())
        );
    }

    /** Tiny helper to avoid importing Arrays in every line. */
    private static final class ArraysSafe {
        static java.util.List<String> of(String a, String b) {
            return java.util.Arrays.asList(a, b);
        }
    }
}
