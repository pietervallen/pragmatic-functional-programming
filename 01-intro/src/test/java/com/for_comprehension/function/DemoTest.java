package com.for_comprehension.function;

import org.junit.Test;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Optional/Stream/CompletableFuture
// Lambda Expressions
// Function Composition
// Functional Interfaces/Lambda Expressions Under The Hood
public class DemoTest {

    @Test
    public void example_0() throws Exception {
        Runnable hello_inner_class = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello from another thread");
            }
        };

        Runnable hello_lambda = () -> System.out.println("Hello from another thread");

        Thread thread = new Thread(hello_inner_class);

        thread.start();
    }

    @Test
    public void example_2() throws Exception {
        Function<Integer, Integer> fun = integer -> integer * 2;

        // Function<Integer, Void>
        Consumer<Integer> consumer = integer -> System.out.println(integer);

        // Function<Void, Integer>
        Supplier<Integer> supplier = () -> 42;

        // BiFunction<Integer, Integer, Integer>
        BinaryOperator<Integer> sum = (i1, i2) -> i1 + i2;

        // Function<Integer, Integer>
        UnaryOperator<Integer> unaryOperator = i -> i + 1;

        // Function<Void, Void>
        Runnable runnable = () -> System.out.println();

        // Function<Integer, Boolean>
        Predicate<Integer> predicate = i -> i > 42;
    }

    @Test
    public void example_3() throws Exception {
        List<Integer> collect = Stream.of(42)
          .filter(belowAge("USA"))
          .collect(Collectors.toList());
    }

    @Test
    public void example_4() throws Exception {
        foo1((Supplier) () -> 42);
        foo1((Callable) () -> 42);
    }

    private void foo1(Supplier runnable) {
    }

    private void foo1(Callable runnable) {
    }

    private Predicate<Integer> belowAge(String country) {
        return u -> false;
    }
}
