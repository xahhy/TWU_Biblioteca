package com.twu.biblioteca;

public class User {
    private final String name;
    private final Integer age;
    private final String eMail;
    private final String phoneNumber;

    User(String name, Integer age, String eMail, String phoneNumber) {
        this.name = name;
        this.age = age;
        this.eMail = eMail;
        this.phoneNumber = phoneNumber;
    }

    void showDetail() {
        System.out.println("User Name:" + this.name);
        System.out.println("User Age:" + this.age);
        System.out.println("User E-Mail:" + this.eMail);
        System.out.println("User Phone Number:" + this.phoneNumber);
    }
}
