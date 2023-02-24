package com.example.restarauntsys.tables;

public class Employees extends User{
    private String role;
    private String nick_name;

    public Employees(String nick_name, String role) {
        this.nick_name = nick_name;
        this.role = role;
    }

    public Employees(String name, String surname, String password) {
        super(name, surname, password);
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public Employees() {
    }
}
