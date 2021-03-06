package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BibliotecaApp {

    ArrayList<Book> books = new ArrayList<Book>();
    Menu menu = new Menu();

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
        menu.setTitle("Biblioteca App");
        menu.addItem(new MenuItem("List Books", this, "listBooks"));
        menu.addItem(new MenuItem("Check Out Book With Name", this, "checkOutBookPromote"));
        menu.addItem(new MenuItem("Return Book With Name", this, "returnBookPromote"));
        menu.execute();
    }

    public void checkOutBookPromote(){
        try {
            String input = menu.in.readLine();
            checkOutBook(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void returnBookPromote(){
        try {
            String input = menu.in.readLine();
            returnBook(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checkOutBook(String name) {
        Optional<Book> first = books.stream().filter(book -> book.name.equals(name)).peek(book -> book.setCheckedOut(true)).findFirst();
        if (first.isPresent()) {
            System.out.println("Thank you! Enjoy the book");
        }else {
            System.out.println("That book is not available");
        }
    }

    public void returnBook(String name) {
        Optional<Book> first = books.stream().filter(book -> book.name.equals(name)).peek(book -> book.setCheckedOut(false)).findFirst();
        if (first.isPresent()) {
            System.out.println("Thank you for returning the book");
        }else {
            System.out.println("That is not a valid book to return");
        }
    }
}
