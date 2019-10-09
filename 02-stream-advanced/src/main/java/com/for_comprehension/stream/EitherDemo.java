package com.for_comprehension.stream;

import io.vavr.control.Either;

class EitherDemo {
    public static void main(String[] args) {

        Either<Integer, String> left = Either.left(42);
        left.bimap(
          i -> i + 2,
          i -> i + "ffoo");

        left.left()
          .map(i -> i * 2);
        left.right()
          .map(i -> i + "asd");
    }
}
