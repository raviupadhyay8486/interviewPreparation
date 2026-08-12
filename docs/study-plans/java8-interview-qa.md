# Java 1.8 Interview Questions & Answers (Concepts Only)

**Scope:** Java 1.8 concepts only (language, Collections, Streams, Optional, concurrency basics).  
**Format:** Best interview questions with clear answers, one by one.  
**Use:** Read answer aloud in 60–90 seconds; mark weak ones for revision.

---

## 1. OOP & Language Basics

### Q1. What is the difference between JDK, JRE, and JVM?
**Answer:**  
- **JVM** runs Java bytecode (platform-specific).  
- **JRE** = JVM + core libraries (needed to run apps).  
- **JDK** = JRE + development tools (`javac`, debugger, etc.) to develop apps.  
You need JDK to compile; JRE/JVM to run.

### Q2. What are the four pillars of OOP?
**Answer:**  
1. **Encapsulation** — hide state; expose via methods.  
2. **Inheritance** — reuse/extend behavior (`extends`).  
3. **Polymorphism** — same interface, different behavior (overloading/overriding).  
4. **Abstraction** — expose essential behavior; hide implementation (abstract class/interface).

### Q3. Abstract class vs interface in Java 8?
**Answer:**  
- **Abstract class:** can have constructors, instance fields, abstract + concrete methods; single inheritance.  
- **Interface (Java 8):** can have abstract, **`default`**, and **`static`** methods; multiple inheritance of type.  
- Use abstract class for shared state/identity; interface for capability/contract.  
Java 8 interfaces reduced the need for abstract utility base classes via `default` methods.

### Q4. Why were `default` methods added in Java 8?
**Answer:**  
To evolve APIs (like Collections) without breaking all implementing classes. Example: `List.sort(Comparator)` is a default method. Implementing classes get new behavior without recompilation changes for every implementor.

### Q5. Can two interfaces have the same default method? What happens?
**Answer:**  
Yes — diamond problem. If a class implements both, it **must override** that method and choose which one to call (e.g. `InterfaceA.super.method()`), or provide its own implementation. Compilation fails until resolved.

### Q6. What is a functional interface?
**Answer:**  
An interface with **exactly one abstract method** (SAM). It may have default/static methods. Marked with `@FunctionalInterface` (optional but recommended). Used as lambda/method-reference targets (`Predicate`, `Function`, `Runnable`, etc.).

### Q7. Overloading vs overriding?
**Answer:**  
- **Overloading:** same method name, different parameter list; compile-time polymorphism.  
- **Overriding:** subclass redefines superclass instance method with same signature; runtime polymorphism.  
Override rules: same/covariant return, same or broader access, cannot throw broader checked exceptions.

### Q8. Can static methods be overridden?
**Answer:**  
No. Static methods are **hidden**, not overridden. Call resolution is based on reference type at compile time, not runtime object type.

### Q9. What is the difference between composition and inheritance?
**Answer:**  
Inheritance = “is-a” (tight coupling). Composition = “has-a” (flexible). Prefer composition when reuse is needed without exposing parent API or when behavior should change at runtime.

### Q10. Explain encapsulation with a real example.
**Answer:**  
Keep fields private; validate in setters/constructors. Example: `accountNumber` must be 10 alphanumeric — callers cannot set invalid values directly; class enforces rules.

---

## 2. Object contracts: equals, hashCode, Comparable

### Q11. Why must equals and hashCode be overridden together?
**Answer:**  
Contract: equal objects **must** have equal hash codes. `HashMap`/`HashSet` use hashCode to find bucket, then equals to compare. Breaking this causes lost entries or duplicates.

### Q12. `==` vs `.equals()`?
**Answer:**  
- `==` compares references for objects (value for primitives).  
- `.equals()` compares logical equality (if overridden).  
For `String`, prefer `.equals()` for content comparison.

### Q13. Comparable vs Comparator?
**Answer:**  
- **Comparable (`compareTo`):** natural order defined inside the class.  
- **Comparator:** external ordering; can define multiple sort strategies.  
Java 8: Comparator lambdas / `Comparator.comparing(...)`.

### Q14. What is the equals/hashCode contract summary?
**Answer:**  
equals must be reflexive, symmetric, transitive, consistent, and `x.equals(null)` is false. Equal objects ⇒ same hashCode. Unequal objects may still collide on hashCode.

### Q15. Why should keys in HashMap be immutable (or not change fields used in equals/hashCode)?
**Answer:**  
If key mutates after insert, hash bucket location becomes wrong; `get` may fail to find the entry.

---

## 3. String

### Q16. Why is String immutable in Java?
**Answer:**  
Security (class loading, network), String pool caching, thread-safety, and hashCode caching for HashMap keys. Any “change” creates a new String object.

### Q17. String vs StringBuilder vs StringBuffer?
**Answer:**  
- **String:** immutable.  
- **StringBuilder:** mutable, **not** synchronized — faster for single-threaded building.  
- **StringBuffer:** mutable, synchronized — safer for multi-threaded, usually slower.  
Java 8 everyday code: prefer `StringBuilder` unless synchronization is required.

### Q18. What is the String pool?
**Answer:**  
A special heap area (in modern Java, heap-based pool) storing unique string literals so identical literals can be reused. `new String("a")` forces a new object; `"a"` may reuse pooled instance.

### Q19. Is String thread-safe?
**Answer:**  
Yes, because it is immutable — state cannot change after creation. Sharing a String across threads is safe without synchronization.

---

## 4. Exceptions

### Q20. Checked vs unchecked exceptions?
**Answer:**  
- **Checked:** must declare/handle (`IOException`) — compile-time enforced.  
- **Unchecked:** `RuntimeException` and subclasses (`NullPointerException`, `IllegalArgumentException`) — not required to declare.  
For APIs: validation errors often unchecked; recoverable external IO may be checked (or wrapped in runtime in many modern services).

### Q21. `throw` vs `throws`?
**Answer:**  
- `throw` — actually throw an exception instance.  
- `throws` — declare that a method may propagate checked exceptions to caller.

### Q22. What is try-with-resources?
**Answer:**  
Java 7+ syntax that auto-closes resources implementing `AutoCloseable`/`Closeable`. Closing happens in reverse order; suppressed exceptions are attached if close fails after primary exception.

### Q23. Does `finally` always execute?
**Answer:**  
Almost always — after try/catch. It may not run if JVM crashes (`System.exit`, power kill, infinite hang). Prefer try-with-resources for closing resources.

### Q24. final vs finally vs finalize?
**Answer:**  
- **final:** cannot reassign / override / extend (depending on target).  
- **finally:** block that runs after try/catch.  
- **finalize():** deprecated legacy GC cleanup hook — do not rely on it in Java 8+ production design.

---

## 5. Generics

### Q25. What is type erasure?
**Answer:**  
Generic type parameters are removed at compile time for bytecode compatibility. `List<String>` becomes raw `List` at runtime (with compile-time checks and bridges). You cannot do `new T()` or `instanceof T` reliably.

### Q26. What is PECS?
**Answer:**  
**Producer Extends, Consumer Super.**  
- Use `? extends T` when you only read (produce) `T`.  
- Use `? super T` when you only write (consume) `T`.  
Example: copy from `List<? extends Number>` to `List<? super Number>`.

### Q27. Why isn’t `List<String>` a subtype of `List<Object>`?
**Answer:**  
Because generics are invariant. If it were allowed, you could insert an `Integer` into a `List<Object>` that is actually `List<String>`, breaking type safety.

### Q28. Bounded type parameters — what are they?
**Answer:**  
Constraints like `<T extends Number>` or `<T extends Comparable<T>>` restricting what types can be used, enabling calling methods of the bound.

---

## 6. Collections Framework

### Q29. ArrayList vs LinkedList?
**Answer:**  
- **ArrayList:** dynamic array; O(1) random access; amortized O(1) append; O(n) insert/delete in middle.  
- **LinkedList:** doubly linked; O(n) access; O(1) insert/delete if node known.  
In practice ArrayList wins most cases due to CPU cache locality.

### Q30. HashSet vs TreeSet vs LinkedHashSet?
**Answer:**  
- **HashSet:** unordered unique elements via HashMap; average O(1).  
- **LinkedHashSet:** insertion-order uniqueness.  
- **TreeSet:** sorted unique via TreeMap; O(log n); needs Comparable/Comparator.

### Q31. How does HashMap work internally in Java 8?
**Answer:**  
Array of bins (buckets). Key’s `hashCode` determines index. Collisions store in a linked list; in Java 8, if a bin grows beyond threshold (and capacity large enough), it converts to a **balanced tree** for O(log n) worst-case lookups. On load factor breach (default 0.75), map resizes and rehashes.

### Q32. What is load factor in HashMap?
**Answer:**  
Measure of how full the map can get before resizing. Default **0.75** balances time and space. Higher = less memory wasted but more collisions; lower = fewer collisions but more resizing/memory.

### Q33. HashMap vs Hashtable vs ConcurrentHashMap?
**Answer:**  
- **HashMap:** not synchronized; allows one null key.  
- **Hashtable:** legacy synchronized whole-map locks; no nulls.  
- **ConcurrentHashMap:** concurrent thread-safe map; no nulls; better scalability than Hashtable.

### Q34. What is fail-fast iterator?
**Answer:**  
Iterators on most java.util collections detect concurrent structural modification (via `modCount`) and throw `ConcurrentModificationException`. It is best-effort, not a guarantee for all concurrency bugs.

### Q35. Fail-fast vs fail-safe?
**Answer:**  
- Fail-fast: detect modification during iteration and fail quickly (ArrayList iterator).  
- Fail-safe style: iterate on snapshot/weakly consistent view (e.g. ConcurrentHashMap iterators don’t throw CME typically; reflect some updates).

### Q36. How do you remove elements while iterating a List safely?
**Answer:**  
Use `Iterator.remove()`, or Java 8 `list.removeIf(predicate)`. Do not call `list.remove(i)` inside enhanced for-each (CME risk).

### Q37. What is LinkedHashMap used for?
**Answer:**  
Maintains insertion order (or access order). Useful for predictable iteration and simple **LRU cache** via `removeEldestEntry` in access-order mode.

### Q38. Difference between Collection and Collections?
**Answer:**  
- **Collection:** interface root of List/Set/Queue hierarchy.  
- **Collections:** utility class (`sort`, `synchronizedList`, `unmodifiableList`, etc.).

---

## 7. Java 8 Lambdas & Functional Interfaces

### Q39. What is a lambda expression?
**Answer:**  
A concise representation of a single-method behavior (anonymous function) that implements a functional interface. Example: `(a, b) -> a + b` for `BinaryOperator`/`Comparator`.

### Q40. What does “effectively final” mean?
**Answer:**  
A local variable used inside a lambda must not be reassigned after initialization (even if not declared `final`). This keeps capture semantics clear and thread-safe expectations simpler.

### Q41. Lambda vs anonymous inner class?
**Answer:**  
- Lambda does not create a new scope for `this` (`this` refers to enclosing class).  
- Anonymous class has its own `this`.  
- Lambdas are lighter syntax and target functional interfaces only.  
- Variable capture rules: effectively final for both patterns in practice for locals.

### Q42. Explain Predicate, Function, Consumer, Supplier.
**Answer:**  
- **Predicate&lt;T&gt;** — `boolean test(T)` (validation/filtering).  
- **Function&lt;T,R&gt;** — `R apply(T)` (mapping/transformation).  
- **Consumer&lt;T&gt;** — `void accept(T)` (side effects).  
- **Supplier&lt;T&gt;** — `T get()` (lazy factory/value provider).

### Q43. What are method references?
**Answer:**  
Shorthand for lambdas that only call an existing method:  
1. Static: `String::valueOf`  
2. Instance bound: `str::length`  
3. Instance unbound: `String::toLowerCase`  
4. Constructor: `ArrayList::new`

### Q44. Can a functional interface have default methods?
**Answer:**  
Yes. Default/static methods do not count toward the single abstract method limit. Example: `Predicate.and(...)`.

---

## 8. Optional (Java 8)

### Q45. What is Optional and why was it introduced?
**Answer:**  
A container that may or may not hold a non-null value. It makes “value may be missing” explicit in return types and helps avoid uncontrolled `NullPointerException` when used correctly.

### Q46. `Optional.of` vs `ofNullable` vs `empty`?
**Answer:**  
- `of(x)` — x must be non-null; NPE if null.  
- `ofNullable(x)` — empty if null.  
- `empty()` — explicit empty Optional.

### Q47. `orElse` vs `orElseGet`?
**Answer:**  
- `orElse(value)` — value is **always evaluated**.  
- `orElseGet(supplier)` — supplier runs **only if empty**.  
Use `orElseGet` for expensive defaults.

### Q48. `map` vs `flatMap` on Optional?
**Answer:**  
- `map` wraps returned value into Optional (unless already handling null as empty).  
- `flatMap` is used when mapper already returns `Optional` — avoids `Optional<Optional<T>>`.

### Q49. Should Optional be used as method parameter or field?
**Answer:**  
Generally **no**. Best practice: use Optional mainly as **return type**. For params/fields, prefer clear overloads, null-object patterns, or required values.

### Q50. Is Optional a replacement for all null checks?
**Answer:**  
No. It is for signaling absence in return values. Blindly wrapping everything in Optional adds noise. Don’t call `get()` without checking/`orElseThrow`.

---

## 9. Streams API (Java 8)

### Q51. What is a Stream?
**Answer:**  
A sequence of elements supporting functional-style operations. It is not a data structure; it does not store elements. Pipelines are usually lazy until a terminal operation.

### Q52. Intermediate vs terminal operations?
**Answer:**  
- **Intermediate:** return a stream (`filter`, `map`, `sorted`) — lazy.  
- **Terminal:** produce result/side-effect (`collect`, `forEach`, `count`, `findFirst`) — triggers processing.  
After terminal op, stream is consumed and cannot be reused.

### Q53. `map` vs `flatMap`?
**Answer:**  
- `map`: 1 input → 1 output element.  
- `flatMap`: 1 input → 0..n outputs, then flatten into a single stream.  
Example: stream of lists → stream of elements.

### Q54. What is lazy evaluation in Streams?
**Answer:**  
Intermediate ops form a pipeline but don’t run until terminal op. Enables optimizations like short-circuiting (`findFirst`, `anyMatch`).

### Q55. `reduce` vs `collect`?
**Answer:**  
- `reduce`: general immutable reduction to one value (sum, max).  
- `collect`: mutable reduction using Collector (toList, groupingBy) — usually better for building collections.

### Q56. What does `groupingBy` do?
**Answer:**  
Collector that groups elements by classifier function into `Map<K, List<T>>` (or custom downstream collectors like counting/mapping).

### Q57. `findFirst` vs `findAny`?
**Answer:**  
- `findFirst`: first element in encounter order.  
- `findAny`: any element; useful in parallel streams for performance.  
In sequential streams both often behave similarly, but semantics differ.

### Q58. Are parallel streams always faster?
**Answer:**  
No. Overhead can dominate for small data. Also unsafe with shared mutable state. Avoid for ordered sensitive ops unless carefully designed. Prefer parallel for large CPU-bound, splittable data with no shared mutation.

### Q59. Can a stream be reused?
**Answer:**  
No. Once a terminal operation runs, the stream is consumed. Create a new stream from the source for another pipeline.

### Q60. Difference between Collection and Stream?
**Answer:**  
Collection is an in-memory data structure with eager storage. Stream is a view/pipeline for computation — can be infinite (generator), lazy, and consumable once.

---

## 10. Concurrency basics (often asked with Java 8)

### Q61. Process vs Thread?
**Answer:**  
Process = independent execution with own memory space. Thread = lightweight unit within a process sharing memory. Java concurrency is mostly about threads sharing heap carefully.

### Q62. Runnable vs Callable?
**Answer:**  
- `Runnable.run()` — no return, cannot throw checked exceptions.  
- `Callable.call()` — returns value, may throw Exception.  
Use Callable with `ExecutorService.submit` for results/`Future`.

### Q63. What is ExecutorService?
**Answer:**  
A higher-level API to manage thread pools and asynchronously execute tasks instead of manually creating threads. Improves resource control and scalability.

### Q64. synchronized vs volatile?
**Answer:**  
- **synchronized:** mutual exclusion + memory visibility.  
- **volatile:** visibility/ordering for a single variable; **no** atomic compound actions (`i++` still unsafe).  
Use volatile for flags; synchronized/locks/atomics for read-modify-write.

### Q65. What is a race condition?
**Answer:**  
When correctness depends on uncontrolled timing of threads accessing shared mutable state. Fix with synchronization, concurrency utilities, or immutability.

### Q66. What is deadlock?
**Answer:**  
Two or more threads wait forever for locks held by each other. Prevention: lock ordering, tryLock timeouts, avoid nested locks, reduce lock scope.

### Q67. What is CompletableFuture (Java 8)?
**Answer:**  
A Future that can be completed explicitly and supports non-blocking callback pipelines (`thenApply`, `thenCompose`, `thenCombine`, `allOf`). Useful for async service composition without blocking threads excessively.

### Q68. `thenApply` vs `thenCompose`?
**Answer:**  
- `thenApply`: transform result into a value (like map).  
- `thenCompose`: transform result into another CompletionStage (like flatMap) — flattens nested futures.

### Q69. Thread pool sizing — CPU vs I/O bound?
**Answer:**  
- CPU-bound: roughly `cores` or `cores+1`.  
- I/O-bound: can be larger (threads wait on IO), formula often `cores * (1 + wait/compute)` as a guideline — tune with metrics.

### Q70. Is HashMap thread-safe? What to use instead?
**Answer:**  
HashMap is not thread-safe. For concurrent access use `ConcurrentHashMap` (or synchronize externally). In Java 8, ConcurrentHashMap provides better throughput under contention.

---

## 11. JVM Memory (concept questions)

### Q71. Stack vs Heap?
**Answer:**  
- **Stack:** per-thread frames — local primitives/references, method calls. Fast; short-lived.  
- **Heap:** shared objects/instances. Managed by GC.

### Q72. What is a memory leak in Java?
**Answer:**  
Objects still reachable (so GC can’t free) but unused by app logic — e.g. unbounded static caches, unremoved listeners. Not the same as forgetting `free()` in C.

### Q73. What is Garbage Collection conceptually?
**Answer:**  
Automatic reclamation of unreachable objects. Java 8 typically uses generational GC (young/old). Exact collector varies by JVM/flags; interview focus is reachability and avoiding leaks.

### Q74. OutOfMemoryError — what does it mean?
**Answer:**  
JVM cannot allocate more memory in heap (or other areas like Metaspace). Investigate heap dumps, leaks, oversized caches, or insufficient `-Xmx`.

### Q75. Why do teams upgrade from Java 8 to newer LTS (e.g. 11)?
**Answer:**  
Security updates, performance, language/API improvements, container friendliness, and long-term support. Java 8 apps often migrate carefully due to dependency/runtime changes.

---

## 12. Misc high-frequency Java 8 questions

### Q76. What new features did Java 8 introduce? (list)
**Answer:**  
Lambdas, Functional interfaces, Streams, Optional, default/static methods in interfaces, new Date/Time API (`java.time`), CompletableFuture, ConcurrentHashMap enhancements, method references, type annotations improvements, Nashorn (historical), etc.

### Q77. Why is the new Date/Time API (`java.time`) better than `Date`/`Calendar`?
**Answer:**  
Old API was mutable, confusing (month indexes), not thread-safe. `java.time` is immutable, fluent, clearer (`LocalDate`, `LocalDateTime`, `ZonedDateTime`, `Instant`), better for concurrency.

### Q78. What is Nashorn? (awareness)
**Answer:**  
Java 8 JavaScript engine for running JS on JVM. Later deprecated/removed in newer JDKs — mention only as Java 8 historical feature.

### Q79. Difference between intermediate `peek` and `map`?
**Answer:**  
`peek` is for debugging/side effects; should not mutate state in production pipelines. `map` is for transforming elements into new values.

### Q80. How do you sort a list in Java 8?
**Answer:**  
`list.sort(Comparator.comparing(Lender::getName));` or `Collections.sort(list, comparator)`. Streams: `list.stream().sorted(...).collect(Collectors.toList())` (doesn’t sort in-place unless you reassign).

---

## Quick revision checklist (best of the best)

Must be able to answer fluently:
1. Java 8 default methods + diamond problem  
2. Functional interface + lambda + effectively final  
3. HashMap internals in Java 8  
4. equals/hashCode contract  
5. ArrayList vs LinkedList; HashMap vs ConcurrentHashMap  
6. Optional best practices (`orElse` vs `orElseGet`)  
7. Stream map vs flatMap; lazy evaluation  
8. reduce vs collect; groupingBy  
9. Parallel stream risks  
10. CompletableFuture thenApply vs thenCompose  
11. synchronized vs volatile  
12. checked vs unchecked  
13. String immutability  
14. PECS  
15. java.time vs Date  

---

## How to practice answers

For each question:
1. Give **definition** (1 sentence)  
2. Give **why it matters** (1 sentence)  
3. Give **example** (1 sentence)  
4. Optional: **resume link** (microservices/validation/Java upgrade)

Example for Q31 (HashMap):  
“HashMap stores entries in buckets by hash. Java 8 treeifies long collision chains. This matters for worst-case lookup performance under collisions. Example: caching lenders by accountNumber.”

---

**Related docs**
- Day-by-day plan: [`java8-day-by-day.md`](./java8-day-by-day.md)  
- Hard coding challenges: [`java8-topics-questions-challenges.md`](./java8-topics-questions-challenges.md)
