package com.for_comprehension.function.E06;

import com.for_comprehension.function.misc.User;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class CompletableFuturesTest {

    @Test
    public void l1_manualCompletion() {
        CompletableFuture<Integer> input = new CompletableFuture<>();
        CompletableFutures.L1_manualCompletion().accept(input);

        assertThat(input).isCompletedWithValue(42);
    }

    @Test
    public void l2_manualExceptionCompletion() {
        CompletableFuture<Integer> input = new CompletableFuture<>();
        CompletableFutures.L2_manualExceptionCompletion().accept(input);

        assertThat(input).isCompletedExceptionally();
    }

    @Test
    public void l3_runAsync() {
        CompletableFuture<User> result = CompletableFutures.L3_runAsync().apply(42);

        assertThat(result.join().getId()).isEqualTo(42);
    }

    @Test
    public void l4_runAsyncOnCustomPool() {
        AtomicInteger counter = new AtomicInteger();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        CompletableFuture<User> result = CompletableFutures
          .L4_runAsyncOnACustomPool().apply(42, new CountingExecutorService(executor, counter));

        assertThat(result.join().getId()).isEqualTo(42);
        assertThat(counter.get()).isGreaterThan(0);
    }

    @Test
    public void l5_runAsyncAndCombine() {
        List<Integer> list = CompletableFutures.L5_runAsyncAndCombine().apply(1, 2).join()
          .stream()
          .map(User::getId)
          .collect(Collectors.toList());

        assertThat(list).containsExactly(1, 2);
    }

    @Test
    public void l6_composeFutures() {
        String result = CompletableFutures.L6_composeFutures().apply(1).join();

        assertThat(result)
          .contains("Street")
          .contains("1");
    }

    @Test
    public void l7_returnValueOfTheFirstCompleted() {
        BiFunction<CompletableFuture<String>, CompletableFuture<String>, String> f = CompletableFutures
          .L7_returnValueOfTheFirstCompleted();

        CompletableFuture<String> f1 = new CompletableFuture<>();
        CompletableFuture<String> f2 = new CompletableFuture<>();

        f2.complete("42");
        String result = f.apply(f1, f2);

        assertThat(result).isEqualTo("42");
    }
}