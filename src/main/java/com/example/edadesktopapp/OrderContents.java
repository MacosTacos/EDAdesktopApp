package com.example.edadesktopapp;

public class OrderContents {
    private int foodId;
    private String name;
    private String quantity;

    public OrderContents(int foodId, String name, String quantity) {
        this.foodId = foodId;
        this.name = name;
        this.quantity = quantity;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return name + " " + quantity + " шт";
    }
}
