package com.for_comprehension.stream.option;

import java.util.function.Function;

public abstract class Option<T> {

    private Option() {
    }

    static class Some<T> extends Option<T> {

        private final T value;

        Some(T value) {
            this.value = value;
        }

        @Override
        public <R> Option<R> flatMap(Function<T, Option<R>> mapper) {
            return null;
        }

        @Override
        public <R> Option<R> map(Function<T, R> mapper) {
            return null;
        }

        @Override
        public T orElse(T fallback) {
            return null;
        }
    }

    static class None<T> extends Option<T> {

        @Override
        public <R> Option<R> flatMap(Function<T, Option<R>> mapper) {
            return null;
        }

        @Override
        public <R> Option<R> map(Function<T, R> mapper) {
            return null;
        }

        @Override
        public T orElse(T fallback) {
            return null;
        }
    }

    public static <T1> Option<T1> of(T1 value) {
        return value == null ? new None<>() : new Some<>(value);
    }

    public abstract <R> Option<R> flatMap(Function<T, Option<R>> mapper);

    public abstract <R> Option<R> map(Function<T, R> mapper);

    public abstract T orElse(T fallback);
}
