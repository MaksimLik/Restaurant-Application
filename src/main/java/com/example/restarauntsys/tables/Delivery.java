package com.example.restarauntsys.tables;

public class Delivery {
    private int id;
    private String date;
    private String invoice;
    private int id_order;
    private String nick_name;


    public Delivery(int id, String date, String invoice, int id_order, String nick_name) {
        this.id = id;
        this.date = date;
        this.invoice = invoice;
        this.id_order = id_order;
        this.nick_name = nick_name;
    }

    public Delivery(String date, String invoice, int id_order, String nick_name) {
        this.date = date;
        this.invoice = invoice;
        this.id_order = id_order;
        this.nick_name = nick_name;
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

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public int getId_order() {
        return id_order;
    }

    public void setId_order(int id_order) {
        this.id_order = id_order;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }
}
