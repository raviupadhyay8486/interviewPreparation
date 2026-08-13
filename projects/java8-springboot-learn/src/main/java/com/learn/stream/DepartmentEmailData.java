package com.learn.stream;

import com.learn.model.Employee;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Task: From departments, collect unique employee emails that appear in more than one department.
 *
 * Sample data intentionally shares emails across departments:
 * - ravi.ranjan@company.com → PD + MG
 * - ishaq@company.com       → PD + HR
 */
public final class DepartmentEmailData {

    private DepartmentEmailData() {
    }

    /**
     * Department -> list of employees (Java objects with data to play with).
     */
    public static Map<String, List<Employee>> sampleDepartmentMap() {
        Map<String, List<Employee>> deptMap = new HashMap<String, List<Employee>>();

        // PD department
        deptMap.put("PD", Arrays.asList(
                new Employee(1, "Ravi Ranjan", "ravi.ranjan@company.com", "PD", 3000,
                        Arrays.asList("Java", "Spring")),
                new Employee(2, "Shaik Ishaq", "ishaq@company.com", "PD", 2000,
                        Arrays.asList("Java", "Kafka")),
                new Employee(3, "Neha", "neha@company.com", "PD", 3500,
                        Arrays.asList("Spring", "Docker"))
        ));

        // HR department (Ishaq email repeated from PD)
        deptMap.put("HR", Arrays.asList(
                new Employee(4, "Anjieny", "anjieny@company.com", "HR", 40000,
                        Arrays.asList("Comm")),
                new Employee(5, "Santosh", "santosh@company.com", "HR", 1500,
                        Arrays.asList("Java")),
                new Employee(6, "Shaik Ishaq", "ishaq@company.com", "HR", 2200,
                        Arrays.asList("Excel"))
        ));

        // MG department (Ravi email repeated from PD)
        deptMap.put("MG", Arrays.asList(
                new Employee(7, "Amit", "amit@company.com", "MG", 6000,
                        Arrays.asList("React", "AWS")),
                new Employee(8, "Ravi Ranjan", "ravi.ranjan@company.com", "MG", 5000,
                        Arrays.asList("Java", "AWS"))
        ));

        return deptMap;
    }

    /**
     * Unique emails that appear in more than one department.
     */
    public static Set<String> emailsInMultipleDepartments(Map<String, List<Employee>> deptMap) {
        return deptMap.values().stream()
                .flatMap(List::stream)
                .collect(Collectors.groupingBy(Employee::getEmail, Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }
}
