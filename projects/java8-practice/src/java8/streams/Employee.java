package java8.streams;

import java.util.Objects;

/**
 * Simple employee model for Streams / flatMap practice.
 */
public class Employee {
    private final int id;
    private final String name;
    private final String email;
    private final String department;
    private final int salary;

    public Employee(int id, String name, String email, String department, int salary) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.department = department;
        this.salary = salary;
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

    @Override
    public String toString() {
        return "Employee{id=" + id
                + ", name='" + name + '\''
                + ", email='" + email + '\''
                + ", department='" + department + '\''
                + ", salary=" + salary + '}';
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
