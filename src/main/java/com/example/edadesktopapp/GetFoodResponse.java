package com.example.edadesktopapp;

public class GetFoodResponse {
    private int id;
    private int quantity;
    private double price;
    private String name;

    public GetFoodResponse(int id, int quantity, double price, String name) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name + "\n" + quantity + " шт. " +
                price + " руб.";
    }
}
