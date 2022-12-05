package com.example.restarauntsys.tables;

public class Menu {
    private String name;
    private float kcal;
    private float price;

    public Menu(String name, float kcal, float price) {
        this.name = name;
        this.kcal = kcal;
        this.price = price;
    }

    public Menu() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getKcal() {
        return kcal;
    }

    public void setKcal(float kcal) {
        this.kcal = kcal;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
