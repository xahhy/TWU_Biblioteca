package com.twu.biblioteca;

public class Book {
    private String author;
    private String publishedYear;
    public String name;
    public boolean checkedOut=false;

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
