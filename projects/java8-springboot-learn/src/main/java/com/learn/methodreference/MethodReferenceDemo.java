package com.learn.methodreference;

import com.learn.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Method references: static, instance, arbitrary-instance, constructor.
 */
@Service
public class MethodReferenceDemo {

    public static String formatName(String name) {
        return name == null ? "" : name.trim().toUpperCase();
    }

    public Object demo() {
        List<Employee> employees = Employee.sampleList();

        // static method reference
        List<String> upperNames = employees.stream()
                .map(Employee::getName)
                .map(MethodReferenceDemo::formatName)
                .collect(Collectors.toList());

        // instance method on arbitrary object
        List<Integer> nameLengths = employees.stream()
                .map(Employee::getName)
                .map(String::length)
                .collect(Collectors.toList());

        // constructor reference
        List<Employee> copied = employees.stream()
                .map(e -> new Employee(e.getId(), e.getName(), e.getEmail(), e.getDepartment(), e.getSalary(), e.getSkills()))
                .collect(Collectors.toList());

        return java.util.Arrays.asList(
                "upperNames=" + upperNames,
                "nameLengths=" + nameLengths,
                "copiedSize=" + copied.size()
        );
    }
}
