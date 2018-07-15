package com.twu.biblioteca;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BibliotecaApp {

    ArrayList<Book> books = new ArrayList<Book>();
    ArrayList<Movie> movies = new ArrayList<Movie>();
    Menu menu = new Menu();
    private ArrayList<User> users = new ArrayList<User>();
    private Optional<User> loggedInUser;

    public static void main(String[] args) {
        BibliotecaApp app = new BibliotecaApp();
        app.initBooks();
        app.initMovies();
        app.initUsers();
        app.initMenu();
    }

    private void initMovies() {
        this.addMovie("哈利·波特与魔法石", "克里斯·哥伦布", "2001", 7.5f);
        this.addMovie("Zootopia", "Rich Moore", "2016", 8.0f);
    }

    private void initUsers() {
        this.addUser("Tom", 18, "tom@gmail.com", "110", "111-1111", "password");
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
        menu.addItem(new MenuItem("Login", this, "loginPromote"));
        menu.execute();
    }

    private void loggedInMenue() {
        menu = new Menu();
        menu.addItem(new MenuItem("List Books", this, "listBooks"));
        menu.addItem(new MenuItem("Check Out Book With Name", this, "checkOutBookPromote"));
        menu.addItem(new MenuItem("Return Book With Name", this, "returnBookPromote"));
        menu.addItem(new MenuItem("List Movies", this, "listMovies"));
        menu.addItem(new MenuItem("Check Out Movie With Name", this, "checkOutMoviePromote"));
        menu.addItem(new MenuItem("Return Movie With Name", this, "returnMoviePromote"));
        menu.addItem(new MenuItem("Show User Detail", this, "showUserDetailPromote"));
        menu.execute();
    }

    public void checkOutBookPromote() {
        try {
            String input = menu.in.readLine();
            checkOutBook(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void returnBookPromote() {
        try {
            String input = menu.in.readLine();
            returnBook(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checkOutMoviePromote() {
        try {
            String input = menu.in.readLine();
            checkOutMovie(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void returnMoviePromote() {
        try {
            String input = menu.in.readLine();
            returnMovie(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checkOutBook(String name) {
        Optional<Book> first = books.stream()
                .filter(book -> book.name.equals(name))
                .peek(book -> {
                    book.setCheckedOut(true);
                    loggedInUser.ifPresent(user -> book.owners.add(user));
                })
                .findFirst();
        if (first.isPresent()) {
            System.out.println("Thank you! Enjoy the book");
        } else {
            System.out.println("That book is not available");
        }
    }

    public void returnBook(String name) {
        Optional<Book> first = books.stream().filter(book -> book.name.equals(name))
                .peek(book -> {
                    book.owners.remove(loggedInUser.get());
                    book.setCheckedOut(false);
                }).findFirst();
        if (first.isPresent()) {
            System.out.println("Thank you for returning the book");
        } else {
            System.out.println("That is not a valid book to return");
        }
    }

    public void returnMovie(String name) {
        Optional<Movie> first = movies.stream().filter(movie -> movie.name.equals(name)).peek(movie -> movie.setCheckedOut(false)).findFirst();
        if (first.isPresent()) {
            System.out.println("Thank you for returning the movie");
        } else {
            System.out.println("That is not a valid movie to return");
        }
    }

    public void addMovie(String name, String director, String year, Float rating) {
        movies.add(new Movie(name, director, year, rating));
    }

    public void listMovies() {
        System.out.println("Movie Name\tAuthor\tPublished Year\tDirector\tRating");
        List<Movie> moviesAvailable = movies.stream().filter(movie -> !movie.checkedOut).collect(Collectors.toList());
        for (Movie movie : moviesAvailable) {
            System.out.println(movie);
        }
    }

    public void checkOutMovie(String name) {
        Optional<Movie> first = movies.stream()
                .filter(movie -> movie.name.equals(name))
                .peek(movie -> {
                    movie.setCheckedOut(true);
                    loggedInUser.ifPresent(user -> movie.owners.add(user));
                })
                .findFirst();
        if (first.isPresent()) {
            System.out.println("Thank you! Enjoy the movie");
        } else {
            System.out.println("That movie is not available");
        }
    }

    public void addUser(String name, Integer age, String eMail, String phoneNumber, String libraryNumber, String password) {
        users.add(new User(name, age, eMail, phoneNumber, libraryNumber, password));
    }

    public boolean login(String libraryNumber, String password) {
        this.loggedInUser = users.stream()
                .filter(user -> user.checkPassword(libraryNumber, password))
                .findFirst();
        return this.loggedInUser.isPresent();
    }

    public User getLoginUser() {
        return this.loggedInUser.orElse(null);
    }

    public void showUserDetailPromote() {
        if (this.loggedInUser.isPresent()) {
            this.loggedInUser.get().showDetail();
        } else {
            System.out.println("Please Login.");
        }
    }

    public void loginPromote() {
        try {
            System.out.print("Library Number: ");
            String libraryNumber = menu.in.readLine();
            System.out.print("Password: ");
            String password = menu.in.readLine();
            boolean isLoggedIn = login(libraryNumber, password);
            if (isLoggedIn) {
                loggedInMenue();
            } else {
                System.out.println("Wrong Library Number Or Password!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
