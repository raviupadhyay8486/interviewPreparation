# Java 1.8 Study Plan — Topics + Interview Questions + Hard Programming Challenges

**For:** Interview prep (Technical Lead / Java Full Stack)  
**Style:** Each topic = concepts → interview Qs → **hard coding challenge(s)**  
**Rule:** Solve challenges in **Java 8 style** (lambdas, Streams, Optional, `CompletableFuture` where relevant).  
**Companion:** Day-by-day schedule → [`java8-day-by-day.md`](./java8-day-by-day.md) · Combined Spring Boot plan → [`java8-springboot-study-plan.md`](./java8-springboot-study-plan.md)

### How to use
1. Study the topic (30–45 min)  
2. Answer interview questions out loud (15–20 min)  
3. Solve the hard challenge without IDE autocomplete if possible (45–90 min)  
4. Refactor using Java 8 APIs; add edge-case tests  

**Difficulty legend:** ⭐⭐ hard · ⭐⭐⭐ very hard · ⭐⭐⭐⭐ expert/interview-killer  

---

# TOPIC 1 — OOP, Classes, Interfaces (Java 8 defaults)

## Study
- Encapsulation, inheritance, polymorphism, abstraction  
- Abstract class vs interface  
- Java 8: `default` / `static` interface methods, diamond resolution  

## Interview questions
1. Abstract class vs interface in Java 8?  
2. Why default methods? Can they break binary compatibility?  
3. How is diamond problem resolved with two default methods?  
4. Overriding rules (return type, access, exceptions)?  
5. Composition vs inheritance — when do you refuse `extends`?  

## Hard challenges
### C1. Plugin fee calculator (⭐⭐⭐)
Design a loan fee engine:
- Interface `FeeRule` with `default` method `supports(ChangeType)` and `double calculate(Policy ctx)`  
- Multiple rules: flat fee, % of loan, zip-based surcharge  
- Conflict: two interfaces both define `default String name()` — resolve in implementing class  
- Add `static FeeRule chain(FeeRule...)` that applies rules in order  

**Constraints:** No Spring; pure Java 8. Support at least 4 rule types.  

### C2. Immutable hierarchy (⭐⭐)
Create immutable `Lender` / `Policy` with builders; subclasses cannot break immutability. Prove with attempted mutation tests.

---

# TOPIC 2 — equals / hashCode / Comparable / Comparator

## Study
- equals/hashCode contract  
- Consistency with `TreeSet` / `HashMap`  
- `Comparable` vs `Comparator`; multi-field sort  

## Interview questions
1. Why override both equals and hashCode?  
2. What breaks in HashMap if hashCode changes after insert?  
3. Comparable vs Comparator?  
4. Can TreeSet violate equals symmetry if compareTo disagrees with equals?  
5. How do you sort on name ASC then account DESC?  

## Hard challenges
### C3. Stable domain identity (⭐⭐⭐)
`Lender` identity = `accountNumber` (case-insensitive).  
`Policy` identity = `policyNumber + zip`.  
Put mixed objects into `HashSet` and `TreeSet` with different sort orders.  
**Must:** no duplicates by business key; TreeSet order by name then account; demonstrate equals/compareTo consistency pitfalls and fix them.

### C4. Interval scheduling comparator (⭐⭐⭐)
Given meeting intervals `[start,end)`, merge overlaps and sort by start then longest duration using only `Comparator` + Java 8 (`List.sort`, streams).

---

# TOPIC 3 — String & Text processing

## Study
- Immutability, pool  
- `StringBuilder`  
- Regex basics  

## Interview questions
1. Why String is immutable?  
2. StringBuilder vs StringBuffer?  
3. How many objects: `String a = new String("x"); String b = "x";`?  
4. When is regex expensive? Alternatives?  

## Hard challenges
### C5. Account number Normalizer + Validator (⭐⭐⭐) — MRCS-style
Input may contain spaces/dashes. Normalize to exactly **10 alphanumeric**.  
Rules:
- Reject if letters+digits length ≠ 10 after normalize  
- Reject if contains other symbols  
- Batch validate `List<String>` → `Map<String, List<String>>` with keys `VALID` / `INVALID` using Streams  
- Also return first invalid reason via custom exception with suppressed exceptions for batch mode  

### C6. Mini fuzzy match (⭐⭐⭐⭐)
Given lender name queries, rank candidates by:
- case-insensitive contains  
- token overlap score  
- edit distance ≤ 2 (implement Levenshtein yourself)  
Return top K using streams + priority logic.

---

# TOPIC 4 — Exceptions & Resource management

## Study
- Checked vs unchecked  
- try-with-resources  
- Exception chaining  

## Interview questions
1. Checked vs unchecked — API design choice?  
2. finally vs try-with-resources?  
3. Should microservices use checked exceptions across APIs?  
4. How do you preserve stack traces when wrapping?  

## Hard challenges
### C7. Multi-resource transactional simulator (⭐⭐⭐)
Simulate 3 resources (`ApiClient`, `FileStore`, `Lock`) implementing `AutoCloseable`.  
Operation must:
- acquire all  
- on failure, close in reverse order  
- aggregate failures into one exception with suppressed exceptions  
- retry transient failures up to N times with backoff (no Spring)

### C8. Exception taxonomy (⭐⭐)
Design `AppException` hierarchy: `ValidationException`, `NotFoundException`, `IntegrationException`.  
Write a handler function `Function<Throwable, ErrorBody>` using Java 8 that maps to error codes.

---

# TOPIC 5 — Generics & PECS

## Study
- Type erasure  
- `? extends` / `? super`  
- PECS  

## Interview questions
1. What is type erasure?  
2. Explain PECS with example.  
3. Why `List<String>` is not a `List<Object>`?  
4. Generic method vs generic class?  

## Hard challenges
### C9. Generic Repository + Specification (⭐⭐⭐⭐)
Implement in-memory:
```text
Repository<T, ID>
Specification<T>  // functional interface boolean test(T)
```
Support `findAll(Specification<T>)`, `and`/`or` composition (default methods),  
`map(Function<T,R>)` projections, and PECS-safe `copy(List<? extends T> src, List<? super T> dest)`.

### C10. Type-safe event bus (⭐⭐⭐)
`EventBus.subscribe(Class<E>, Consumer<E>)` and `publish(E)` without raw types leaking; support unregister.

---

# TOPIC 6 — Collections: List / Set / Map

## Study
- ArrayList vs LinkedList  
- HashSet / TreeSet / LinkedHashSet  
- HashMap internals (Java 8 tree bins)  
- Fail-fast iterators  

## Interview questions
1. HashMap put/get internals in Java 8?  
2. Load factor and resize?  
3. Fail-fast vs fail-safe?  
4. When LinkedHashMap?  
5. Complexity of common operations?  

## Hard challenges
### C11. Implement HashMapLite (⭐⭐⭐⭐)
Implement a simplified `HashMap<K,V>`:
- array of bins  
- linked list collision  
- **Java 8 twist:** convert bin to balanced structure OR document tree-bin behavior and implement binary search tree bins after threshold 8  
- `get/put/remove`, resize at 0.75  
- Support null key  

### C12. Group and rank lenders (⭐⭐⭐)
Given `List<Lender>`:
- group by state  
- within each state, top 3 by number of policies  
- return `Map<String, List<String>>` of lender names using Collections + Streams  

### C13. Detect cycles in graph (⭐⭐⭐)
Graph as `Map<String, List<String>>` adjacency. Detect cycle (DFS). Relates to dependency graphs in microservices.

---

# TOPIC 7 — Concurrent collections & thread safety

## Study
- ConcurrentHashMap  
- CopyOnWriteArrayList  
- Race conditions  

## Interview questions
1. ConcurrentHashMap vs Collections.synchronizedMap?  
2. compute/merge atomicity?  
3. COWAL use case?  
4. Happens-before visibility basics?  

## Hard challenges
### C14. Concurrent hit counter (⭐⭐⭐)
URL hit counter updated by 20 threads. Implement with:
1. synchronized HashMap (baseline)  
2. ConcurrentHashMap.merge  
Compare correctness; explain why (1) can be slow.  

### C15. Lock-free-ish ID generator registry (⭐⭐⭐⭐)
Service registry: register instance IDs, heartbeat expiry. Threads register/deregister concurrently. Expired entries must be purged by a cleaner thread. Use `ConcurrentHashMap` + atomic timestamps.

---

# TOPIC 8 — Lambdas & Functional interfaces

## Study
- Lambda syntax, effectively final  
- Predicate/Function/Consumer/Supplier  
- Method references  

## Interview questions
1. Functional interface rules?  
2. Lambda vs anonymous class (`this`)?  
3. Method reference kinds?  
4. How do you compose Predicates for validation?  

## Hard challenges
### C16. Validation DSL (⭐⭐⭐⭐) — MRCS inspired
Build:
```text
Validator<T> = Function<T, List<String>> // errors
```
Compose with `and`, `or`, `not` (default methods).  
Rules for lender search:
- name 3–90  
- address 2–60 no commas  
- city letters 2–18  
- account exactly 10 alnum  

Validate objects and collections; short-circuit vs collect-all modes.

### C17. Pipeline framework (⭐⭐⭐)
`Pipe<T>` with `map`, `filter`, `peek`, `execute(Consumer)` using functional interfaces only (no Stream API allowed in this challenge — reimplement a tiny subset).

---

# TOPIC 9 — Optional

## Study
- of / ofNullable  
- map / flatMap / filter  
- orElse vs orElseGet  
- Anti-patterns  

## Interview questions
1. orElse vs orElseGet?  
2. Optional.map vs flatMap?  
3. Why not Optional parameters/fields?  
4. How to replace nested null checks?  

## Hard challenges
### C18. Deep optional chaining (⭐⭐⭐)
Domain: `Order → Optional<Customer> → Optional<Address> → Optional<Zip>`.  
Write `nearestWarehouse(order)` using Optional only (no direct null checks).  
If missing any level, return `Optional.empty()` and log via Supplier lazily.

### C19. Optional anti-pattern fixer (⭐⭐)
Refactor a given ugly codebase snippet (you write the ugly version first) that misuses Optional; produce clean API returning `Optional` only as return type.

---

# TOPIC 10 — Streams API (core)

## Study
- Lazy evaluation  
- Intermediate vs terminal  
- filter/map/distinct/sorted/limit  
- collect / count / match / find  

## Interview questions
1. Intermediate vs terminal?  
2. Can you reuse a stream?  
3. findFirst vs findAny?  
4. When is stream slower than for-loop?  

## Hard challenges
### C20. Mortgage change analytics (⭐⭐⭐)
Given records `(changeType, state, zip, loanAmount)`:
- total amount by state  
- top 5 zips by change count  
- partition Audit vs Non-Audit  
- join top lender names as CSV  
All with one-pass-ish collectors where possible (`groupingBy`, `partitioningBy`, `mapping`).

### C21. Custom Collector (⭐⭐⭐⭐)
Write a collector that builds summary:
`count`, `min`, `max`, `avg`, `distinctStates` for loan amounts in **one** collection pass (implement `Collector` interface).

---

# TOPIC 11 — Streams API (advanced)

## Study
- flatMap  
- reduce vs collect  
- groupingBy downstream collectors  
- parallel streams pitfalls  

## Interview questions
1. map vs flatMap?  
2. reduce vs collect?  
3. Parallel stream pitfalls?  
4. How does groupingBy concurrent work?  

## Hard challenges
### C22. Nested policy flatten (⭐⭐⭐)
`List<Lender>` each has `List<Branch>` each has `List<Policy>`.  
Produce `List<PolicyView>` with lenderName+branch+policy via `flatMap`.  
Then group by lenderName → policyCount; filter count > N.

### C23. Parallel correctness trap (⭐⭐⭐⭐)
Create a demoshowing parallel stream corrupting a shared `ArrayList`/`HashMap`.  
Then fix using concurrent collectors / thread-safe structures.  
Explain ordering differences with `forEach` vs `forEachOrdered`.

### C24. Anagram groups + top frequency (⭐⭐⭐)
Group words that are anagrams; return groups size ≥ 2 sorted by size desc (Streams).

---

# TOPIC 12 — Concurrency & CompletableFuture (Java 8)

## Study
- ExecutorService  
- synchronized / volatile basics  
- deadlock  
- CompletableFuture pipelines  

## Interview questions
1. Runnable vs Callable?  
2. Thread pool sizing CPU vs IO?  
3. volatile vs synchronized?  
4. Deadlock conditions?  
5. thenApply vs thenCompose?  
6. allOf vs anyOf?  

## Hard challenges
### C25. Fan-out lender enrichment (⭐⭐⭐⭐) — microservices style
Given accountId, call 3 mock services async (name, address risk score, region):
- timeouts per call  
- if region fails, continue with partial data  
- if name fails, fail whole request  
- combine into `LenderProfile`  
Use `CompletableFuture`, `orTimeout` polyfill (Java 8: manual completeOnTimeout via scheduler), and an `Executor`.

### C26. Worker pool job scheduler (⭐⭐⭐⭐)
Job queue with priorities; N worker threads; delayed retries; graceful shutdown (`awaitTermination`). No external libs.

### C27. Deadlock then fix (⭐⭐⭐)
Create classic two-lock deadlock between `AccountService` and `LedgerService`; detect via jstack mindset; fix with ordered locking.

---

# TOPIC 13 — JVM memory & performance (interview + coding)

## Study
- Stack vs heap  
- GC basics  
- Common leaks (static maps, listeners)  

## Interview questions
1. Stack vs heap?  
2. Memory leak examples in Java?  
3. What to check after OOM?  
4. Why upgrade Java 8 → 11?  

## Hard challenges
### C28. Leak hunter (⭐⭐⭐)
Write a program that “leaks” via unbounded static cache; then fix with `LinkedHashMap` LRU (`removeEldestEntry`) max size 1000.  
Optional: show footprint discussion (heap dump mindset).

### C29. Allocate & measure (⭐⭐)
Generate 1M objects; compare memory/time between boxed `Integer` streams vs primitive `IntStream` sum.

---

# TOPIC 14 — Mixed “interview killer” projects (capstone)

Build **one** of these end-to-end in pure Java 8 (2–4 evenings):

### Capstone A — In-memory MRCS backend slice (⭐⭐⭐⭐)
- Validate lender search (account / name-address rules)  
- Optional-based service layer  
- Concurrent registry of lenders  
- Stream analytics dashboard methods  
- CompletableFuture calls to mock “region” + “MIS” APIs  
- Custom exceptions + suppressed aggregation  

### Capstone B — Mini HashMap + Stream analytics kit (⭐⭐⭐⭐)
- Your HashMapLite  
- Dataset loader  
- Custom Collector reports  
- Parallel vs sequential benchmark  

### Capstone C — Async orchestration framework (⭐⭐⭐⭐)
- Task DAG with dependencies  
- CompletableFuture execution  
- Failure policies: fail-fast / best-effort  
- Retry with jitter  

---

# 30-day challenge calendar (optional intensity track)

| Day | Topic | Must finish challenge |
|-----|--------|------------------------|
| 1 | OOP | C1 |
| 2 | equals/hashCode | C3 |
| 3 | String | C5 |
| 4 | Exceptions | C7 |
| 5 | Generics | C9 |
| 6 | Collections | C12 |
| 7 | Revision | Re-solve weakest |
| 8 | HashMap internals | C11 (start) |
| 9 | HashMap internals | C11 (finish) |
| 10 | Concurrent collections | C14–C15 |
| 11 | Lambdas | C16 |
| 12 | Optional | C18 |
| 13 | Streams core | C20 |
| 14 | Streams advanced | C21 or C22 |
| 15 | Parallel pitfalls | C23 |
| 16 | Concurrency | C27 |
| 17 | CompletableFuture | C25 |
| 18 | Scheduler | C26 (start) |
| 19 | Scheduler | C26 (finish) |
| 20 | JVM/perf | C28–C29 |
| 21–30 | Capstone A/B/C | Pick one and harden |

---

# Interview quick-fire pack (50)

**Core:** OOP defaults, equals/hashCode, String immutability, checked vs unchecked, PECS, ArrayList vs LinkedList, HashMap Java 8, fail-fast, ConcurrentHashMap, TreeSet vs HashSet  

**Java 8:** lambda effectively final, Predicate composition, method refs, Optional orElse/orElseGet, stream laziness, map/flatMap, reduce/collect, groupingBy, parallel pitfalls, custom Collector idea  

**Concurrency:** thread pool sizing, synchronized vs volatile, deadlock, race condition, CF thenApply/thenCompose, allOf, timeouts, atomic merge on CHM  

**Design:** immutability, API exception model, validation pipeline, fan-out aggregation, LRU cache, identity vs equality  

---

# Scoring rubric for each hard challenge

| Score | Meaning |
|------|---------|
| 0 | Did not compile / wrong approach |
| 1 | Works for happy path only |
| 2 | Edge cases handled |
| 3 | Clean Java 8 idioms + complexity awareness |
| 4 | Production-quality (tests, clear API, concurrency-safe if required) |

Aim for **3+** on every ⭐⭐⭐ challenge before interviews.

---

# Resume mapping (talk while you code)

| Challenge | Resume hook |
|-----------|-------------|
| C5, C16 | MRCS lender/account validation |
| C25, Capstone A | Third-party fan-out + gateway-style aggregation |
| C12, C20–C22 | Reporting / search result shaping |
| C14–C15, C26 | High-throughput backend services |
| C28, Topic 13 | Ancestry upgrade / production hardening mindset |
| C9, C11 | Strong CS fundamentals for senior interviews |

---

**Next (optional):** I can add a `projects/java8-challenges/` folder with starter stubs + JUnit tests for C5, C11, C16, C20, and C25.
