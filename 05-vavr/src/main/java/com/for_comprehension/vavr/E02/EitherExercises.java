package com.for_comprehension.vavr.E02;

import io.vavr.control.Either;

import java.net.URI;
import java.util.NoSuchElementException;
import java.util.function.Function;

class EitherExercises {

    /**
     * Encapsulate {@link java.net.URI}'s creation within
     * a {@link io.vavr.control.Either} object using {@link URI#create(String)}
     *
     * ... if an {@link Exception} gets throws, return {@link io.vavr.control.Either}
     * containing "{@code new ErrorObject("Something went wrong")}"
     */
    static Function<String, Either<ErrorObject, URI>> L1_stringToEitherUri() {
        return string -> {
            return null;
        };
    }

    /**
     * Handle received {@link io.vavr.control.Either} object by returning the containing value in {@link String} representation
     * or defaulting to {@code "http://google.com"} if {@link io.vavr.control.Either} did not contain the errorMessage
     */
    static Function<Either<ErrorObject, URI>, String> L2_eitherToUri() {
        return either -> null;
    }

    /**
     * Handle received {@link io.vavr.control.Either} object by returning the containing value in {@link String} representation
     * or defaulting to {@code "http://google.com"} if {@link io.vavr.control.Either} did not contain the errorMessage
     */
    static Function<Either<ErrorObject, URI>, String> L3_eitherToErrorMessage() {
        return either -> null;
    }

    static class ErrorObject {
        private final String errorMessage;

        public ErrorObject(String result) {
            this.errorMessage = result;
        }

        public String getErrorMessage() {
            return errorMessage;
        }
    }
}
