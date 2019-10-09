package com.for_comprehension.vavr.E01;

import io.vavr.control.Try;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

public class TryExercisesTest {
    private static final Logger log = LoggerFactory.getLogger(TryExercisesTest.class);

    @Test
    public void l1_stringToUri() {
        assertThat(TryExercises.L1_stringToUri().apply("www.google.com").isSuccess()).isTrue();
        assertThat(TryExercises.L1_stringToUri().apply(":").isFailure()).isTrue();
    }

    @Test
    public void l3_stringToUriOrDefault() {
        assertThat(TryExercises.L2_stringToUriOrDefault().apply("foo.com")).isEqualTo(URI.create("foo.com"));
        assertThat(TryExercises.L2_stringToUriOrDefault().apply(":")).isEqualTo(URI.create("http://google.com"));
    }

    @Test
    public void l3_stringToUriOrRecover() {
        assertThat(TryExercises.L3_stringToUriOrRecover().apply("foo.com").get()).isEqualTo(URI.create("foo.com"));
        assertThat(TryExercises.L3_stringToUriOrRecover().apply(":").get()).isEqualTo(URI.create("http://google.com"));
    }

}
