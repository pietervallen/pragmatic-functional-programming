package com.for_comprehension.stream;

import io.vavr.collection.List;
import io.vavr.control.Option;
import org.junit.Test;

public class VavrDemo {
    @Test
    public void example_1() throws Exception {
        List<Integer> list1 = List.of(1);
        List<Integer> list2 = List.of(2);
        List<Integer> integers = list1.appendAll(list2);
        System.out.println(integers);

        List<Option<Integer>> optionals = List.of(
          Option.of(1), Option.none(), Option.of(2), Option.of(3));

        List<Integer> integers1 = optionals
          .flatMap(i -> i);

        List<Integer> zeros = List.of(0, 1, 2, 3, 4);
        List<Integer> anotherList = List.of(10, 11, 12, 13, 14);
        List<Integer> map = zeros.map(anotherList);

        System.out.println(map);
        java.util.List<Integer> integers2 = integers1.toJavaList();
        java.util.List<Integer> integers3 = integers1.asJava();
    }

    @Test
    public void example_2() throws Exception {

        List<Integer> of = List.of(1, 2, 3, 4, 5);

        Option<Integer> integers = of.toStream()
          .map(i -> i * 2)
          .map(i -> i + 2)
          .map(i -> i + 2)
          .map(i -> i + 2)
          .map(i -> i + 2)
          .map(i -> i + 2)
          .map(i -> i + 2)
          .map(i -> i + 2)
          .map(i -> i + 2)
          .map(i -> i + 2)
          .headOption();
    }
}
