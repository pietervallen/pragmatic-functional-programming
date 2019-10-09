package com.for_comprehension.stream.E02;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

class DynamicStreamFiltering {

    public static void main(String[] args) {
    }

    /**
     * Implement a function that takes a stream and filters it with a provided list of predicates
     */
    @SafeVarargs
    public static <T> UnaryOperator<Stream<T>> filterWith(Predicate<T>... predicates) {
        return (Stream<T> stream) -> {
            Stream<T> temp = stream;
            for (Predicate<T> predicate : predicates) {
                temp = temp.filter(predicate);
            }
            return temp;
        };
    }
}