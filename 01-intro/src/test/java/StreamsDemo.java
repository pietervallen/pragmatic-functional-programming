import org.junit.Test;

import java.util.stream.Stream;

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
}
