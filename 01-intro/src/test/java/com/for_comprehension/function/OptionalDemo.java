package com.for_comprehension.function;

import com.for_comprehension.function.misc.User;
import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;

public class OptionalDemo {
    @Test
    public void example_1() throws Exception {

        Optional<User> maybeJohn = findById(42);
        Optional<String> maybeName = maybeJohn.map(user -> user.getName());
        Optional<String> value = maybeName.map(s -> s.toUpperCase());
        String result = value.orElse("some default name");

        String result2 = findById(42)        // 1
          .map(user -> user.getName())       // 2
          .filter(s -> !s.isEmpty())
          .map(s -> s.toUpperCase())         // 3
          .orElse("some default name"); // 4

        /*if (maybeJohn.isPresent()) {
            User user = maybeJohn.get();
            if (user.getName() != null) {
                String name = user.getName();
                return name.toUpperCase();
            }
        }*/
    }

    @Test
    public void example_2() throws Exception {
        Optional<Integer> integer = Optional.ofNullable(42);
        System.out.println(integer);
        Optional<Integer> integerEmpty = Optional.ofNullable(null);
        System.out.println(integerEmpty);

        Optional<Integer> integer1 = Optional.of(42);
       //  Optional<Integer> integerNull = Optional.of(null); NPE!
    }

    @Test
    public void example_3() throws Exception {
        Optional<Integer> userId = findById(42)
          .map(u -> u.getId());

        Optional<Optional<User>> user = findById(42)
          .map(u -> findById(u.getId()));

        Optional<User> userFlat = findById(42)
          .flatMap(u -> findById(u.getId()));
    }

    @Test
    public void example_4() throws Exception {
        User user = findById(42)
          .orElseThrow(() -> new NoSuchElementException());
    }

    // orElse vs orElseGet (lazy)
    @Test
    public void example_5() throws Exception {
        User user2 = findById(421983)
          .orElseGet(() -> new User(12312, "foo"));
    }

    // Optional#get as a syntax sugar converting NPE to NoSuchElementException
    @Test
    public void example_6() throws Exception {
        User user = findById(42).get(); // we know that this user always exists
    }

    public static Optional<User> findById(int id) {
        if (id == 42) {
            return Optional.of(new User(42, "John"));
        } else return Optional.empty();
    }

}
