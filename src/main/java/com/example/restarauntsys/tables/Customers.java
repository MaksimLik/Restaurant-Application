package com.example.restarauntsys.tables;

public class Customers extends User {
    private String nick_name;

    public Customers(String nick_name) {
        this.nick_name = nick_name;
    }
    public Customers() {
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }
}
