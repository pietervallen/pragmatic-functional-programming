package com.for_comprehension.stream.E06;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Spliterator;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static java.util.concurrent.CompletableFuture.anyOf;

/**
 * @author Grzegorz Piwowarek
 */
final class CompletionOrderSpliterator<T> implements Spliterator<T> {

    CompletionOrderSpliterator(Collection<CompletableFuture<T>> futures) {
    }

    @Override
    public boolean tryAdvance(Consumer<? super T> action) {
        return false;
    }

    @Override
    public Spliterator<T> trySplit() {
        return null;
    }

    @Override
    public long estimateSize() {
        return 42;
    }

    @Override
    public int characteristics() {
        return 42;
    }
}
