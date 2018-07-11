package com.twu.biblioteca;

public class Book {
    public String name;

    Book(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
