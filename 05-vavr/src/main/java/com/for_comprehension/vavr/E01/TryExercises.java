package com.for_comprehension.vavr.E01;

import io.vavr.control.Try;

import java.net.URI;
import java.util.function.Function;
import java.util.function.Supplier;

class TryExercises {

    /**
     * Encapsulate {@link java.net.URI}'s creation within
     * a {@link io.vavr.control.Try} object using {@link URI#create(String)}
     */
    static Function<String, Try<URI>> L1_stringToUri() {
        return null;
    }

    /**
     * Encapsulate {@link java.net.URI}'s creation within
     * a {@link io.vavr.control.Try} object using {@link URI#create(String)}
     *
     * ... and default to {@code "http://google.com"} if something goes wrong.
     *
     * hint: {@link Try#getOrElse(Supplier)}
     */
    static Function<String, URI> L2_stringToUriOrDefault() {
        return null;
    }

    /**
     * Encapsulate {@link java.net.URI}'s creation within
     * a {@link io.vavr.control.Try} object using {@link URI#create(String)}
     *
     * ... and default to {@code "http://google.com"} if something goes wrong.
     *
     * hint: {@link Try#recoverWith(Function)}
     */
    static Function<String, Try<URI>> L3_stringToUriOrRecover() {
        return null;
    }
}
