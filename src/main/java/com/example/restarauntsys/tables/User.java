package com.example.restarauntsys.tables;

import java.sql.Date;

public class User {
    private String name;
    private String surname;
    private String nick_name;
    private String password;

    public User(String name, String surname, String nick_name, String password) {
        this.name = name;
        this.surname = surname;
        this.nick_name = nick_name;
        this.password = password;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
