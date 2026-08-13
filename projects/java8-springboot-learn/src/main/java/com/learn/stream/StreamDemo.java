package com.learn.stream;

import com.learn.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Streams: map vs flatMap, filter, distinct, sorted.
 */
@Service
public class StreamDemo {

    public Object mapVsFlatMap() {
        List<Employee> employees = Employee.sampleList();

        // map: 1 -> 1 (List of skill-lists remains nested if mapped to list)
        List<List<String>> nestedSkills = employees.stream()
                .map(Employee::getSkills)
                .collect(Collectors.toList());

        // flatMap: 1 -> many, then flatten
        List<String> flatSkills = employees.stream()
                .flatMap(e -> e.getSkills().stream())
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        // words from names via flatMap
        List<String> nameWords = employees.stream()
                .flatMap(e -> Arrays.stream(e.getName().split("\\s+")))
                .collect(Collectors.toList());

        return Arrays.asList(
                "nestedSkills=" + nestedSkills,
                "flatSkills=" + flatSkills,
                "nameWords=" + nameWords
        );
    }

    public Object basicPipeline() {
        return Employee.sampleList().stream()
                .filter(e -> e.getSalary() >= 3000)
                .map(Employee::getName)
                .collect(Collectors.toList());
    }

    /**
     * From departments, collect unique employee emails that appear in more than one department.
     */
    public Object emailsInMultipleDepartments() {
        Map<String, List<Employee>> deptMap = DepartmentEmailData.sampleDepartmentMap();

        Map<String, Object> result = new java.util.LinkedHashMap<String, Object>();
        result.put("departmentData", deptMap.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().stream()
                                .map(emp -> emp.getName() + " <" + emp.getEmail() + ">")
                                .collect(Collectors.toList())
                )));
        result.put("emailsInMultipleDepartments",
                DepartmentEmailData.emailsInMultipleDepartments(deptMap));
        result.put("expected", Arrays.asList(
                "ravi.ranjan@company.com (PD + MG)",
                "ishaq@company.com (PD + HR)"
        ));
        return result;
    }
}
