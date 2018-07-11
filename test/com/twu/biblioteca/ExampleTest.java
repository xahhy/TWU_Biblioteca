package com.twu.biblioteca;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

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

}
