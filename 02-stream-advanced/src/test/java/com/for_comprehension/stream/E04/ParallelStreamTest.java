package com.for_comprehension.stream.E04;

import org.junit.Test;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

public class ParallelStreamTest {

    @Test
    public void parallelSync() {
        AtomicInteger counter = new AtomicInteger();
        List<Integer> list = Arrays.asList(1, 2, 3, 4);

        long seconds = countSeconds(() -> ParallelStream.parallelSync(list, i -> withDelay(i), new CountingExecutorService(Executors.newFixedThreadPool(4), counter)));

        assertThat(seconds).isEqualTo(1);
        assertThat(counter.get()).isEqualTo(4);
    }

    @Test
    public void parallelAsync() {

        ExecutorService executor = Executors.newFixedThreadPool(4);
        List<Integer> list = Arrays.asList(1, 2, 3, 4);

        long seconds = countSeconds(() -> ParallelStream.parallelAsync(list, i -> withDelay(i), executor).join());

        assertThat(seconds).isEqualTo(1);
    }

    private static long countSeconds(Runnable action) {
        LocalTime before = LocalTime.now();
        action.run();
        LocalTime after = LocalTime.now();
        return Duration.between(before, after).getSeconds();
    }

    private static <T> T withDelay(T value) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // ignore shamefully
        }

        return value;
    }
}