package com.learn.controller;

import com.learn.collector.CollectorDemo;
import com.learn.concurrency.ConcurrencyDemo;
import com.learn.datetime.DateTimeDemo;
import com.learn.defaultstatic.DefaultStaticDemo;
import com.learn.functionalinterface.FunctionalInterfaceDemo;
import com.learn.lambda.LambdaDemo;
import com.learn.methodreference.MethodReferenceDemo;
import com.learn.optional.OptionalDemo;
import com.learn.stream.StreamDemo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * REST entry points for each Java 8 topic package.
 */
@RestController
@RequestMapping("/api/java8")
public class Java8TopicController {

    private final FunctionalInterfaceDemo functionalInterfaceDemo;
    private final LambdaDemo lambdaDemo;
    private final MethodReferenceDemo methodReferenceDemo;
    private final StreamDemo streamDemo;
    private final CollectorDemo collectorDemo;
    private final OptionalDemo optionalDemo;
    private final DateTimeDemo dateTimeDemo;
    private final ConcurrencyDemo concurrencyDemo;
    private final DefaultStaticDemo defaultStaticDemo;

    public Java8TopicController(FunctionalInterfaceDemo functionalInterfaceDemo,
                                LambdaDemo lambdaDemo,
                                MethodReferenceDemo methodReferenceDemo,
                                StreamDemo streamDemo,
                                CollectorDemo collectorDemo,
                                OptionalDemo optionalDemo,
                                DateTimeDemo dateTimeDemo,
                                ConcurrencyDemo concurrencyDemo,
                                DefaultStaticDemo defaultStaticDemo) {
        this.functionalInterfaceDemo = functionalInterfaceDemo;
        this.lambdaDemo = lambdaDemo;
        this.methodReferenceDemo = methodReferenceDemo;
        this.streamDemo = streamDemo;
        this.collectorDemo = collectorDemo;
        this.optionalDemo = optionalDemo;
        this.dateTimeDemo = dateTimeDemo;
        this.concurrencyDemo = concurrencyDemo;
        this.defaultStaticDemo = defaultStaticDemo;
    }

    @GetMapping
    public Map<String, String> index() {
        Map<String, String> apis = new LinkedHashMap<String, String>();
        apis.put("functional-interface", "/api/java8/functional-interface");
        apis.put("lambda", "/api/java8/lambda");
        apis.put("method-reference", "/api/java8/method-reference");
        apis.put("stream", "/api/java8/stream");
        apis.put("stream-map-flatmap", "/api/java8/stream/map-vs-flatmap");
        apis.put("collector", "/api/java8/collector");
        apis.put("optional", "/api/java8/optional");
        apis.put("datetime", "/api/java8/datetime");
        apis.put("concurrency", "/api/java8/concurrency");
        apis.put("parallel-stream", "/api/java8/parallel-stream");
        apis.put("default-static", "/api/java8/default-static");
        return apis;
    }

    @GetMapping("/functional-interface")
    public ResponseEntity<Object> functionalInterface() {
        return ResponseEntity.ok(functionalInterfaceDemo.demo());
    }

    @GetMapping("/lambda")
    public ResponseEntity<Object> lambda() {
        return ResponseEntity.ok(lambdaDemo.demo());
    }

    @GetMapping("/method-reference")
    public ResponseEntity<Object> methodReference() {
        return ResponseEntity.ok(methodReferenceDemo.demo());
    }

    @GetMapping("/stream")
    public ResponseEntity<Object> stream() {
        return ResponseEntity.ok(streamDemo.basicPipeline());
    }

    @GetMapping("/stream/map-vs-flatmap")
    public ResponseEntity<Object> mapVsFlatMap() {
        return ResponseEntity.ok(streamDemo.mapVsFlatMap());
    }

    @GetMapping("/collector")
    public ResponseEntity<Object> collector() {
        return ResponseEntity.ok(collectorDemo.demo());
    }

    @GetMapping("/optional")
    public ResponseEntity<Object> optional() {
        return ResponseEntity.ok(optionalDemo.demo());
    }

    @GetMapping("/datetime")
    public ResponseEntity<Object> datetime() {
        return ResponseEntity.ok(dateTimeDemo.demo());
    }

    @GetMapping("/concurrency")
    public ResponseEntity<Object> concurrency() throws Exception {
        return ResponseEntity.ok(concurrencyDemo.demo());
    }

    @GetMapping("/parallel-stream")
    public ResponseEntity<Object> parallelStream() {
        return ResponseEntity.ok(concurrencyDemo.parallelStreamDemo());
    }

    @GetMapping("/default-static")
    public ResponseEntity<Object> defaultStatic() {
        return ResponseEntity.ok(defaultStaticDemo.demo());
    }
}
