package com.learn.concurrency;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * Java 8 concurrency: CompletableFuture fan-out.
 */
@Service
public class ConcurrencyDemo {

    public Object demo() throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        try {
            CompletableFuture<String> name = CompletableFuture.supplyAsync(() -> slow("NAME:Ravi"), pool);
            CompletableFuture<String> region = CompletableFuture.supplyAsync(() -> slow("REGION:IL"), pool);
            CompletableFuture<String> risk = CompletableFuture.supplyAsync(() -> slow("RISK:LOW"), pool);

            CompletableFuture<Void> all = CompletableFuture.allOf(name, region, risk);
            all.join();

            List<String> combined = Arrays.asList(name.get(), region.get(), risk.get());
            return combined;
        } finally {
            pool.shutdown();
        }
    }

    public Object parallelStreamDemo() {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        List<Integer> squares = nums.parallelStream()
                .map(n -> n * n)
                .collect(Collectors.toList());
        return squares;
    }

    private String slow(String value) {
        try {
            Thread.sleep(50L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return value;
    }
}
