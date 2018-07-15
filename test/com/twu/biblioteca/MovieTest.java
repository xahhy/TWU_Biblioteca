package com.twu.biblioteca;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MovieTest {

    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final BibliotecaApp app = new BibliotecaApp();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testWelcomeMessage() {
        BibliotecaApp.welcome();
        assertEquals("Welcome to Biblioteca!\r\n", outContent.toString());
    }

    @Test
    public void testListMovies() {
        initMovies();
        app.listMovies();
        assertEquals("Movie Name\tAuthor\tPublished Year\tDirector\tRating\r\n" +
                        "哈利·波特与魔法石\t克里斯·哥伦布\t2001\t7.5\r\n" +
                        "Zootopia\tRich Moore\t2016\t8.0\r\n",
                outContent.toString());
    }

    private void initMovies() {
        app.addMovie("哈利·波特与魔法石", "克里斯·哥伦布", "2001", 7.5f);
        app.addMovie("Zootopia", "Rich Moore", "2016", 8.0f);
    }

    @Test
    public void testListMoviesNotCheckedOut() {
        initMovies();
        app.checkOutMovie("Zootopia");
        app.listMovies();
        assertTrue(outContent.toString().endsWith("Movie Name\tAuthor\tPublished Year\tDirector\tRating\r\n" +
                "哈利·波特与魔法石\t克里斯·哥伦布\t2001\t7.5\r\n"));
    }

    @Test
    public void testShowUserDetail() {
        User user = new User("Tom", 18, "tom@gmail.com", "110");
        user.showDetail();
        assertEquals("User Name:Tom\r\n" +
                "User Age:18\r\n" +
                "User E-Mail:tom@gmail.com\r\n" +
                "User Phone Number:110\r\n", outContent.toString());
    }

    //
//    @Test
//    public void testCheckOutAMovie() {
//        app.initMovies();
//        app.checkOutMovie("CleanCode");
//        assertTrue(outContent.toString().endsWith("Thank you! Enjoy the book\r\n"));
//    }
//
//    @Test
//    public void testCheckOutAMovieWithWrongName() {
//        app.initMovies();
//        app.checkOutMovie("NoCleanCode");
//        assertTrue(outContent.toString().endsWith("That book is not available\r\n"));
//    }
//
//    @Test
//    public void testReturnAMovieWithName() {
//        app.initMovies();
//        app.checkOutMovie("CleanCode");
//        app.returnMovie("CleanCode");
//        assertTrue(outContent.toString().endsWith("Thank you for returning the book\r\n"));
//    }
//
//    @Test
//    public void testReturnAMovieWithWrongName() {
//        app.initMovies();
//        app.checkOutMovie("CleanCode");
//        app.returnMovie("NoCleanCode");
//        assertTrue(outContent.toString().endsWith("That is not a valid book to return\r\n"));
//    }
}
