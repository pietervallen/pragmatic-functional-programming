package com.for_comprehension.function;

import org.junit.Test;

import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

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
        Function<Integer, Integer> fun = integer -> 42 + 24;

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
}
