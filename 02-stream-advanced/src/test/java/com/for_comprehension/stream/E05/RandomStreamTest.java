package com.for_comprehension.stream.E05;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

import static org.assertj.core.api.Assertions.assertThat;

public class RandomStreamTest {

    @Test
    public void shouldReturnRandom() {
        ArrayList<Integer> input = IntStream.range(0, 10).boxed().collect(Collectors.toCollection(ArrayList::new));

        List<Integer> result = StreamSupport.stream(new RandomStream<>(input), false)
          .collect(Collectors.toList());

        assertThat(result)
          .doesNotContainNull()
          .containsOnlyElementsOf(input)
          .doesNotContainSequence(input);
    }
}