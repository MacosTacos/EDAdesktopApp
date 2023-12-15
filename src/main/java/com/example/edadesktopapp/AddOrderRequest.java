package com.example.edadesktopapp;

public class AddOrderRequest {
    private String foodIds;
    private String foodQantities;

    public AddOrderRequest(String foodIds, String foodQantities) {
        this.foodIds = foodIds;
        this.foodQantities = foodQantities;
    }

    public String getFoodIds() {
        return foodIds;
    }

    public void setFoodIds(String foodIds) {
        this.foodIds = foodIds;
    }

    public String getFoodQantities() {
        return foodQantities;
    }

    public void setFoodQantities(String foodQantities) {
        this.foodQantities = foodQantities;
    }

}
