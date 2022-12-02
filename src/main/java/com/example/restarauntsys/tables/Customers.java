package com.example.restarauntsys.tables;

public class Customers extends User {
    public Customers(String nick_name) {
        super(nick_name);
    }

    public Customers(String name, String surname, String nick_name, String password) {
        super(name, surname, nick_name, password);
    }

    public Customers() {
    }
}
