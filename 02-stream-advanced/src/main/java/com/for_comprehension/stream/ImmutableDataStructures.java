package com.for_comprehension.stream;

import io.vavr.collection.List;

class ImmutableDataStructures {
    public static void main(String[] args) {
        List<Integer> list = List.of(0);
        List<Integer> list1 = list.append(1);
        System.out.println(list);
        System.out.println(list1);
    }
}
