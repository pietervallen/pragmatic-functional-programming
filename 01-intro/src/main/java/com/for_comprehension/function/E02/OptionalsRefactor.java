package com.for_comprehension.function.E02;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;
import java.util.Optional;
import java.util.function.Function;

class OptionalsRefactor {

    private Optional<Person> findPersonAdapter(int id) {
        return Optional.ofNullable(findPersonLegacy(id));
    }

    private Person findPersonLegacy(int id) {
        switch (id) {
            case 1:
                return new Person("James", 48, 193, LocalDate.of(2000, Month.NOVEMBER, 1));
            case 2:
                return new Person("John", 62, 169, LocalDate.of(1989, Month.OCTOBER, 21));
            case 0:
                return null;
            default:
                return null;
        }
    }

    private Optional<Person> findPersonOptional(int id) {
        switch (id) {
            case 1:
                return Optional.of(new Person("James", 48, 193, LocalDate.of(2000, Month.NOVEMBER, 1)));
            case 2:
                return Optional.of(new Person("John", 62, 169, LocalDate.of(1989, Month.OCTOBER, 21)));
            default:
                return Optional.empty();
        }
    }

    private Optional<String> findAddress(Person person) {
        return Optional.ofNullable(person)
          .map(personToAddress());
    }

    private Function<Person, String> personToAddress() {
        return p -> {
            if (p.getBirthDate().isAfter(LocalDate.of(2000, Month.JANUARY, 1))) {
                return "";
            }
            if (p.getBirthDate().isAfter(LocalDate.of(1980, Month.JANUARY, 1))) {
                return " Some St.   ";
            }

            return null;
        };
    }

    private String findAddressById(int id) {
        final Optional<Person> maybePerson = Optional.ofNullable(findPerson(id));

        maybePerson.

        if (maybePerson.isPresent()) {
            if (maybePerson.get().getHeight() > 168) {
                final Optional<String> maybeAddress = Optional.ofNullable(findAddress(maybePerson.get()));
                if (maybeAddress.isPresent()) {
                    return maybeAddress.get().trim();
                }
            }
        }
        return maybePerson.filter(person -> person.getHeight() > 168 );
    private Optional<String> findAddressById(int id) {
        return findPersonAdapter(id)
          .filter(p -> p.getHeight() > 168)
          .flatMap(p -> findAddress(p))
          .map(address -> address.trim());
    }

    // ***
    // DON"T CHANGE ANYTHING BEYOND THIS POINT
    // ***

    private class Person {
        private final String name;
        private final int weight;
        private final int height;
        private final LocalDate birthDate;

        private Person(String name, int weight, int height, LocalDate birthDate) {
            this.name = name;
            this.weight = weight;
            this.height = height;
            this.birthDate = birthDate;
        }

        public String getName() {
            return name;
        }

        public int getWeight() {
            return weight;
        }

        public int getHeight() {
            return height;
        }

        public LocalDate getBirthDate() {
            return birthDate;
        }
    }
}