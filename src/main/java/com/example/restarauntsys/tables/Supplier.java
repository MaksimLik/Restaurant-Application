package com.example.restarauntsys.tables;

public class Supplier {
    private int ID_supplier;
    private int ID_delivery;
    private String nick_name;

    public Supplier(int ID_supplier, int ID_delivery, String nick_name) {
        this.ID_supplier = ID_supplier;
        this.ID_delivery = ID_delivery;
        this.nick_name = nick_name;
    }

    public int getID_supplier() {
        return ID_supplier;
    }

    public void setID_supplier(int ID_supplier) {
        this.ID_supplier = ID_supplier;
    }

    public int getID_delivery() {
        return ID_delivery;
    }

    public void setID_delivery(int ID_delivery) {
        this.ID_delivery = ID_delivery;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }
}
