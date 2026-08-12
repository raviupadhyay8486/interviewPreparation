# Java 8 Streams — flatMap, Collectors, reduce vs collect, IntStream, Parallel Streams

**Purpose:** Save-and-reuse interview + practice notes.  
**Focus:** Best `flatMap` examples and **complex / tricky** problems.  
**Style:** Concept → simple example → tricky problem → solution idea.

---

# PART A — flatMap (deep dive)

## 1. What is flatMap?

| | `map` | `flatMap` |
|---|--------|-----------|
| Idea | 1 element → 1 element | 1 element → **0..n elements**, then **flatten** |
| Return type of function | `R` | `Stream<R>` |
| Shape | `Stream<A>` → `Stream<B>` | `Stream<A>` → `Stream<B>` (flattened) |

**Mental model:**  
`map` = transform  
`flatMap` = transform **to a stream** + **concatenate** all those streams into one

```text
[["a","b"], ["c"]]
  map(list -> list)     → Stream<List<String>>   still nested
  flatMap(list -> list.stream()) → Stream<String>  a,b,c
```

---

## 2. Best basic examples

### Example 1 — Flatten list of lists

```java
List<List<String>> nested = Arrays.asList(
    Arrays.asList("Java", "Spring"),
    Arrays.asList("React", "AWS"),
    Arrays.asList("Kafka")
);

List<String> flat = nested.stream()
    .flatMap(List::stream)
    .collect(Collectors.toList());

// [Java, Spring, React, AWS, Kafka]
```

### Example 2 — Split sentences into words

```java
List<String> lines = Arrays.asList(
    "mortgage record change",
    "lender account number"
);

List<String> words = lines.stream()
    .flatMap(line -> Arrays.stream(line.split("\\s+")))
    .collect(Collectors.toList());

// [mortgage, record, change, lender, account, number]
```

### Example 3 — Optional-like flattening (Java 8 pattern)

When a method returns `List` that may be empty:

```java
List<Employee> employees = ...;

List<String> projectNames = employees.stream()
    .flatMap(e -> e.getProjects().stream()) // each employee → stream of projects
    .map(Project::getName)
    .distinct()
    .collect(Collectors.toList());
```

### Example 4 — flatMap vs map trap

```java
List<String> names = Arrays.asList("A|B", "C|D");

// map → Stream<String[]>  (NOT flattened)
Stream<String[]> mapped = names.stream().map(s -> s.split("\\|"));

// flatMap → Stream<String>
List<String> flat = names.stream()
    .flatMap(s -> Arrays.stream(s.split("\\|")))
    .collect(Collectors.toList());
// [A, B, C, D]
```

### Example 5 — flatMapToInt (primitive)

```java
int sumOfDigits = Arrays.asList("12", "34").stream()
    .flatMapToInt(s -> s.chars().map(c -> c - '0'))
    .sum();
// 1+2+3+4 = 10
```

---

## 3. Complex / tricky flatMap problems (save these)

### Problem 1 — Multi-level nest (Lender → Branch → Policy) ⭐⭐⭐

**Domain (MRCS-style):**

```java
class Policy { String policyNumber; String zip; }
class Branch { String branchCode; List<Policy> policies; }
class Lender { String name; List<Branch> branches; }
```

**Task:** Get all policy numbers for lenders in state `"IL"` whose zip starts with `"617"`.

```java
List<String> policyNumbers = lenders.stream()
    .filter(l -> "IL".equals(l.getState()))
    .flatMap(l -> l.getBranches().stream())
    .flatMap(b -> b.getPolicies().stream())
    .filter(p -> p.getZip() != null && p.getZip().startsWith("617"))
    .map(Policy::getPolicyNumber)
    .distinct()
    .collect(Collectors.toList());
```

**Trick:** You need **two** `flatMap`s for 3-level nesting.  
`map` would leave `Stream<Stream<...>>`.

---

### Problem 2 — flatMap + filter duplicates across groups ⭐⭐⭐

**Task:** From departments, collect unique employee emails that appear in **more than one** department.

```java
Map<String, List<Employee>> deptMap = ...; // dept -> employees

Set<String> emailsInMultipleDepts = deptMap.values().stream()
    .flatMap(List::stream)
    .collect(Collectors.groupingBy(Employee::getEmail, Collectors.counting()))
    .entrySet().stream()
    .filter(e -> e.getValue() > 1)
    .map(Map.Entry::getKey)
    .collect(Collectors.toSet());
```

**Trick:** First flatten all employees from all dept lists, then groupBy count.

---

### Problem 3 — Cartesian product with flatMap ⭐⭐⭐⭐

**Task:** All `(accountNumber, region)` pairs.

```java
List<String> accounts = Arrays.asList("A1", "A2");
List<String> regions = Arrays.asList("N", "S");

List<String> pairs = accounts.stream()
    .flatMap(a -> regions.stream().map(r -> a + "-" + r))
    .collect(Collectors.toList());
// [A1-N, A1-S, A2-N, A2-S]
```

**Trick:** Inner `map` builds pairs; outer `flatMap` flattens `Stream<Stream<String>>` idea into one stream.

Interview follow-up: complexity is O(n*m).

---

### Problem 4 — flatMap to remove null/empty safely ⭐⭐⭐

```java
List<String> raw = Arrays.asList("AB", null, "", "CD");

List<String> cleaned = raw.stream()
    .flatMap(s -> {
        if (s == null || s.isEmpty()) return Stream.empty();
        return Stream.of(s);
    })
    .collect(Collectors.toList());
// [AB, CD]
```

Java 9+ has `Stream.ofNullable`; in **Java 8** use `Stream.empty()` / `Stream.of(...)`.

---

### Problem 5 — Tricky: map returns Stream by mistake ⭐⭐⭐⭐

```java
// BUG interview question: what is the type / output?
List<List<String>> data = Arrays.asList(
    Arrays.asList("x", "y"),
    Arrays.asList("z")
);

// This compiles but result is List<Stream<String>>  (!!)
List<Stream<String>> wrong = data.stream()
    .map(List::stream)
    .collect(Collectors.toList());

// Correct
List<String> right = data.stream()
    .flatMap(List::stream)
    .collect(Collectors.toList());
```

**Trick question:** “Why didn’t map flatten?”  
Because `map` preserves structure of what the function returns.

---

### Problem 6 — flatMap with Map values ⭐⭐⭐

```java
Map<String, List<Integer>> deptSalaries = new HashMap<>();
deptSalaries.put("IT", Arrays.asList(100, 200, 300));
deptSalaries.put("HR", Arrays.asList(90, 110));

IntSummaryStatistics stats = deptSalaries.values().stream()
    .flatMap(List::stream)
    .mapToInt(Integer::intValue)
    .summaryStatistics();

// count, sum, min, avg, max across ALL departments
```

---

### Problem 7 — Second-level grouping after flatten ⭐⭐⭐⭐

**Task:** Among all policies of all lenders, group by zip and list lender names (distinct).

```java
Map<String, Set<String>> zipToLenders = lenders.stream()
    .flatMap(lender -> lender.getBranches().stream()
        .flatMap(branch -> branch.getPolicies().stream()
            .map(policy -> new AbstractMap.SimpleEntry<>(policy.getZip(), lender.getName()))
        )
    )
    .collect(Collectors.groupingBy(
        Map.Entry::getKey,
        Collectors.mapping(Map.Entry::getValue, Collectors.toSet())
    ));
```

**Trick:** Nested `flatMap` + create pair(zip, lenderName) + `groupingBy` + `mapping(..., toSet)`.

---

### Problem 8 — flatMap vs reduce trap ⭐⭐⭐⭐

**Bad (quadratic / hard to read):** concatenating lists with reduce

```java
List<String> bad = listOfLists.stream()
    .reduce(new ArrayList<>(), (a, b) -> {
        a.addAll(b);
        return a;
    }, (a, b) -> { a.addAll(b); return a; });
```

**Good:**

```java
List<String> good = listOfLists.stream()
    .flatMap(List::stream)
    .collect(Collectors.toList());
```

Interview point: prefer `flatMap + collect` over mutating `reduce` for list concatenation.

---

### Problem 9 — Character frequency using flatMapToInt ⭐⭐⭐

```java
String text = "mortgage";

Map<Character, Long> freq = text.chars()                 // IntStream of code points
    .mapToObj(c -> (char) c)
    .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
```

Or words → chars:

```java
Map<Character, Long> letterFreq = Arrays.asList("java", "stream").stream()
    .flatMapToInt(String::chars)
    .mapToObj(c -> (char) c)
    .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
```

---

### Problem 10 — Interview killer: order after flatMap ⭐⭐⭐⭐

```java
List<String> result = Arrays.asList(
    Arrays.asList("b", "a"),
    Arrays.asList("c")
).stream()
 .flatMap(List::stream)
 .sorted()
 .collect(Collectors.toList());
// [a, b, c]
```

Without `sorted()`, encounter order is **concat order**: `b,a,c`.  
Parallel + unordered may not preserve that — don’t assume order in parallel `flatMap` unless documented carefully.

---

# PART B — reduce vs collect

## Concept

| | `reduce` | `collect` |
|---|----------|-----------|
| Style | Immutable fold to one value | Mutable reduction via container |
| Typical result | sum, max, concatenated string (careful) | List, Set, Map, summary |
| Parallel | Needs associative combiner | Collectors designed for this |
| Prefer for collections | Usually **no** | **Yes** (`Collectors.toList()` etc.) |

### reduce example

```java
Optional<Integer> max = salaries.stream().reduce(Integer::max);
int sum = salaries.stream().reduce(0, Integer::sum);
```

### collect example

```java
List<Integer> list = salaries.stream().collect(Collectors.toList());
```

### Tricky: reduce to List (anti-pattern)

```java
// Works but not idiomatic / can be buggy if not careful with identity mutability
List<String> viaReduce = stream.reduce(
    new ArrayList<>(),
    (list, item) -> { list.add(item); return list; },
    (a, b) -> { a.addAll(b); return a; }
);
```

**Prefer:** `.collect(Collectors.toList())`

---

# PART C — Collectors cheat sheet (best examples)

## toList / toSet

```java
List<String> names = emp.stream().map(Employee::getName).collect(Collectors.toList());
Set<String> depts = emp.stream().map(Employee::getDept).collect(Collectors.toSet());
```

## toMap

```java
Map<String, Integer> accountToSalary = emp.stream()
    .collect(Collectors.toMap(
        Employee::getAccountNumber,
        Employee::getSalary,
        (oldV, newV) -> newV  // merge function — REQUIRED if duplicate keys possible
    ));
```

**Tricky:** Without merge function, duplicate keys → `IllegalStateException`.

## groupingBy

```java
Map<String, List<Employee>> byDept =
    emp.stream().collect(Collectors.groupingBy(Employee::getDept));

Map<String, Long> countByDept =
    emp.stream().collect(Collectors.groupingBy(Employee::getDept, Collectors.counting()));

Map<String, Optional<Employee>> maxSalByDept =
    emp.stream().collect(Collectors.groupingBy(
        Employee::getDept,
        Collectors.maxBy(Comparator.comparing(Employee::getSalary))
    ));
```

## partitioningBy (always 2 groups: true/false)

```java
Map<Boolean, List<Employee>> partitioned =
    emp.stream().collect(Collectors.partitioningBy(e -> e.getSalary() >= 100000));

List<Employee> high = partitioned.get(true);
List<Employee> low = partitioned.get(false);
```

## joining

```java
String csv = emp.stream()
    .map(Employee::getName)
    .collect(Collectors.joining(", ", "[", "]"));
// [Ravi, Amit, Neha]
```

---

## Complex collector problem — second highest salary by dept ⭐⭐⭐⭐

```java
Map<String, Optional<Integer>> secondHighestByDept = employees.stream()
    .collect(Collectors.groupingBy(
        Employee::getDept,
        Collectors.collectingAndThen(
            Collectors.mapping(Employee::getSalary, Collectors.toSet()),
            salaries -> salaries.stream()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst()
        )
    ));
```

**Note:** Your earlier `maxBy` code was **highest**, not second highest.

---

# PART D — Primitive streams (IntStream basics)

```java
IntStream.range(1, 5).forEach(System.out::print);        // 1234
IntStream.rangeClosed(1, 5).forEach(System.out::print);  // 12345

int sum = employees.stream()
    .mapToInt(Employee::getSalary)
    .sum();

OptionalDouble avg = employees.stream()
    .mapToInt(Employee::getSalary)
    .average();

IntSummaryStatistics stats = employees.stream()
    .mapToInt(Employee::getSalary)
    .summaryStatistics();
```

**Why primitives?** Avoid boxing (`Integer`) → less memory/GC for big numeric pipelines.

```java
// boxed back when you need Stream<Integer>
List<Integer> list = IntStream.rangeClosed(1, 3).boxed().collect(Collectors.toList());
```

---

# PART E — Parallel streams — when NOT to use

## When NOT to use (interview gold)

1. **Small lists** — thread overhead > benefit  
2. **Shared mutable state** — race bugs (`list.add` inside `forEach`)  
3. **Order-sensitive logic** where you need stable encounter order (unless careful)  
4. **Heavy blocking I/O** per element (DB/API calls) — use async/reactive or bounded pools instead  
5. **Costly merge / poor splittability**  
6. **Already inside a small request thread** in high-traffic services without measuring  

## Bad example

```java
List<String> target = new ArrayList<>(); // NOT thread-safe
list.parallelStream().forEach(target::add); // DATA RACE / lost elements / CME risk
```

## Better

```java
List<String> target = list.parallelStream().collect(Collectors.toList());
```

## Acceptable use

```java
long count = hugeList.parallelStream()
    .filter(this::cpuHeavyPureFunction)
    .count();
```

**Rule:** Measure. Prefer sequential by default; parallel only for large CPU-bound, side-effect-free ops.

---

# PART F — flatMap practice sheet (solve without looking)

1. Flatten `List<List<Integer>>` and find unique sorted values.  
2. From `List<String>` sentences, find distinct words longer than 4.  
3. Employee → List\<Skill\>; find all unique skills containing `"Java"`.  
4. Cartesian product of colors × sizes.  
5. Map\<Dept, List\<Emp\>\> → top 3 salaries overall.  
6. Nested lender/branch/policy → `Map<zip, List<policyNumber>>`.  
7. Explain why `map(List::stream)` is wrong for flatten.  
8. Fix parallel `forEach(add)` bug.  
9. Convert `Stream<String>` account numbers to `IntStream` of lengths and average.  
10. Build CSV of distinct cities using `flatMap` + `joining`.

### Sample solutions sketch

```java
// 1
List<Integer> uniqueSorted = nested.stream()
    .flatMap(List::stream).distinct().sorted().collect(Collectors.toList());

// 2
Set<String> words = sentences.stream()
    .flatMap(s -> Arrays.stream(s.split("\\s+")))
    .filter(w -> w.length() > 4)
    .collect(Collectors.toSet());

// 5
List<Integer> top3 = deptMap.values().stream()
    .flatMap(List::stream)
    .map(Employee::getSalary)
    .sorted(Comparator.reverseOrder())
    .limit(3)
    .collect(Collectors.toList());
```

---

# PART G — 15 interview Q&A (quick)

1. **map vs flatMap?** map 1→1; flatMap 1→stream then flatten.  
2. **When flatMap?** nested collections, split tokens, optional empty streams, cartesian.  
3. **flatMap function must return?** `Stream` (or primitive stream variant).  
4. **reduce vs collect?** collect for mutable containers; reduce for values.  
5. **Why merge in toMap?** duplicate keys.  
6. **groupingBy vs partitioningBy?** partitioningBy only boolean 2 buckets.  
7. **joining use case?** CSV / path concatenation.  
8. **Why IntStream?** avoid boxing.  
9. **range vs rangeClosed?** end exclusive vs inclusive.  
10. **Parallel default?** no — measure first.  
11. **Why parallel forEach(add) fails?** shared mutable non-concurrent list.  
12. **Can flatMap remove elements?** yes via `Stream.empty()`.  
13. **Two-level nest needs?** usually two flatMaps.  
14. **map(List::stream) type?** `Stream<Stream<T>>` conceptually via `Stream` elements.  
15. **Best flatten idiom?** `.flatMap(Collection::stream)`.

---

# PART H — One “best” copy-paste demo (all topics)

```java
import java.util.*;
import java.util.stream.*;

public class FlatMapCollectorsDemo {
    static class Emp {
        String name, dept; int salary; List<String> skills;
        Emp(String n, String d, int s, List<String> sk) {
            name = n; dept = d; salary = s; skills = sk;
        }
    }

    public static void main(String[] args) {
        List<Emp> list = Arrays.asList(
            new Emp("Ravi", "IT", 120, Arrays.asList("Java", "Spring")),
            new Emp("Neha", "IT", 140, Arrays.asList("Java", "Kafka")),
            new Emp("Amit", "HR", 90, Arrays.asList("Comm")),
            new Emp("Isha", "HR", 110, Arrays.asList("Java", "Excel"))
        );

        // flatMap skills
        Set<String> skills = list.stream()
            .flatMap(e -> e.skills.stream())
            .collect(Collectors.toSet());
        System.out.println("skills=" + skills);

        // groupingBy + mapping
        Map<String, List<String>> namesByDept = list.stream()
            .collect(Collectors.groupingBy(
                e -> e.dept,
                Collectors.mapping(e -> e.name, Collectors.toList())
            ));
        System.out.println("namesByDept=" + namesByDept);

        // partitioningBy
        Map<Boolean, List<Emp>> part = list.stream()
            .collect(Collectors.partitioningBy(e -> e.salary >= 110));
        System.out.println("highCount=" + part.get(true).size());

        // joining
        String csv = list.stream().map(e -> e.name).collect(Collectors.joining(","));
        System.out.println("csv=" + csv);

        // IntStream
        IntSummaryStatistics st = list.stream().mapToInt(e -> e.salary).summaryStatistics();
        System.out.println("sum=" + st.getSum() + ", avg=" + st.getAverage());

        // reduce
        int sumReduce = list.stream().map(e -> e.salary).reduce(0, Integer::sum);
        System.out.println("sumReduce=" + sumReduce);

        // second highest overall via flatMap not needed, but distinct salaries:
        Optional<Integer> second = list.stream()
            .map(e -> e.salary)
            .distinct()
            .sorted(Comparator.reverseOrder())
            .skip(1)
            .findFirst();
        System.out.println("secondHighest=" + second.orElse(null));
    }
}
```

---

## Related docs
- Concept Q&A: [`java8-interview-qa.md`](./java8-interview-qa.md)  
- Day-by-day plan: [`java8-day-by-day.md`](./java8-day-by-day.md)  
- Hard challenges: [`java8-topics-questions-challenges.md`](./java8-topics-questions-challenges.md)

**Save this file and practice Problems 1–10 weekly before interviews.**
