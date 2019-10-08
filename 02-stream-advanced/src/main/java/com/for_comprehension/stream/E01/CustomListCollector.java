package com.for_comprehension.stream.E01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class CustomListCollector<T> implements Collector<T, List<T>, List<T>> {

    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 2, 3);

        List<Integer> collect = integers.stream()
          .collect(toCustomList());

        System.out.println(collect);
    }

    private static CustomListCollector<Integer> toCustomList() {
        return new CustomListCollector<>();
    }

    @Override
    public Supplier<List<T>> supplier() {
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return (ts, t) -> ts.add(t);
    }

    @Override
    public BinaryOperator<List<T>> combiner() {
        return (ts, ts2) -> {
            return Stream.concat(ts.stream(), ts2.stream())
              .collect(Collectors.toList());
        };
    }

    @Override
    public Function<List<T>, List<T>> finisher() {
        return i -> i;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.emptySet();
    }
}
