package com.for_comprehension.stream;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class StreamFlatMapDemo {

    public static void main(String[] args) {
        Optional<Integer> collect = Stream.of(42)
          .flatMap(f -> Stream.iterate(0, i -> i + 1))
          .map(i -> {
              System.out.println("sending an email to user: " + i);
              return i;
          })
          .peek(System.out::println)
          .findAny();
    }
}
