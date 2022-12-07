package com.example.restarauntsys.tables;

public class Menu {
    private int id;
    private String name;
    private double kcal;
    private double price;

    public Menu(int id, String name, double kcal, double price) {
        this.id = id;
        this.name = name;
        this.kcal = kcal;
        this.price = price;
    }
    public Menu(String name, double kcal, double price) {
        this.name = name;
        this.kcal = kcal;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getKcal() {
        return kcal;
    }

    public double getPrice() {
        return price;
    }
}
