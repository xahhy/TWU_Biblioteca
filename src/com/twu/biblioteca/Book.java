package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Book {
    private String author;
    private String publishedYear;
    public String name;
    public boolean checkedOut=false;
    public HashSet<User> owners = new HashSet<User>();

    Book(String name, String author, String publishedYear) {
        this.name = name;
        this.author = author;
        this.publishedYear = publishedYear;
    }

    Book(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.join("\t", this.name, this.author, this.publishedYear);
    }

    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }
}
