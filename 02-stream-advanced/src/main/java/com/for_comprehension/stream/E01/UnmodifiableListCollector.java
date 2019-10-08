package com.for_comprehension.stream.E01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toCollection;

public class UnmodifiableListCollector<T> implements Collector<T, ArrayList<T>, List<T>> {

    static <T> Collector<T, ?, List<T>> toUnmodifiableList() {
        return new UnmodifiableListCollector<>();
    }

    /**
     * Throws {@link UnsupportedOperationException} since {@link java.util.stream.Collectors#toCollection(Supplier)}
     * assumes that the target is mutable
     */
    public static void main(String[] args) {
        List<Integer> list = Stream.of(42).collect(toCollection(() -> Collections.unmodifiableList(new ArrayList<>())));
        // see test: l1_collectImmutable
    }

    @Override
    public Supplier<ArrayList<T>> supplier() {
        return null;
    }

    @Override
    public BiConsumer<ArrayList<T>, T> accumulator() {
        return null;
    }

    @Override
    public BinaryOperator<ArrayList<T>> combiner() {
        return (ts, ts2) -> null;
    }

    @Override
    public Function<ArrayList<T>, List<T>> finisher() {
        return null;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return EnumSet.noneOf(Characteristics.class);
    }
}