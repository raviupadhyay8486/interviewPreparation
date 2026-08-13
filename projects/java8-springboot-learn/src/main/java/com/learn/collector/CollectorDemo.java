package com.learn.collector;

import com.learn.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Collectors: toList, toSet, toMap, groupingBy, partitioningBy, joining.
 */
@Service
public class CollectorDemo {

    public Object demo() {
        List<Employee> employees = Employee.sampleList();

        List<String> names = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());

        Set<String> departments = employees.stream()
                .map(Employee::getDepartment)
                .collect(Collectors.toSet());

        Map<String, Integer> emailToSalary = employees.stream()
                .collect(Collectors.toMap(
                        Employee::getEmail,
                        Employee::getSalary,
                        (oldV, newV) -> newV,
                        LinkedHashMap::new
                ));

        Map<String, List<Employee>> byDept = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

        Map<String, Long> countByDept = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));

        Map<Boolean, List<Employee>> partitioned = employees.stream()
                .collect(Collectors.partitioningBy(e -> e.getSalary() >= 3000));

        String csv = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(", ", "[", "]"));

        Map<String, Optional<Employee>> maxSalByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.maxBy(Comparator.comparingInt(Employee::getSalary))
                ));

        // emails appearing more than once if we duplicated — demo count>=1 grouping style
        Set<String> javaSkillEmails = employees.stream()
                .filter(e -> e.getSkills().contains("Java"))
                .map(Employee::getEmail)
                .collect(Collectors.toSet());

        Map<String, Object> result = new LinkedHashMap<String, Object>();
        result.put("toList_names", names);
        result.put("toSet_departments", departments);
        result.put("toMap_emailToSalary", emailToSalary);
        result.put("groupingBy_dept", summarize(byDept));
        result.put("groupingBy_count", countByDept);
        result.put("partitioningBy_highSalary", partitioned.get(true).stream().map(Employee::getName).collect(Collectors.toList()));
        result.put("joining_csv", csv);
        result.put("maxSalaryByDept", maxSalByDept.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().map(Employee::getName).orElse("n/a")
                )));
        result.put("javaSkillEmails", javaSkillEmails);
        return result;
    }

    private Map<String, List<String>> summarize(Map<String, List<Employee>> byDept) {
        return byDept.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().stream().map(Employee::getName).collect(Collectors.toList())
                ));
    }
}
