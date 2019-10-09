package com.for_comprehension.function;

import org.junit.Test;

import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import static java.util.concurrent.Executors.newFixedThreadPool;

public class FuturesDemo {
    @Test
    public void example_1() throws Exception {
        ExecutorService executorService = newFixedThreadPool(10);

        Future<Integer> submit = executorService.submit(() -> {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                //
            }

            return 42;
        });

        System.out.println(submit.get());
    }

    @Test
    public void example_2() throws Exception {
        ExecutorService executorService = new ThreadPoolExecutor(10, 10,
          0L, TimeUnit.MILLISECONDS,
          new LinkedBlockingQueue<Runnable>(),
          r -> new Thread(r, UUID.randomUUID().toString()));

        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(4000);
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                //
            }

            return 1;
        }, executorService);

        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                //
            }

            return 2;
        }, executorService);

        f1.acceptEither(f2, System.out::println).join();
    }

    @Test
    public void example_3() throws Exception {
        ExecutorService executorService = new ThreadPoolExecutor(10, 10,
          0L, TimeUnit.MILLISECONDS,
          new ArrayBlockingQueue<>(100),
          r -> new Thread(r, UUID.randomUUID().toString()));

        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture
          .supplyAsync(withDelay(42), executorService);

        Thread.sleep(2000);

        CompletableFuture<Integer> integerCompletableFuture1 = integerCompletableFuture
          .thenApplyAsync(i -> withDelay(i).get(), executorService);
    }

    @Test
    public void example_4() throws Exception {
        CompletableFuture<Integer> integerCompletableFuture = new CompletableFuture<>();
        integerCompletableFuture.complete(42);
        integerCompletableFuture.join();
    }

    // http://viktorklang.com/blog/Futures-in-Scala-protips-6.html
    @Test
    public void example_5() throws Exception {
        ExecutorService executorService = newFixedThreadPool(10);
        Future<?> submit = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        submit.cancel(true);
    }

    private Supplier<Integer> withDelay(int i) {
        return () -> {
            try {
                Thread.sleep(1000);
                System.out.println("Returning " + i + " from thread " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return i;
        };
    }
}
