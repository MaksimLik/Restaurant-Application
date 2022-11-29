package com.example.restarauntsys.tables;

import com.example.restarauntsys.mysql.DB_Handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Customers extends DB_Handler {
    private String nick_name;

    int currentUserId;
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

  /*  public int getID() {
        ResultSet rs = null;
        Statement stmt = null;
        String selectquery = "SELECT max(id_user) FROM users";
        System.out.println("dsfsdfsdf");

        try {
            stmt = getDbConnection().createStatement();
            rs = stmt.executeQuery(selectquery);
            rs.next();
            int currentUserId;
            currentUserId = Integer.parseInt(rs.getString(1));

            System.out.println(currentUserId);


        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("selectoin error");
        }
        System.out.println(currentUserId);
        return currentUserId;

    } */

}
