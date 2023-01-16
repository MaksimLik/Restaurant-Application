package com.example.restarauntsys.tables;

public class Orders {
    private int id;
    private String date;
    private String order_status;
    private int cust_id;
    private int food_id;

    public Orders(int id, String date, String order_status, int cust_id, int food_id) {
        this.id = id;
        this.date = date;
        this.order_status = order_status;
        this.cust_id = cust_id;
        this.food_id = food_id;
    }

    public Orders(String date, String order_status, int cust_id, int food_id) {
        this.date = date;
        this.order_status = order_status;
        this.cust_id = cust_id;
        this.food_id = food_id;
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

    public int getFood_id() {
        return food_id;
    }

    public void setFood_id(int food_id) {
        this.food_id = food_id;
    }
}
