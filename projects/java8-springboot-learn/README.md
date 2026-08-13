# Java 1.8 + Spring Boot Learning Project

Runnable Spring Boot starter project with **topic-wise packages** for Java 8 interview practice.

- **Java:** 1.8 (`source/target 1.8`)
- **Spring Boot:** 2.7.18 (last line supporting Java 8)
- **Port:** `8088`

## Package structure

```text
com.learn
├── Java8LearnApplication.java          ← Spring Boot main
├── model/
│   └── Employee.java                   ← shared sample data
├── controller/
│   └── Java8TopicController.java       ← REST APIs for each topic
├── functionalinterface/                ← Predicate, Function, Consumer, Supplier, @FunctionalInterface
├── lambda/                             ← lambda expressions
├── methodreference/                    ← method / constructor references
├── stream/                             ← map vs flatMap, filter pipelines
├── collector/                          ← toList, toSet, toMap, groupingBy, partitioningBy, joining
├── optional/                           ← Optional map/flatMap/orElseGet
├── datetime/                           ← java.time API
├── concurrency/                        ← CompletableFuture + parallel stream
├── defaultstatic/                      ← default/static methods + diamond problem
└── demo/
    └── StartupBanner.java
```

## Run

```bash
cd projects/java8-springboot-learn
mvn spring-boot:run
```

Or:

```bash
mvn clean package -DskipTests
java -jar target/java8-springboot-learn-1.0.0.jar
```

## Try APIs

| Topic | URL |
|-------|-----|
| Index | http://localhost:8088/api/java8 |
| Functional Interface | http://localhost:8088/api/java8/functional-interface |
| Lambda | http://localhost:8088/api/java8/lambda |
| Method Reference | http://localhost:8088/api/java8/method-reference |
| Stream | http://localhost:8088/api/java8/stream |
| Map vs FlatMap | http://localhost:8088/api/java8/stream/map-vs-flatmap |
| Collector | http://localhost:8088/api/java8/collector |
| Optional | http://localhost:8088/api/java8/optional |
| DateTime | http://localhost:8088/api/java8/datetime |
| Concurrency | http://localhost:8088/api/java8/concurrency |
| Parallel Stream | http://localhost:8088/api/java8/parallel-stream |
| Default/Static | http://localhost:8088/api/java8/default-static |

```bash
curl http://localhost:8088/api/java8/collector
curl http://localhost:8088/api/java8/stream/map-vs-flatmap
```

## Import into IDE

1. Open `projects/java8-springboot-learn` as Maven project  
2. JDK 8+ (project compiles as Java 8)  
3. Run `Java8LearnApplication`

You can also copy this folder into your local  
`/Users/raviranjanupadhyay/Desktop/Interview/InterviewExamples`.
