package com.example.restarauntsys.tables;

import com.example.restarauntsys.mysql.DB_Handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Customers extends DB_Handler {
    private String nick_name;

    protected int currentUserId;
    public Customers(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public int getID()  {
        ResultSet rs = null;
        Statement stmt = null;
        String selectQuery = "SELECT max(id_user) FROM users";

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


        System.out.println(currentUserId + "proba");
        return currentUserId;
    }
}
