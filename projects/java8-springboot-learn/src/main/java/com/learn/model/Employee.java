package com.learn.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Shared Employee model for all Java 8 topic demos.
 */
public class Employee {

    private int id;
    private String name;
    private String email;
    private String department;
    private int salary;
    private List<String> skills;

    public Employee() {
    }

    public Employee(int id, String name, String email, String department, int salary, List<String> skills) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.department = department;
        this.salary = salary;
        this.skills = skills != null ? skills : new ArrayList<String>();
    }

    public static List<Employee> sampleList() {
        return Arrays.asList(
                new Employee(1, "Ravi Ranjan", "ravi.ranjan@company.com", "PD", 3000, Arrays.asList("Java", "Spring", "AWS")),
                new Employee(2, "Shaik Ishaq", "ishaq@company.com", "PD", 2000, Arrays.asList("Java", "Kafka")),
                new Employee(3, "Anjieny", "anjieny@company.com", "HR", 40000, Arrays.asList("Comm", "Excel")),
                new Employee(4, "Santosh", "santosh@company.com", "HR", 1500, Arrays.asList("Java")),
                new Employee(5, "Amit", "amit@company.com", "MG", 6000, Arrays.asList("React", "Java", "AWS")),
                new Employee(6, "Neha", "neha@company.com", "PD", 3500, Arrays.asList("Spring", "Docker")),
                new Employee(7, "Isha", "isha@company.com", "MG", 5500, Arrays.asList("Kafka", "AWS"))
        );
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDepartment() {
        return department;
    }

    public int getSalary() {
        return salary;
    }

    public List<String> getSkills() {
        return skills;
    }

    @Override
    public String toString() {
        return "Employee{id=" + id + ", name='" + name + "', dept='" + department
                + "', salary=" + salary + ", email='" + email + "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Employee)) {
            return false;
        }
        Employee employee = (Employee) o;
        return id == employee.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
