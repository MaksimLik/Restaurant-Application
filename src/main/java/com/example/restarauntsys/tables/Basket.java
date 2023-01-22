package com.example.restarauntsys.tables;

public class Basket {
    private int id_order;
    private String date_of_order;
    private String name;
    private String name_food;
    private String order_status;

    public Basket(int id_order, String name, String name_food, String order_status, String date_of_order) {
        this.id_order = id_order;
        this.name = name;
        this.name_food = name_food;
        this.order_status = order_status;
        this.date_of_order = date_of_order;
    }

    public int getId_order() {
        return id_order;
    }

    public void setId_order(int id_order) {
        this.id_order = id_order;
    }

    public String getDate_of_order() {
        return date_of_order;
    }

    public void setDate_of_order(String date_of_order) {
        this.date_of_order = date_of_order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName_food() {
        return name_food;
    }

    public void setName_food(String name_food) {
        this.name_food = name_food;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }
}
