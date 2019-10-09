package com.for_comprehension.function.E06;

import com.pivovarit.collectors.ParallelCollectors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

class PCollectorsDemo {
    public static void main(String[] args) {
        Stream<Integer> collect = Stream.iterate(0, i -> i + 1)
          .limit(20)
          .collect(ParallelCollectors.parallel(i -> returnWithRandomDelay(i), Executors.newFixedThreadPool(10)));

        collect.forEach(System.out::println);
    }


    private static Integer returnWithRandomDelay(Integer i) {
        try {
            Thread.sleep(500 + Math.abs(ThreadLocalRandom.current().nextInt(1000)));
        } catch (InterruptedException e) {
        }
        return i;
    }
}
