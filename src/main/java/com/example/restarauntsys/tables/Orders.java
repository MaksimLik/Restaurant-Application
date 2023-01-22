package com.example.restarauntsys.tables;

public class Orders {
    private int id;
    private String date;
    private String order_status;
    private int cust_id;
    private String name_food;
    private String name;

    public Orders(int id, String date, String order_status, int cust_id, String name_food, String name) {
        this.id = id;
        this.date = date;
        this.order_status = order_status;
        this.cust_id = cust_id;
        this.name_food = name_food;
        this.name = name;
    }

    public Orders(String date, String order_status, int cust_id, String name_food, String name) {
        this.date = date;
        this.order_status = order_status;
        this.cust_id = cust_id;
        this.name_food = name_food;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public int getCust_id() {
        return cust_id;
    }

    public void setCust_id(int cust_id) {
        this.cust_id = cust_id;
    }

    public String getName_food() {
        return name_food;
    }

    public void setName_food(String name_food) {
        this.name_food = name_food;
    }
}
