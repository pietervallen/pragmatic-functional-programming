package com.for_comprehension.stream.E03;

import org.junit.Test;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class SeeSawStreamTest {
    @Test
    public void l1_shouldSeesaw() {
        final List<Integer> result = SeeSawStream.seesawStream(5, 1)
          .limit(7)
          .collect(toList());

        // then
        assertThat(result).containsExactly(5, 6, 4, 7, 3, 8, 2);
    }

}