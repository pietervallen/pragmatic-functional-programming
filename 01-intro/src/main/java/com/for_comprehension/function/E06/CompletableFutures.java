package com.for_comprehension.function.E06;

import com.for_comprehension.function.misc.User;
import com.for_comprehension.function.misc.UsersClient;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

class CompletableFutures {

    private static final ExecutorService executor = Executors.newFixedThreadPool(20);

    private static final UsersClient usersClient = new UsersClient();

    /**
     * Complete incoming {@link CompletableFuture} manually with value 42
     */
    static Consumer<CompletableFuture<Integer>> L1_manualCompletion() {
        return f -> {
        };
    }

    /**
     * Complete incoming {@link CompletableFuture} exceptionally with a {@link NullPointerException}
     */
    static Consumer<CompletableFuture<Integer>> L2_manualExceptionCompletion() {
        return f -> {
        };
    }

    /**
     * Run {@link com.for_comprehension.function.misc.UsersClient#getUserById(Integer)} asynchronously
     * Use the provided id to look up the user
     *
     *
     */
    static Function<Integer, CompletableFuture<User>> L3_runAsync() {
        return id -> {
            return CompletableFuture.supplyAsync(() -> new UsersClient().getUserById(id));
        };
    }

    /**
     * Run {@link com.for_comprehension.function.misc.UsersClient#getUserById(Integer)} asynchronously on a given thread pool
     * Use the provided id to look up the user
     *
     * Essentially, the same as above + execution on a provided thread pool
     */
    static BiFunction<Integer, ExecutorService, CompletableFuture<User>> L4_runAsyncOnACustomPool() {
        return (id, executor) -> {
            return CompletableFuture.supplyAsync(() -> new UsersClient().getUserById(id), executor);
        };
    }

    /**
     * Run {@link com.for_comprehension.function.misc.UsersClient#getUserById(Integer)}
     * on two different ids and return both users in a List
     *
     * {@link CompletableFuture#thenCombine(CompletionStage, BiFunction)}
     *
     */
    static BiFunction<Integer, Integer, CompletableFuture<List<User>>> L5_runAsyncAndCombine() {
        return (id, id2) -> {
            CompletableFuture<User> u1 = CompletableFuture
              .supplyAsync(() -> usersClient.getUserById(id));
            CompletableFuture<User> u2 = CompletableFuture
              .supplyAsync(() -> usersClient.getUserById(id2));

            return u1.thenCombine(u2, (user, user2) -> Arrays.asList(user, user2));
        };
    }

    /**
     * Run {@link com.for_comprehension.function.misc.UsersClient#getUserById(Integer)}
     * on two different ids and return the one that returns first!
     *
     * {@link CompletableFuture#thenCombine(CompletionStage, BiFunction)}
     *
     */
    static Function<Integer, CompletableFuture<String>> L6_composeFutures() {
        return (id) -> {
            return null;
        };
    }

    /**
     * Given two futures, return the result of whichever completes first
     *
     * {@link CompletableFuture#anyOf(CompletableFuture[])}
     *
     */
    static <T> BiFunction<CompletableFuture<T>, CompletableFuture<T>, T> L7_returnValueOfTheFirstCompleted() {
        return (f1, f2) -> {
            return null;
        };
    }

}
