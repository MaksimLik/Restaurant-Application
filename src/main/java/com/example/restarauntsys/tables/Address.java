package com.example.restarauntsys.tables;

public class Address {
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
}

