package com.example.restarauntsys.tables;

import com.example.restarauntsys.mysql.Constants;
import com.example.restarauntsys.mysql.DB_Handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User extends DB_Handler {
    private String name;
    private String surname;
    private String nick_name;
    private String password;
    public int currentUserId;

    public User(String name, String surname, String nick_name, String password) {
        this.name = name;
        this.surname = surname;
        this.nick_name = nick_name;
        this.password = password;
    }

    public User(String nick_name) {
        this.nick_name = nick_name;
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
    public int getID()  {
        ResultSet rs = null;
        Statement stmt = null;
        String selectQuery = "SELECT max(" + Constants.USER_ID + ") from " + Constants.USER_TABLE;

        try {
            stmt = getDbConnection().createStatement();
            rs = stmt.executeQuery(selectQuery);
            rs.next();

            currentUserId = Integer.parseInt(rs.getString(1));

            rs.close();
            stmt.close();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return currentUserId;
    }
}
