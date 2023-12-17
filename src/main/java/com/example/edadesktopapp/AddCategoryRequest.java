package com.example.edadesktopapp;

public class AddCategoryRequest {
    private String category;
    private String categoryName;

    public AddCategoryRequest(String category, String categoryName) {
        this.category = category;
        this.categoryName = categoryName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
