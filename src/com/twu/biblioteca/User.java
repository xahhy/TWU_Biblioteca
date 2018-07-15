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

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(this.getClass() != obj.getClass()) return false;
        User that = (User)obj;
        if (!that.name.equals(this.name) ||
                !that.age.equals(this.age) ||
                !that.eMail.equals(this.eMail) ||
                !that.phoneNumber.equals(this.phoneNumber)) {
            return false;
        }
        return true;
    }
}
