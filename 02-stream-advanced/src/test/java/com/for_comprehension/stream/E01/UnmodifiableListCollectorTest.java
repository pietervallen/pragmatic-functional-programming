package com.for_comprehension.stream.E01;

import org.junit.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class UnmodifiableListCollectorTest {

    @Test
    public void l1_collectImmutable() {
        List<Integer> result = Stream.of(42).collect(UnmodifiableListCollector.toUnmodifiableList());

        assertThat(result).containsExactly(42);
    }

}