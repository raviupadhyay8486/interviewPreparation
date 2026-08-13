package java8.streams;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Task: From departments, collect unique employee emails that appear in more than one department.
 *
 * Run from projects/java8-practice:
 *   javac -d out src/java8/streams/*.java
 *   java -cp out java8.streams.EmailsInMultipleDepartments
 */
public class EmailsInMultipleDepartments {

    public static void main(String[] args) {
        Map<String, List<Employee>> deptMap = sampleDepartmentData();

        System.out.println("=== Department -> Employees ===");
        deptMap.forEach((dept, employees) -> {
            System.out.println(dept + ":");
            employees.forEach(e -> System.out.println("  " + e.getName() + " <" + e.getEmail() + ">"));
        });

        Set<String> emailsInMultipleDepts = findEmailsInMultipleDepartments(deptMap);

        System.out.println();
        System.out.println("=== Emails appearing in MORE THAN ONE department ===");
        System.out.println(emailsInMultipleDepts);

        // Expected with sample data:
        // ravi.ranjan@company.com  -> PD and MG
        // ishaq@company.com        -> PD and HR
        // (anjieny / santosh / amit appear once only)
    }

    /**
     * Core solution using flatMap + groupingBy + counting.
     */
    public static Set<String> findEmailsInMultipleDepartments(Map<String, List<Employee>> deptMap) {
        return deptMap.values().stream()                 // Stream<List<Employee>>
                .flatMap(List::stream)                   // Stream<Employee>  (flatten all depts)
                .collect(Collectors.groupingBy(
                        Employee::getEmail,
                        Collectors.counting()            // email -> how many times it appears
                ))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1)   // more than one department occurrence
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }

    /**
     * Stronger version: count DISTINCT departments per email
     * (useful if same email appears twice in one department list).
     */
    public static Set<String> findEmailsInMultipleDistinctDepartments(Map<String, List<Employee>> deptMap) {
        return deptMap.entrySet().stream()
                .flatMap(entry -> entry.getValue().stream()
                        .map(emp -> new DeptEmail(entry.getKey(), emp.getEmail())))
                .collect(Collectors.groupingBy(
                        DeptEmail::getEmail,
                        Collectors.mapping(DeptEmail::getDepartment, Collectors.toSet())
                ))
                .entrySet().stream()
                .filter(e -> e.getValue().size() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }

    /**
     * Sample data to play with.
     *
     * Shared emails across departments:
     * - ravi.ranjan@company.com in PD and MG
     * - ishaq@company.com in PD and HR
     */
    public static Map<String, List<Employee>> sampleDepartmentData() {
        Map<String, List<Employee>> deptMap = new HashMap<String, List<Employee>>();

        deptMap.put("PD", Arrays.asList(
                new Employee(1, "Ravi Ranjan", "ravi.ranjan@company.com", "PD", 3000),
                new Employee(2, "Shaik Ishaq", "ishaq@company.com", "PD", 2000),
                new Employee(6, "Neha", "neha@company.com", "PD", 3500)
        ));

        deptMap.put("HR", Arrays.asList(
                new Employee(3, "Anjieny", "anjieny@company.com", "HR", 40000),
                new Employee(4, "Santosh", "santosh@company.com", "HR", 1500),
                new Employee(7, "Shaik Ishaq", "ishaq@company.com", "HR", 2200) // same email as PD
        ));

        deptMap.put("MG", Arrays.asList(
                new Employee(5, "Amit", "amit@company.com", "MG", 6000),
                new Employee(8, "Ravi Ranjan", "ravi.ranjan@company.com", "MG", 5000) // same email as PD
        ));

        return deptMap;
    }

    /** Helper pair for distinct-department counting. */
    private static final class DeptEmail {
        private final String department;
        private final String email;

        private DeptEmail(String department, String email) {
            this.department = department;
            this.email = email;
        }

        private String getDepartment() {
            return department;
        }

        private String getEmail() {
            return email;
        }
    }
}
