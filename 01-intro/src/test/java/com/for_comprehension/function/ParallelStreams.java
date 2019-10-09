package com.for_comprehension.function;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static com.pivovarit.collectors.ParallelCollectors.parallelToList;

public class ParallelStreams {

    @Test
    public void example_1() throws Exception {
        List<Integer> integers = Arrays.asList(1, 2, 3);

        Integer integer = integers.stream()
          .parallel()
          .map(i -> getAnIntWithDelay(i))
          .reduce(Integer::sum)
          .orElse(0);

        System.out.println("result: " + integer);
    }

    private final static Integer[] list;

    static {
        list = new Random().ints().limit(10000000).boxed().toArray(Integer[]::new);
    }


    @Test
    public void example_2() throws Exception {
          Arrays.parallelSort(list);
    }


    @Test
    public void example_3() {
        List<Integer> integers = Arrays.asList(1, 2, 3);

        ExecutorService executorService = new ThreadPoolExecutor(10, 10,
          0L, TimeUnit.MILLISECONDS,
          new LinkedBlockingQueue<Runnable>(),
          r -> new Thread(r, UUID.randomUUID().toString()));

        List<Integer> join = integers.stream()
          .collect(parallelToList(i -> getAnIntWithDelay(i), executorService, 10))
          .join();
    }

    private int getAnIntWithDelay(Integer i) {
        try {
            Thread.sleep(1000);
            System.out.println("processing on thread " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            //
        }
        return i * 2;
    }
}
