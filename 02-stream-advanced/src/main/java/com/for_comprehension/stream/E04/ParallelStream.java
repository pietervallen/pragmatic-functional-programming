package com.for_comprehension.stream.E04;

import com.sun.xml.internal.bind.v2.util.CollisionCheckStack;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.concurrent.CompletableFuture.allOf;
import static java.util.concurrent.CompletableFuture.supplyAsync;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

class ParallelStream {

    static <T, R> List<R> parallelSync(Collection<T> input, Function<T, R> task, ExecutorService executor) {
        return input.stream()
                .map(elem -> CompletableFuture.supplyAsync(() -> task.apply(elem), executor))
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    static <T, R> CompletableFuture<List<R>> parallelAsync(Collection<T> input, Function<T, R> task, ExecutorService executor) {
        return null;
    }
}
