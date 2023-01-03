package com.example.restarauntsys.tables;

import com.example.restarauntsys.mysql.Constants;
import com.example.restarauntsys.mysql.DB_Handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Address extends DB_Handler {
    public static int address_id_max;
    public String street;
    public String room_number;
    public String postal_index;

    public Address(String street, String room_number, String postal_index) {
        this.street = street;
        this.room_number = room_number;
        this.postal_index = postal_index;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getRoom_number() {
        return room_number;
    }

    public void setRoom_number(String room_number) {
        this.room_number = room_number;
    }

    public String getPostal_index() {
        return postal_index;
    }

    public void setPostal_index(String postal_index) {
        this.postal_index = postal_index;
    }

    public int getIDadr() {
        ResultSet rs = null;
        Statement stmt = null;
        String selectQuery = "select max(ID_address) from address;";

        try {
            stmt = getDbConnection().createStatement();
            rs = stmt.executeQuery(selectQuery);
            rs.next();

            address_id_max = Integer.parseInt(rs.getString(1));

            rs.close();
            stmt.close();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return address_id_max;

    }
}

