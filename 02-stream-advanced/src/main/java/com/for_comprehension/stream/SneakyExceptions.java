package com.for_comprehension.stream;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

class SneakyExceptions {
    public static void main(String[] args) {
        Optional<URI> foo = Stream.of("foo")
          .map(toURI())
          .findAny();
    }

    private static Function<String, URI> toURI() {
        return f -> {
            try {
                return new URI(f);
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        };
    }

    static <T, R> Function<T, R> sneaky(ThrowingFunction<T, R> action) {
        return t -> {
            try {
                return action.apply(t);
            } catch (Exception e) {
                rethrow(e);
            }
            return null;
        };
    }

    static <T extends Throwable> void rethrow(Exception t) throws T {
        throw (T) t;
    }

    interface ThrowingFunction<T, R> {
        R apply(T t) throws Exception;
    }
}


