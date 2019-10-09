package com.for_comprehension.function.E06;

import com.for_comprehension.function.misc.User;
import com.for_comprehension.function.misc.UsersClient;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.concurrent.CompletableFuture.completedFuture;

class CompletableFutures {

    public static void main(String[] args) {
        CompletableFuture<Integer> f1 = completedFuture(1);
        CompletableFuture<Integer> f2 = completedFuture(2);

        f1.thenApply(i -> i + 1); // f1.map(i -> i + 1)
        f1.thenCompose(i -> f2); // f1.flatMap(i -> f2)

        CompletableFuture<User> userCompletableFuture = f1.thenCompose(usersClient::getUserByIdAsync);

        CompletableFuture<String> foo = completedFuture("foo");
        CompletableFuture<User> user = completedFuture(new User(42, ""));
        CompletableFuture<Integer> integerCompletableFuture = completedFuture(42);

        CompletableFuture<Void> objectCompletableFuture = CompletableFuture
          .allOf(foo, user, integerCompletableFuture);

        objectCompletableFuture.join();

        List<CompletableFuture<Integer>> collect = Stream.of(1, 2, 3)
          .map(i -> completedFuture(i))
          .collect(Collectors.toList());

        CompletableFuture.allOf(collect.toArray(new CompletableFuture[0]));
    }

    private static final ExecutorService executor = Executors.newFixedThreadPool(20);

    private static final UsersClient usersClient = new UsersClient();

    /**
     * Complete incoming {@link CompletableFuture} manually with value 42
     */
    static Consumer<CompletableFuture<Integer>> L1_manualCompletion() {
        return f -> {
            f.complete(42);
        };
    }

    /**
     * Complete incoming {@link CompletableFuture} exceptionally with a {@link NullPointerException}
     */
    static Consumer<CompletableFuture<Integer>> L2_manualExceptionCompletion() {
        return f -> {
            f.completeExceptionally(new NullPointerException());
        };
    }

    /**
     * Run {@link com.for_comprehension.function.misc.UsersClient#getUserById(Integer)} asynchronously
     * Use the provided id to look up the user
     */
    static Function<Integer, CompletableFuture<User>> L3_runAsync() {
        return id -> {
            return CompletableFuture.supplyAsync(() -> usersClient.getUserById(id));
        };
    }

    /**
     * Run {@link com.for_comprehension.function.misc.UsersClient#getUserById(Integer)} asynchronously on a given thread pool
     * Use the provided id to look up the user
     * <p>
     * Essentially, the same as above + execution on a provided thread pool
     */
    static BiFunction<Integer, ExecutorService, CompletableFuture<User>> L4_runAsyncOnACustomPool() {
        return (id, executor) -> {
            return CompletableFuture.supplyAsync(() -> usersClient.getUserById(id), executor);
        };
    }

    /**
     * Run {@link com.for_comprehension.function.misc.UsersClient#getUserById(Integer)}
     * on two different ids and return both users in a List
     * <p>
     * {@link CompletableFuture#thenCombine(CompletionStage, BiFunction)}
     */
    static BiFunction<Integer, Integer, CompletableFuture<List<User>>> L5_runAsyncAndCombine() {
        return (id, id2) -> {
            CompletableFuture<User> user1CompletableFuture = CompletableFuture.supplyAsync(() -> usersClient.getUserById(id));
            CompletableFuture<User> user2CompletableFuture = CompletableFuture.supplyAsync(() -> usersClient.getUserById(id2));
            return user1CompletableFuture.thenCombine(user2CompletableFuture, (user1, user2) ->  Arrays.asList(user1, user2));
        };
    }

    /**
     * Return a combined future which completes with a value of the first completed future
     * <p>
     * {@link CompletableFuture#thenCombine(CompletionStage, BiFunction)}
     */
    static BiFunction<CompletableFuture<Integer>, CompletableFuture<Integer>, CompletableFuture<Integer>> L6_composeFutures() {
        return (f1, f2) -> {
            return null;
        };
    }

    /**
     * Given two futures, return the result of whichever completes first
     * <p>
     * {@link CompletableFuture#anyOf(CompletableFuture[])}
     */
    static <T> BiFunction<CompletableFuture<T>, CompletableFuture<T>, T> L7_returnValueOfTheFirstCompleted() {
        return (f1, f2) -> {
            return null;
        };
    }

    /**
     * Given a list of futures, convert it to a future containing a list of all results
     * <p>
     * {@link CompletableFuture#allOf(CompletableFuture[])}
     */
    static <T> Function<List<CompletableFuture<T>>, CompletableFuture<List<T>>> L8_returnResultsAsList() {
        return futures -> {
            return null;
        };
    }
}
