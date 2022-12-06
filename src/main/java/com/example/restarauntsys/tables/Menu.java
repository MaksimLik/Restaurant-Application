package com.example.restarauntsys.tables;

public class Menu {
    private int id;
    private String name;
    private float kcal;
    private float price;

    public Menu(int id, String name, float kcal, float price) {
        this.id = id;
        this.name = name;
        this.kcal = kcal;
        this.price = price;
    }
    public Menu(String name, float kcal, float price) {
        this.name = name;
        this.kcal = kcal;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Float getKcal() {
        return kcal;
    }

    public Float getPrice() {
        return price;
    }
}
