package com.twu.biblioteca;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ExampleTest {

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
    public void testListBooks() {
        app.initBooks();
        app.listBooks();
        assertEquals("Book Name\tAuthor\tPublished Year\r\n" +
                        "CleanCode\tRobert C. Martin\t2012\r\n" +
                        "DevOps Practice\tJoakim Verona\t2016\r\n",
                outContent.toString());
    }

    @Test
    public void testListBooksNotCheckedOut() {
        app.initBooks();
        app.checkOutBook("CleanCode");
        app.listBooks();
        assertTrue(outContent.toString().endsWith("Book Name\tAuthor\tPublished Year\r\n" +
                "DevOps Practice\tJoakim Verona\t2016\r\n"));
    }

    @Test
    public void testCheckOutABook() {
        app.initBooks();
        app.checkOutBook("CleanCode");
        assertTrue(outContent.toString().endsWith("Thank you! Enjoy the book\r\n"));
    }

    @Test
    public void testCheckOutABookWithWrongName() {
        app.initBooks();
        app.checkOutBook("NoCleanCode");
        assertTrue(outContent.toString().endsWith("That book is not available\r\n"));
    }

    @Test
    public void testReturnABookWithName() {
        app.initBooks();
        app.checkOutBook("CleanCode");
        app.returnBook("CleanCode");
        assertTrue(outContent.toString().endsWith("Thank you for returning the book\r\n"));
    }

    @Test
    public void testReturnABookWithWrongName() {
        app.initBooks();
        app.checkOutBook("CleanCode");
        app.returnBook("NoCleanCode");
        assertTrue(outContent.toString().endsWith("That is not a valid book to return\r\n"));
    }
}
