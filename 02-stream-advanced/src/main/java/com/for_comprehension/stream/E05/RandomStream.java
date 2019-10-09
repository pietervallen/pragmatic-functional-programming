package com.for_comprehension.stream.E05;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.RandomAccess;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Supplier;

class RandomStream<T, LIST extends RandomAccess & List<T>> // intersection types
  implements Spliterator<T> {

    private final List<T> source;

    RandomStream(LIST source) {
        Objects.requireNonNull(source, "source can't be null");

        this.source = new ArrayList<>(source);
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