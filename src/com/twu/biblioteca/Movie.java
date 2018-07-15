package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Movie {
    private String publishedYear;
    public String name;
    private String director;
    private Float rating;
    public boolean checkedOut = false;
    public HashSet<User> owners = new HashSet<User>();

    Movie(String name, String director, String publishedYear, Float rating) {
        this.name = name;
        this.publishedYear = publishedYear;
        this.director = director;
        this.rating = rating;
    }

    Movie(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.join("\t", this.name, this.director, this.publishedYear, String.format("%.1f", this.rating));
    }

    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }
}
