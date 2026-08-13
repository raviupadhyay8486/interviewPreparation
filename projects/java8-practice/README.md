# Java 8 Practice

Small runnable examples for Streams / flatMap interview practice.

## Emails in multiple departments

**Task:** From departments, collect unique employee emails that appear in more than one department.

### Files
- `src/java8/streams/Employee.java`
- `src/java8/streams/EmailsInMultipleDepartments.java`

### Run

```bash
cd projects/java8-practice
javac -d out src/java8/streams/*.java
java -cp out java8.streams.EmailsInMultipleDepartments
```

### Sample expected emails
- `ravi.ranjan@company.com` (PD + MG)
- `ishaq@company.com` (PD + HR)
