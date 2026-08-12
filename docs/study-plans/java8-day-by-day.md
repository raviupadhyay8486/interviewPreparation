# Java 1.8 — Topic-wise Day-by-Day Study Plan

**Goal:** Master Java 8 fundamentals for interviews (aligned with your Spring Boot / microservices resume).  
**Pace:** 1 day = 1–2 focused topics · ~1.5–2.5 hours/day  
**Duration:** **21 days** (3 weeks) · then revise weak days  

**How to study each day**
1. Read/notes — 40 min  
2. Code 2–3 small programs — 40–60 min  
3. Answer day’s questions out loud — 20–30 min  
4. Mark: 🟢 Done · 🟡 Weak · 🔴 Redo  

---

## Week 1 — Core Java foundation (Days 1–7)

### Day 1 — Java basics & OOP pillars
**Topics**
- JVM / JRE / JDK
- Class, object, package
- Encapsulation, Inheritance, Polymorphism, Abstraction
- Access modifiers (`private`, `default`, `protected`, `public`)

**Practice**
- Model `Lender` / `Policy` classes with encapsulation
- Override `toString`

**Questions**
1. JVM vs JRE vs JDK?
2. What is encapsulation? Give an example.
3. Compile-time vs runtime polymorphism?
4. Can a class be private? Explain access levels.

---

### Day 2 — Inheritance, abstract class, interface (pre–Java 8 + Java 8 start)
**Topics**
- `extends` vs `implements`
- Abstract class vs interface
- Method overriding rules
- `super`, constructor chaining

**Practice**
- `PaymentService` abstract class + implementations
- Interface `Searchable` for lender search

**Questions**
1. Abstract class vs interface (before and after Java 8)?
2. Can constructor be abstract?
3. Rules of overriding (return type, access, exceptions)?
4. Multiple inheritance problem in Java — how interfaces help?

---

### Day 3 — Java 8 interfaces: default & static methods
**Topics**
- `default` methods in interfaces
- `static` methods in interfaces
- Diamond problem with default methods
- Functional interface preview (`@FunctionalInterface`)

**Practice**
- Interface with `default` logging helper + `static` factory-style helper
- Resolve diamond conflict with override

**Questions**
1. Why were default methods added in Java 8?
2. Can default methods be overridden?
3. Interface static method — how to call? Can it be overridden?
4. What happens if two interfaces have same default method?

---

### Day 4 — Object class contracts: equals, hashCode, toString
**Topics**
- `equals` / `hashCode` contract
- `==` vs `equals`
- `Comparable` vs `Comparator` (intro)
- Cloning / immutability intro (shallow vs deep — high level)

**Practice**
- Proper `equals`/`hashCode` for `Lender(accountNumber)`
- Sort lenders with `Comparator`

**Questions**
1. Why override both `equals` and `hashCode`?
2. What breaks in `HashMap` if `hashCode` is wrong?
3. `Comparable` vs `Comparator`?
4. Is it safe to use mutable fields in `hashCode`?

---

### Day 5 — String, StringBuilder, StringBuffer
**Topics**
- String immutability & String pool
- `StringBuilder` vs `StringBuffer`
- Common APIs: `substring`, `split`, `intern` (careful), formatting

**Practice**
- Validate 10-char alphanumeric account number
- Build CSV of policy numbers with `StringBuilder`

**Questions**
1. Why is `String` immutable?
2. `StringBuilder` vs `StringBuffer`?
3. What is String pool?
4. How many objects created for `String s = new String("abc")`?

---

### Day 6 — Exceptions & try-with-resources
**Topics**
- Checked vs unchecked
- `throw` / `throws` / `try-catch-finally`
- Custom exceptions
- try-with-resources (AutoCloseable)

**Practice**
- `InvalidAccountNumberException`
- Read a small file with try-with-resources (or simulate AutoCloseable)

**Questions**
1. Checked vs unchecked — when to use each?
2. Does `finally` always run?
3. try-with-resources vs manual `close()`?
4. Exception wrapping — why use cause?

---

### Day 7 — Week 1 revision + mini test
**Topics**
- Redo 🟡/🔴 items from Days 1–6
- Flash quiz: 15 random questions from Week 1

**Practice**
- Write a small CLI: create lenders, search by account, handle invalid input

**Checkpoint**
- [ ] Explain OOP + Java 8 interface defaults without notes  
- [ ] Correct `equals`/`hashCode`  
- [ ] Exception design for APIs  

---

## Week 2 — Collections, Generics, Generics+Collections (Days 8–14)

### Day 8 — List implementations
**Topics**
- `ArrayList` vs `LinkedList` vs `Vector`
- Iteration: for, for-each, Iterator, ListIterator
- Fail-fast behavior

**Practice**
- Insert/delete benchmarks (mental or small timing)
- Remove elements safely while iterating

**Questions**
1. ArrayList vs LinkedList — time complexity?
2. What is fail-fast?
3. How to remove while iterating without CME?
4. When is `Vector` still relevant (rarely)?

---

### Day 9 — Set implementations
**Topics**
- `HashSet`, `LinkedHashSet`, `TreeSet`
- Uniqueness via `equals`/`hashCode`
- Sorting with `TreeSet` + Comparator

**Practice**
- Deduplicate account numbers preserving order (`LinkedHashSet`)
- Sorted unique city names (`TreeSet`)

**Questions**
1. HashSet vs TreeSet?
2. How does HashSet use HashMap internally?
3. Can TreeSet hold null? (Java 8 behavior)
4. LinkedHashSet use case?

---

### Day 10 — Map & HashMap internals (Java 8)
**Topics**
- `HashMap`, `LinkedHashMap`, `TreeMap`, `Hashtable`
- Hashing, buckets, collision
- Java 8: linked list → balanced tree (TreeBin) when bin grows
- Load factor & rehashing

**Practice**
- Frequency map of characters / words
- Group policies by zip (`Map<String, List<Policy>>`) without Streams first

**Questions**
1. Explain HashMap put/get in Java 8.
2. What is load factor (default 0.75)?
3. HashMap vs Hashtable vs ConcurrentHashMap (intro)?
4. Why key should be immutable?

---

### Day 11 — Concurrent collections (interview essentials)
**Topics**
- `ConcurrentHashMap` (Java 8 compute methods awareness)
- `CopyOnWriteArrayList` (when to use)
- Fail-safe vs fail-fast
- Simple thread-safety rules for collections

**Practice**
- Concurrent map counting with `compute` / `merge` (Java 8)

**Questions**
1. Why ConcurrentHashMap over synchronized HashMap?
2. Is ConcurrentHashMap fully lock-free?
3. CopyOnWriteArrayList — read-heavy use case?
4. Can Iterator of ConcurrentHashMap throw CME?

---

### Day 12 — Generics
**Topics**
- Type parameters, bounded types
- Wildcards: `?`, `? extends`, `? super`
- PECS (Producer Extends, Consumer Super)
- Type erasure

**Practice**
- Generic `ApiResponse<T>`
- Method that copies `List<? extends Number>` to `List<? super Number>`

**Questions**
1. What is type erasure?
2. Explain PECS with example.
3. `List<Object>` vs `List<?>`?
4. Can you create `new T[]`? Why not?

---

### Day 13 — Comparable, Comparator, sorting patterns
**Topics**
- `Comparable.compareTo`
- `Comparator` (anonymous, then Java 8 lambda preview)
- Multi-field sort
- `Collections.sort` / `List.sort`

**Practice**
- Sort lenders by name then account number
- Null-safe comparator discussion

**Questions**
1. When implement Comparable vs pass Comparator?
2. What does compareTo returning negative/zero/positive mean?
3. How to sort descending?
4. Consistency with equals — why it matters for TreeSet?

---

### Day 14 — Week 2 revision + mini test
**Topics**
- HashMap internals redraw from memory
- Collections cheat-sheet (when to use what)

**Practice**
- Implement simple LRU idea with `LinkedHashMap` (removeEldestEntry)

**Checkpoint**
- [ ] Explain HashMap Java 8 collisions  
- [ ] Choose correct collection for a scenario  
- [ ] Generics wildcards / PECS  

---

## Week 3 — Java 8 features + concurrency + JVM (Days 15–21)

### Day 15 — Lambda expressions
**Topics**
- Lambda syntax
- Target typing
- Effectively final variables
- Lambda vs anonymous class (`this`, variables)

**Practice**
- Replace anonymous `Comparator` with lambda
- Custom functional-style callbacks

**Questions**
1. What is a lambda expression?
2. What does “effectively final” mean?
3. Lambda vs anonymous inner class differences?
4. Can lambda throw checked exceptions easily? (wrapping patterns)

---

### Day 16 — Functional interfaces (java.util.function)
**Topics**
- `Predicate<T>`, `Function<T,R>`, `Consumer<T>`, `Supplier<T>`
- `BiFunction`, `BiPredicate`, `UnaryOperator`, `BinaryOperator`
- Chaining: `andThen`, `compose`, `and` / `or` / `negate`

**Practice**
- Account validation `Predicate`
- DTO mapper `Function<Entity, Dto>`
- Pipeline: validate → transform → consume

**Questions**
1. What is a functional interface? Rules?
2. Predicate vs Function?
3. Supplier use cases?
4. Why `@FunctionalInterface`?

---

### Day 17 — Method references & Optional
**Topics**
- Method references: static, instance, arbitrary instance, constructor
- `Optional` creation: `of`, `ofNullable`, `empty`
- `map`, `flatMap`, `filter`, `orElse`, `orElseGet`, `orElseThrow`
- Anti-patterns: `Optional` as field/parameter, `get()` without check

**Practice**
- Safe null handling for lender lookup returning `Optional<Lender>`
- Refactor nested null checks

**Questions**
1. Types of method references?
2. `orElse` vs `orElseGet`?
3. `Optional.map` vs `flatMap`?
4. Why not use Optional as method parameter?

---

### Day 18 — Streams API (Part 1): basics
**Topics**
- Create streams: collection, array, `Stream.of`, `Stream.builder`
- Intermediate vs terminal operations
- Laziness
- `filter`, `map`, `distinct`, `sorted`, `limit`, `skip`
- Terminal: `forEach`, `collect`, `count`, `anyMatch`, `findFirst`

**Practice**
- Filter policies by zip; map to policy numbers; collect to List/Set
- Account number search filter (length 10 alphanumeric)

**Questions**
1. Intermediate vs terminal ops?
2. Are streams reusable?
3. What is lazy evaluation?
4. `findFirst` vs `findAny`?

---

### Day 19 — Streams API (Part 2): advanced
**Topics**
- `flatMap`
- `reduce` vs `collect`
- Collectors: `toList`, `toSet`, `toMap`, `groupingBy`, `partitioningBy`, `joining`
- Primitive streams: `IntStream` (basics)
- Parallel streams — when not to use

**Practice**
- `groupingBy` city → list of lenders
- Flatten `List<List<Policy>>` with `flatMap`
- Sum loan-related numeric field with `reduce` / `mapToInt`

**Questions**
1. `map` vs `flatMap`?
2. `reduce` vs `collect`?
3. How does `groupingBy` work?
4. Parallel stream risks (shared mutable state, ordering)?

---

### Day 20 — Concurrency basics for interviews
**Topics**
- Thread states, `Runnable` vs `Callable`
- `ExecutorService`, thread pools
- `synchronized`, `volatile` (basics)
- Deadlock (cause + prevention)
- Intro `CompletableFuture` (Java 8) — supplyAsync / thenApply / thenCombine

**Practice**
- Run 3 fake “API calls” with `CompletableFuture` and combine
- Demonstrate race condition then fix with concurrent map / sync

**Questions**
1. Runnable vs Callable?
2. How to size thread pools (CPU vs I/O)?
3. volatile vs synchronized?
4. What is deadlock?
5. Why CompletableFuture for microservice fan-out?

---

### Day 21 — JVM memory + Java 8→11 awareness + final revision
**Topics**
- Stack vs heap
- GC overview (Young/Old) — interview level
- Class loading basics (high level)
- Why teams upgrade Java 8 → 11 (your Ancestry story)
- Full Week 3 flash revision

**Practice**
- 30-question mixed mock (self or friend)
- Re-code: Streams grouping + Optional service lookup + HashMap frequency

**Questions**
1. Stack vs heap?
2. Common memory leak causes in Java?
3. OutOfMemoryError — what next in production?
4. Benefits of Java 11 over 8 for production services?
5. Explain your strongest 5 Java 8 topics in 5 minutes.

**Final checkpoint**
- [ ] Lambdas + functional interfaces fluent  
- [ ] Streams grouping/flatMap without panic  
- [ ] Optional used correctly  
- [ ] HashMap + collections selection solid  
- [ ] Concurrency basics explainable  
- [ ] Can connect answers to resume (Ancestry upgrade, API validation, etc.)  

---

## Day-by-day topic cheat sheet (quick view)

| Day | Topic |
|-----|--------|
| 1 | OOP pillars, JDK/JRE/JVM |
| 2 | Inheritance, abstract class, interface |
| 3 | Java 8 `default` / `static` interface methods |
| 4 | equals, hashCode, Comparable intro |
| 5 | String, StringBuilder, StringBuffer |
| 6 | Exceptions, try-with-resources |
| 7 | Week 1 revision |
| 8 | List (`ArrayList`, `LinkedList`) |
| 9 | Set (`HashSet`, `LinkedHashSet`, `TreeSet`) |
| 10 | Map + HashMap internals (Java 8) |
| 11 | Concurrent collections |
| 12 | Generics & wildcards (PECS) |
| 13 | Comparable, Comparator, sorting |
| 14 | Week 2 revision + LRU |
| 15 | Lambdas |
| 16 | Functional interfaces |
| 17 | Method references + Optional |
| 18 | Streams basics |
| 19 | Streams advanced (flatMap, collectors, parallel) |
| 20 | Concurrency + CompletableFuture |
| 21 | JVM memory + full revision |

---

## Resume connection (use in answers)

| Day topics | Tie to your experience |
|------------|----------------------|
| Validation Predicates / Streams | MRCS account number & form rules |
| Optional, DTOs mindset | Clean API responses / null-safe service layers |
| HashMap / Collections | Caching, grouping search results |
| CompletableFuture / concurrency | Async calls / high throughput services |
| Java 8 → modern Java | Ancestry Java 8 → 11 upgrade story |
| Exceptions | REST/microservice error design (MGM, Xebia APIs) |

---

## Tracking tracker

| Week | Status |
|------|--------|
| Week 1 (Days 1–7) | ☐ |
| Week 2 (Days 8–14) | ☐ |
| Week 3 (Days 15–21) | ☐ |

**After Day 21:** move to Spring Boot week in [`java8-springboot-study-plan.md`](./java8-springboot-study-plan.md).

---

## Optional stretch (if a day finishes early)

- Write unit tests with JUnit for that day’s code  
- Solve 1–2 coding problems (account validate, group-by, top-N using Heap/`PriorityQueue`)  
- Teach the topic in 5 minutes (rubber-duck / voice note)
