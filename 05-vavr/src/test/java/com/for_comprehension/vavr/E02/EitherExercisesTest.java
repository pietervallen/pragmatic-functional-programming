package com.for_comprehension.vavr.E02;

import org.junit.Test;

import java.net.URI;
import java.util.NoSuchElementException;

import static io.vavr.control.Either.left;
import static io.vavr.control.Either.right;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class EitherExercisesTest {

    @Test
    public void l1_stringToEitherUri() {
        assertThat(EitherExercises.L1_stringToEitherUri().apply("foo").isRight()).isTrue();
        assertThat(EitherExercises.L1_stringToEitherUri().apply(":").isLeft()).isTrue();
    }

    @Test
    public void l2_eitherToUri() {
        assertThat(EitherExercises.L2_eitherToUri().apply(left(new EitherExercises.ErrorObject(""))))
          .isEqualTo("http://google.com");
        assertThat(EitherExercises.L2_eitherToUri().apply(right(URI.create("foo")))).isEqualTo("foo");
    }

    @Test
    public void l3_eitherToErrorMessage() {
        assertThat(EitherExercises.L3_eitherToErrorMessage().apply(left(new EitherExercises.ErrorObject("foo"))))
          .isEqualTo("foo");

        assertThatThrownBy(() -> EitherExercises.L3_eitherToErrorMessage().apply(right(URI.create("foo"))))
          .isInstanceOf(NoSuchElementException.class);
    }
}
