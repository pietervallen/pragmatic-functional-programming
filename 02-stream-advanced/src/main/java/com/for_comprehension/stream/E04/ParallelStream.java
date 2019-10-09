package com.for_comprehension.stream.E04;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.function.Function;

import static java.util.concurrent.CompletableFuture.supplyAsync;
import static java.util.stream.Collectors.toList;

class ParallelStream {

    static <T, R> List<R> parallelSync(Collection<T> input, Function<T, R> task, ExecutorService executor) {
        return parallelAsync(input, task, executor).join();
    }

    static <T, R> CompletableFuture<List<R>> parallelAsync(Collection<T> input, Function<T, R> task, ExecutorService executor) {
        List<CompletableFuture<R>> results = input.stream()
          .map(elem -> supplyAsync(() -> task.apply(elem), executor))
          .collect(toList());

        return CompletableFuture.allOf(results.toArray(new CompletableFuture[0]))
          .thenApply(vvoid -> results.stream()
            .map(CompletableFuture::join)
            .collect(toList()));
    }
}
