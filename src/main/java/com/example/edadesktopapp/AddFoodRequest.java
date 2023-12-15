package com.example.edadesktopapp;

public class AddFoodRequest {
    private int quantity;
    private double price;
    private String name;
    private String pictureUrl;
    private int categoryId;

    public AddFoodRequest(String name, int quantity, double price, String pictureUrl, int categoryId) {
        this.quantity = quantity;
        this.price = price;
        this.name = name;
        this.pictureUrl = pictureUrl;
        this.categoryId = categoryId;
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

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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

    public String getPictureUrl() {
        return pictureUrl;
    }

    public int getCategoryId() {
        return categoryId;
    }

}
