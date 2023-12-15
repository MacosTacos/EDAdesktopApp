package com.example.edadesktopapp;

public class GetCategoriesResponse {
    private int id;
    private String category;
    private String categoryName;

    public GetCategoriesResponse(int id, String category, String categoryName) {
        this.id = id;
        this.category = category;
        this.categoryName = categoryName;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getCategoryName() {
        return categoryName;
    }

    @Override
    public String toString() {
        return categoryName;
    }
}
