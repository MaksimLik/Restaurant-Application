package com.example.restarauntsys.tables;

public class Menu {
    private int id;
    private String name;
    private String description;
    private double kcal;
    private double price;

    public Menu(int id, String name, String description, double kcal, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.kcal = kcal;
        this.price = price;
    }
    public Menu(String name, String description, double kcal, double price) {
        this.name = name;
        this.description = description;
        this.kcal = kcal;
        this.price = price;
    }

    public String getDescription() {
        return description;
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
