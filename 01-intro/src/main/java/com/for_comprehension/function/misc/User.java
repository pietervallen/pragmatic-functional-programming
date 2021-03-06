package com.for_comprehension.function.misc;

public class User {
    private final Integer id;
    private final String name;

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;

        System.out.println("constructing an user: " + id + " - " + name);
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "User{" +
          "id=" + id +
          ", name='" + name + '\'' +
          '}';
    }
}
