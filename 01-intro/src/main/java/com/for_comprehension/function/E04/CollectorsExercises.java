package com.for_comprehension.function.E04;

import com.sun.javafx.UnmodifiableArrayList;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

class CollectorsExercises {

    /**
     * Collect elements to a {@link List} instance
     */
    static Function<List<String>, List<String>> L1_toList() {
        return ArrayList::new;
    }

    /**
     * Collect elements to a {@link LinkedList} instance
     */
    static Function<List<String>, LinkedList<String>> L2_toLinkedList() {
        return LinkedList::new;
    }

    /**
     * Collect elements to a {@link List} wrapped in {@link Collections#unmodifiableList(List)} instance
     */
    static Function<List<String>, List<String>> L3_unmodifiable() {
        return Collections::unmodifiableList;
    }

    /**
     * Collect elements to a {@link Map} instance with uppercased elements as keys and their corresponding lengths as values
     * and resolve potential collisions
     */
    static Function<List<String>, Map<String, Integer>> L4_toMap() {
        return list -> list.stream()
                .collect(Collectors.toMap(String::toUpperCase, String::length, (i, i2) -> i));
    }

    /**
     * Collect elements to a {@link TreeMap} instance with elements as keys and their corresponding lengths as values
     * and resolve potential collisions by picking any of the strings
     */
    static Function<List<String>, Map<String, Integer>> L5_toTreeMap() {
        return list -> list.stream()
                .collect(Collectors.toMap(String::toUpperCase, String::length, (i, i2) -> i, TreeMap::new));
    }

    /**
     * Collect Map elements to a JSON String instance
     * {@link Collectors#joining(CharSequence, CharSequence, CharSequence)}
     */
    static Function<Map<String, String>, String> L6_toJson() {
        return input -> {

          String output =  input.forEach();

//                    input.forEach((k,v) -> return k+v);
return output;
//                    Collectors.joining(":","{","}"))
//                    .;
        };
    }


    /**
     * Group Strings of the same length
     * {@link Collectors#groupingBy(Function)}
     */
    static Function<List<String>, Map<Integer, List<String>>> L7_groupStrings() {
        return input -> {
            return null;
        };
    }

    /**
     * Group Strings of the same length to a {@link TreeMap}
     */
    static Function<List<String>, TreeMap<Integer, List<String>>> L8_groupStrings() {
        return input -> {
            return null;
        };
    }

    /**
     * Group Strings of the same length into a comma-delimited String
     * {@link Collectors#groupingBy(Function)}
     */
    static Function<List<String>, Map<Integer, String>> L9_groupStrings() {
        return input -> {
            return null;
        };
    }
}
