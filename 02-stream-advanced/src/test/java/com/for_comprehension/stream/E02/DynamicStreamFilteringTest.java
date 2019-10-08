package com.for_comprehension.stream.E02;

import org.junit.Test;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class DynamicStreamFilteringTest {

    @Test
    public void l1_shouldFilter() {
        // given
        Predicate<Integer> p1 = i -> i % 2 == 1;
        Predicate<Integer> p2 = i -> i > 10;
        Predicate<Integer> p3 = i -> i < 20;

        List<Integer> values = IntStream.range(0, 50).boxed().collect(toList());

        List<Integer> result = DynamicStreamFiltering.filterWith(p1, p2, p3).apply(values.stream()).collect(toList());

        assertThat(result).containsExactly(11, 13, 15, 17, 19);
    }
}