package com.example.restarauntsys.tables;

public class Employees extends User {
    private String role;

    public Employees(String nick_name, String role) {
        super(nick_name);
        this.role = role;
    }

    public Employees(String role) {
        this.role = role;
    }

    public Employees() {
    }
}
