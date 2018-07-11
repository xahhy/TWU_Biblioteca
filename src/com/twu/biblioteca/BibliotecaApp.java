package com.twu.biblioteca;

import java.util.ArrayList;

public class BibliotecaApp {

    ArrayList<Book> books = new ArrayList<Book>();

    public static void main(String[] args) {
        System.out.println("Hello, world!");
    }

    public static void welcome() {
        System.out.println("Welcome to Biblioteca!");
    }

    public void initBooks() {
        books.add(new Book("CleanCode"));
        books.add(new Book("DevOps Practice"));
    }
    public void listBooks() {
        System.out.println("Book Name");
        for (Book book: books) {
            System.out.println(book);
        }
    }
}
