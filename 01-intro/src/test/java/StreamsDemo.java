import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

// Stream<T>
// Stream.of(), Stream.generate(), Stream.iterate(), Collections#stream()
// Stream#flatMap/map
public class StreamsDemo {

    @Test
    public void example_1() throws Exception {
        Stream.of(1, 2, 3, 2)
          .distinct()
          .peek(s -> System.out.println("peeking at " + s))
          .map(i -> i + 1)
          .map(i -> i * 2)
          .flatMap(i -> Stream.of(i, i))
          .filter(i -> i % 2 == 0)
          .forEach(System.out::println);
    }

    @Test(expected = IllegalStateException.class)
    public void example_2() throws Exception {
        List<Integer> integers = asList(1, 2, 3);

        Stream<Integer> stream = integers.stream();
        stream.forEach(System.out::println);
        stream.forEach(System.out::println);
    }

    @Test
    public void example_3() throws Exception {
        List<Integer> integers = asList(1, 2, 3);

        Optional<Integer> integer = integers.stream()
          .findAny()
          .map(i -> i * 2);

        List<Integer> collect = integers.stream()
          .collect(Collectors.toList());

        Set<Integer> collect2 = integers.stream()
          .collect(toSet());

        Map<Integer, List<String>> collect1 =
          Stream.of("a", "bb", "ccc", "dd")
          .collect(Collectors.groupingBy(s -> s.length()));

        System.out.println(collect1);

        Optional<Integer> reduce = integers.stream()
          .reduce((i1, i2) -> i1 + i2);

        Integer reduce2 = integers.stream()
          .reduce(0, (i1, i2) -> i1 + i2);

        integers.stream()
          .forEach(s -> System.out.println(s));

        System.out.println(collect);
    }

    @Test
    public void example_4() throws Exception {
        List<Integer> integers = asList(1, 2, 3);

        integers.stream()
          .forEach(i -> sendEmail(i));

        int size = integers.size();
    }

    @Test
    public void example_5() throws Exception {
        List<List<Integer>> integers =
          asList(asList(1), asList(2,3), asList());

        System.out.println(integers);

        List<Integer> collect = integers.stream()
          .flatMap(list -> list.stream())
          .collect(Collectors.toList());

        System.out.println(collect);
    }

    @Test
    public void example_6() throws Exception {
        List<Integer> integers = asList(1, 2, 3);
        System.out.println(integers);

        List<Integer> collect = integers.stream()
          .flatMap(elem -> Stream.of(elem, elem, 42))
          .collect(Collectors.toList());

        System.out.println(collect);
    }

    @Test
    public void example_7() throws Exception {
        List<Integer> integers = asList(1, 2, 3, 4, 5);

        List<Integer> collect = integers.stream()
          .parallel()
          .map(i -> {
              try {
                  Thread.sleep(1000);
              } catch (Exception e) {}
              System.out.println(Thread.currentThread().getName() + " : " + i);
              return i;
          })
          .collect(Collectors.toList());
    }

    @Test
    public void example_8() throws Exception {
        Stream.iterate(0, i -> i + 1)
          .skip(3)
          .limit(10)
          .forEach(System.out::println);
    }

    @Test
    public void example_9() throws Exception {
        IntStream.iterate(0, i -> i + 1)
          .boxed()
          .collect(Collectors.toList());
    }

    @Test
    public void example_10() throws Exception {
        List<Integer> integers = asList(1, 2, 3);

        Set<Integer> collect = integers.stream()
          .collect(Collectors.collectingAndThen(toSet(), Collections::unmodifiableSet));

        collect.add(42);
    }

    @Test
    public void example_11() throws Exception {

        List<String> strings = asList("aaa", "bbb", "cc", "d", "bbb");

        Map<String, Integer> collect = strings.stream()
          .collect(Collectors.toMap(String::toUpperCase, String::length, (i, i2) -> i, TreeMap::new));

        System.out.println(collect);
    }

    private Integer sendEmail(Integer i) {
        System.out.println("sending email for " + i);
        return i;
    }
}
