package com.for_comprehension.function;

import com.for_comprehension.function.misc.User;
import org.junit.Test;

import java.util.Optional;

public class OptionalDemo {
    @Test
    void example_1() throws Exception {

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






    public static Optional<User> findById(int id) {
        return Optional.of(new User(42, "John"));
    }

}
