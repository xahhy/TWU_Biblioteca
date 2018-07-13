package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BibliotecaApp {

    ArrayList<Book> books = new ArrayList<Book>();

    public static void main(String[] args) {
        BibliotecaApp app = new BibliotecaApp();
        app.initBooks();
        app.initMenu();
    }

    public static void welcome() {
        System.out.println("Welcome to Biblioteca!");
    }

    public void initBooks() {
        books.add(new Book("CleanCode", "Robert C. Martin", "2012"));
        books.add(new Book("DevOps Practice", "Joakim Verona", "2016"));
    }

    public void listBooks() {
        System.out.println("Book Name\tAuthor\tPublished Year");
        List<Book> booksAvailable = books.stream().filter(book -> !book.checkedOut).collect(Collectors.toList());
        for (Book book : booksAvailable) {
            System.out.println(book);
        }
    }

    public void initMenu() {
        Menu menu = new Menu();
        menu.setTitle("Biblioteca App");
        menu.addItem(new MenuItem("List Books", this, "listBooks"));
        menu.execute();
    }

    public void checkOutBook(String name) {
        books.stream().filter(book -> book.name.equals(name)).peek(book -> book.setCheckedOut(true)).collect(Collectors.toList());
    }
}
