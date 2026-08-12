# Java 1.8 + Spring Boot Study Plan

**For:** Ravi Ranjan Upadhyay — Java Full Stack / Microservices Technical Lead  
**Goal:** Interview-ready depth on **Java 8** and **Spring Boot**, tied to resume projects (MRCS, Ancestry, Xebia MATT, MGM, SetPlex, Healthcare HIS).  
**Cadence:** ~2–3 hours/day · **4-week core** · then ongoing revision.

> **Java 8 only (topic-wise, day-by-day):** [`java8-day-by-day.md`](./java8-day-by-day.md)  
> **Java 8 topics + interview questions + hard programming challenges:** [`java8-topics-questions-challenges.md`](./java8-topics-questions-challenges.md)

---

## How to use this plan

1. Study the **topic** (notes + small code).
2. Answer the **questions out loud** (STAR + code where useful).
3. Map each answer to a **resume project** (table at the end).
4. Mark: 🟢 Strong · 🟡 Needs practice · 🔴 Must revise.

---

## 4-week roadmap

| Week | Focus | Outcome |
|------|--------|---------|
| **1** | Java 8 core + Collections + Exceptions + Memory | Clear Java 8 fundamentals |
| **2** | Java 8 Streams, Optional, Concurrency, JVM | Coding + concurrency answers |
| **3** | Spring Boot core, REST, Data JPA, Security | Backend interview depth |
| **4** | Microservices, Cloud, Testing + mock interviews | Lead-level storytelling |

**Daily pattern (90–120 min)**  
- 40 min theory/notes  
- 30 min coding (small programs / Spring Boot slice)  
- 20–30 min question drill (speak answers)

---

# PART A — JAVA 1.8 STUDY PLAN

## Week 1 — Core Java 8

### Day 1–2: OOP + Language basics
- Classes, interfaces (incl. `default` / `static` methods — Java 8)
- Inheritance vs composition
- `equals` / `hashCode` / `Comparable` / `Comparator`
- Immutability, `String` pool, `StringBuilder`

**Questions**
1. Difference between abstract class and interface in Java 8?
2. Why override both `equals` and `hashCode`?
3. What is the contract of `Comparable` vs `Comparator`?
4. Is `String` immutable? Why does that matter in multi-threaded apps?
5. What are marker interfaces? Give examples.
6. Difference between `==` and `.equals()` for objects and strings.
7. Can we override a private or static method? Explain.
8. What is covariance / contravariance with return types?
9. Explain composition over inheritance with a microservice example.
10. What changed for interfaces in Java 8 (`default`, `static`)?

### Day 3: Collections framework
- `List`, `Set`, `Map` implementations and Big-O
- `HashMap` internals (buckets, hash, collision — Java 8 tree bins)
- `ConcurrentHashMap` basics
- Fail-fast vs fail-safe iterators

**Questions**
11. Difference between `ArrayList` and `LinkedList`?
12. How does `HashMap` work internally in Java 8?
13. What is the load factor? What happens on rehash?
14. Difference between `HashMap`, `Hashtable`, and `ConcurrentHashMap`?
15. Why is `HashMap` not thread-safe? What can go wrong?
16. Difference between `HashSet` and `TreeSet`?
17. What is fail-fast? Give an example that throws `ConcurrentModificationException`.
18. Difference between `Comparable` and sorting with Streams/`Comparator`.
19. When would you use `LinkedHashMap`?
20. How do you safely iterate and remove from a `List`?

### Day 4: Exceptions + I/O basics
- Checked vs unchecked
- try-with-resources (Java 7+)
- Custom exceptions in REST/microservices

**Questions**
21. Checked vs unchecked exceptions — when to use which?
22. What does try-with-resources guarantee?
23. Difference between `throw` and `throws`?
24. Can you catch `Error`? Should you?
25. How do you design exception hierarchy for a Spring Boot microservice?
26. What is exception wrapping / cause chaining?
27. Difference between `final`, `finally`, `finalize`?

### Day 5: Generics + Enums
- Type erasure, bounded wildcards (`? extends`, `? super`)
- PECS principle

**Questions**
28. What is type erasure?
29. Explain PECS with an example.
30. Difference between `List<Object>` and `List<?>`?
31. Can you create a generic array? Why/why not?
32. How do enums work? Can enums have methods/constructors?

### Day 6–7: Revision + coding drills
- Implement LRU cache with `LinkedHashMap`
- Frequency count with Streams
- Custom sort of domain objects (Lender, Policy)

---

## Week 2 — Java 8 features + concurrency + JVM

### Day 8–9: Lambda, Functional interfaces, Method references
- `@FunctionalInterface`
- `Predicate`, `Function`, `Consumer`, `Supplier`, `BiFunction`
- Method / constructor references

**Questions**
33. What is a functional interface? Rules?
34. Why were lambdas added in Java 8?
35. Difference between lambda and anonymous inner class (variables, `this`)?
36. Explain `Predicate` vs `Function` with API validation examples.
37. What is a method reference? Types of method references?
38. Can a functional interface have `default` methods?

### Day 10–11: Streams API + Optional
- Intermediate vs terminal ops
- `map` / `flatMap` / `filter` / `reduce` / `collect`
- Lazy evaluation, short-circuiting
- Parallel streams caveats
- `Optional` — correct usage (avoid `Optional` fields/params anti-patterns)

**Questions**
39. Intermediate vs terminal operations?
40. Difference between `map` and `flatMap`?
41. Are Streams reusable? What happens if you reuse one?
42. When are parallel streams harmful?
43. How does `reduce` differ from `collect`?
44. How do you handle `null` safely with `Optional`?
45. Why should you not use `Optional` as a method parameter or field?
46. Write a Stream pipeline: filter active lenders, map names, join as CSV.
47. Difference between `findFirst` and `findAny`?
48. What is short-circuiting in Streams?

### Day 12–13: Concurrency
- Thread lifecycle, `Runnable` vs `Callable`
- `ExecutorService`, thread pools
- `synchronized`, locks, volatile, happens-before (basics)
- Deadlock causes
- `CompletableFuture` intro (useful with async microservices)

**Questions**
49. Difference between process and thread?
50. `Runnable` vs `Callable`?
51. How do you size a thread pool for I/O-bound vs CPU-bound work?
52. What does `volatile` guarantee (and not guarantee)?
53. Explain deadlock and how to prevent it.
54. Difference between `synchronized` method and block?
55. What is `CompletableFuture`? How does it help async REST calls?
56. Difference between `wait`/`notify` and parking/locks (high level)?
57. What is a race condition? Example from shared in-memory cache?

### Day 14: Memory / GC / Java 8 → 11 awareness
- Heap vs stack
- GC overview (Young/Old)
- Why Ancestry Java 8 → 11 upgrade matters (resume talking point)

**Questions**
58. Stack vs heap — what lives where?
59. What is a memory leak in Java? Common causes?
60. What happens when `OutOfMemoryError` occurs?
61. Why move from Java 8 to Java 11 in production (Ancestry story)?
62. What is Metaspace (vs PermGen)?

---

# PART B — SPRING BOOT STUDY PLAN

## Week 3 — Spring Boot fundamentals → data → security

### Day 15: Spring vs Spring Boot + IoC/DI
- Bean lifecycle, scopes
- `@Component` / `@Service` / `@Repository` / `@Controller`
- `@Autowired` vs constructor injection
- `@Configuration`, `@Bean`, `@ComponentScan`

**Questions**
63. What is Inversion of Control? Dependency Injection?
64. Why prefer constructor injection?
65. Bean scopes — singleton vs prototype (and request/session)?
66. What does Spring Boot auto-configuration do?
67. What is `@SpringBootApplication` composed of?
68. How do you exclude an auto-configuration?
69. Difference between `@Component` and `@Bean`?
70. Circular dependency — how does Spring detect/fix it?

### Day 16: REST APIs + validation + exception handling
- `@RestController`, `@RequestMapping`, status codes
- `@Valid`, `@ControllerAdvice`
- DTOs vs entities
- Idempotency basics

**Questions**
71. Difference between `@Controller` and `@RestController`?
72. How do you return proper HTTP status codes?
73. How do you implement global exception handling?
74. PUT vs PATCH vs POST — when to use which?
75. How do you version REST APIs?
76. What is idempotency? Which methods are idempotent?
77. How do you validate request bodies in Spring Boot?
78. DTO vs Entity — why not expose JPA entities directly? (MRCS/lender APIs)

### Day 17: Spring Data JPA / Hibernate
- Repositories, derived queries, `@Query`
- Lazy vs Eager
- N+1 problem
- Transactions `@Transactional` (propagation basics)
- Pagination/sorting

**Questions**
79. What is Spring Data JPA? How does it reduce boilerplate?
80. LazyInitializationException — causes and fixes?
81. What is the N+1 select problem? How do you fix it?
82. `@Transactional` — what does it do? Where should it live?
83. Propagation `REQUIRED` vs `REQUIRES_NEW`?
84. Difference between `save`, `saveAndFlush`, `persist`?
85. How do you implement pagination for lender search results?
86. First-level vs second-level cache (basics)?
87. How do you map relationships (`@OneToMany`, etc.) without blowing performance?

### Day 18: Spring Security + OAuth2 (resume-critical)
- Filter chain basics
- Stateless JWT / Bearer tokens
- OAuth2 / OIDC concepts
- Okta (MGM) and Entra B2E (MRCS) storytelling

**Questions**
88. How does Spring Security filter chain work?
89. Difference between authentication and authorization?
90. What is JWT? Pros/cons vs session cookies?
91. OAuth2 roles: resource owner, client, auth server, resource server?
92. Authorization Code vs Client Credentials flows — when each?
93. How would you validate a Bearer token at an API Gateway (LIG story)?
94. Difference between OAuth2 and OpenID Connect?
95. How did you use Okta with Spring Security in MGM?
96. How does Entra B2E Bearer header flow work in MRCS?
97. CSRF — when does it matter for SPAs calling APIs?

### Day 19: Config, profiles, actuators, scheduling/async
- `application.yml`, profiles, externalized config
- Actuator health/metrics
- `@Async`, `@Scheduled`
- Spring Batch awareness (Xebia Excel imports)

**Questions**
98. How do Spring profiles work across dev/test/prod?
99. How do you externalize secrets (without hardcoding)?
100. What Actuator endpoints would you expose on OpenShift/K8s?
101. How does `@Async` work? What Executor is used?
102. When choose Spring Batch vs plain `@Async` for Excel import (Xebia)?
103. How do you make a Spring Boot app 12-factor friendly?

### Day 20–21: Mini project drill
Build a tiny **Lender Search** Spring Boot API:
- `GET /lenders/account/{id}`
- `GET /lenders/search?name=&address=`
- JPA entity + validation + `@ControllerAdvice`
- Mock Bearer header check filter  
(Aligns with MRCS backend side.)

---

## Week 4 — Microservices + resilience + testing + interview mocks

### Day 22–23: Microservices with Spring Cloud (resume stack)
- Service decomposition / bounded contexts
- Feign Client
- API Gateway (Spring Cloud Gateway — LIG)
- Config patterns
- Circuit breaker / resilience mindset (even if tool varies)

**Questions**
104. Monolith vs microservices — trade-offs?
105. How do you define service boundaries? (SetPlex / HIS examples)
106. What is an API Gateway responsible for?
107. Feign vs RestTemplate vs WebClient?
108. How do you handle inter-service authentication?
109. What is the saga pattern (high level)? When needed?
110. How do you version microservices independently?
111. Shared DB vs DB-per-service — your recommendation?
112. How did you approach Healthcare HIS / SetPlex decomposition?
113. How does Spring Cloud Gateway validate tokens then call downstream (MRCS/LIG)?

### Day 24: Messaging + async architectures
- Kafka producers/consumers (Ancestry / State Farm)
- At-least-once vs exactly-once (practical view)
- Idempotent consumers

**Questions**
114. Why use Kafka between microservices?
115. Consumer group behavior?
116. How do you avoid duplicate processing?
117. Ordering guarantees in Kafka — partition key choice?
118. Sync REST vs async events — when each? (Media processing example)

### Day 25: Testing
- JUnit 5 + Mockito
- `@SpringBootTest` vs `@WebMvcTest` vs `@DataJpaTest`
- Testcontainers awareness (optional)

**Questions**
119. How do you mock a dependency with Mockito?
120. `@Mock` vs `@InjectMocks`?
121. When use slice tests vs full `@SpringBootTest`?
122. How do you test a `@RestController`?
123. How do you keep 90%+ coverage meaningful (not just vanity)?

### Day 26: Production concerns
- Logging, correlation IDs
- Health checks, readiness/liveness
- Blue-green / rolling deploys (Argo CD story)
- Observability (Dynatrace)

**Questions**
124. How do you debug a production issue in a microservice mesh?
125. What is a readiness vs liveness probe?
126. How do you achieve zero-downtime deploy for Spring Boot on OpenShift?
127. What metrics matter for API latency and error rate?

### Day 27–28: Mock interviews
- 45 min Java 8 grilling  
- 45 min Spring Boot + microservices  
- 30 min resume walkthrough (project deep dive)

---

# PART C — QUESTION BANK (QUICK LISTS)

## Java 1.8 — must-answer list (top 25)

1. Java 8 interface `default` methods — why?
2. `HashMap` internals (Java 8)
3. `ConcurrentHashMap` vs synchronized `HashMap`
4. Functional interfaces + lambda
5. Stream `map` vs `flatMap`
6. Parallel stream pitfalls
7. `Optional` best practices
8. `equals`/`hashCode` contract
9. Fail-fast iterators
10. Checked vs unchecked exceptions
11. Generics PECS
12. Thread pool sizing
13. `volatile` vs `synchronized`
14. Deadlock prevention
15. `CompletableFuture` basics
16. Immutability benefits
17. `Comparable` vs `Comparator`
18. Memory leak examples
19. Why Java 8 → 11 upgrade?
20. `String` immutability + pool
21. Difference `ArrayList` / `LinkedList` / `CopyOnWriteArrayList`
22. How `HashSet` uses `HashMap`
23. Try-with-resources
24. Race conditions
25. When not to use Streams

## Spring Boot — must-answer list (top 30)

1. Auto-configuration
2. Constructor injection
3. Bean scopes
4. `@SpringBootApplication`
5. REST exception handling (`@ControllerAdvice`)
6. Validation (`@Valid`)
7. DTO vs Entity
8. JPA Lazy loading / `LazyInitializationException`
9. N+1 problem
10. `@Transactional` + propagation
11. Spring Security filter chain
12. JWT Bearer validation
13. OAuth2 vs OIDC
14. Okta integration approach
15. API Gateway responsibilities
16. Feign Client usage
17. Profiles & external config
18. Actuator health
19. `@Async` / executors
20. Spring Batch use cases
21. Microservice decomposition
22. DB-per-service
23. Kafka in Spring Boot apps
24. Idempotent APIs/consumers
25. Testing slices (`WebMvcTest`, etc.)
26. Zero-downtime deploy
27. Correlation ID / distributed tracing mindset
28. Circuit breaker concept
29. Designing lender search APIs
30. Securing SPA → Gateway → microservices (MRCS)

---

# PART D — MAP QUESTIONS TO YOUR RESUME

| Resume project | Drill these topics / questions |
|----------------|--------------------------------|
| **MRCS** | REST design, Bearer/OAuth2, Gateway, validation, third-party API calls (Q73–78, 88–97, 106–113) |
| **State Farm AWS/ROSA** | K8s deploy, health, Kafka, Spring Boot on OpenShift (Q100, 114–118, 124–127) |
| **Ancestry** | Java 8→11, microservices migration, Kafka, CI/CD (Q58–62, 104–112, 114–118) |
| **Xebia MATT** | Service design, async REST, JPA, batch/Excel (Q79–87, 98–103, 104–111) |
| **MGM** | Spring Security, Okta OAuth2/OIDC, role microservices (Q88–97, 104–108) |
| **SetPlex / HIS** | Monolith → microservices, Feign, AOP logging, health (Q104–113, 119–123) |

### STAR answer template (use for senior interviews)

- **Situation:** project + constraint  
- **Task:** your ownership  
- **Action:** Spring Boot / Java design choices  
- **Result:** metric or qualitative outcome (downtime ↓, zero-downtime upgrade, coverage, etc.)

---

# PART E — PRACTICE CODING PROMPTS (JAVA 8)

1. Group a `List<Policy>` by `zip` using Streams → `Map<String, List<Policy>>`.
2. Deduplicate lenders by account number preserving order.
3. Parse account numbers; validate exactly 10 alphanumeric (`Character`/`regex` + Stream).
4. Async: call 3 downstream APIs with `CompletableFuture` and combine results.
5. Implement rate-limiting bucket with concurrency safety (interview discussion).

# PART F — PRACTICE CODING PROMPTS (SPRING BOOT)

1. Lender search API with validation + pagination.
2. `@ControllerAdvice` mapping domain exceptions → HTTP codes.
3. Security filter that checks `Authorization: Bearer …`.
4. JPA entity graph / join-fetch to avoid N+1 on “lender + addresses”.
5. Kafka listener that updates status idempotently.

---

## Suggested resources (keep lean)

- Java 8: official Stream/Lambda tutorials + your own flashcards from Part C  
- Spring: Spring Boot Reference — Core, Web, Data, Security sections  
- Practice: rebuild tiny slices under `projects/mrcs/backend` (see repo scaffold)

## Tracking checklist

- [ ] Week 1 Java core complete  
- [ ] Week 2 Streams/concurrency complete  
- [ ] Week 3 Spring Boot + JPA + Security complete  
- [ ] Week 4 Microservices + 2 mock interviews  
- [ ] Can explain MRCS auth/gateway without notes  
- [ ] Can explain Ancestry Java upgrade + Kafka  
- [ ] Can explain MGM Okta + Xebia microservice design  

---

**Next step in this repo:** add answer keys or flashcards per section, or start a Spring Boot lender-search coding kata aligned to MRCS.
