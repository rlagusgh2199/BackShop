package com.backshop.productDB;

public class CategoryCountDTO {
    private String category;
    private int count;

    public CategoryCountDTO(String category, int count) {
        this.category = category;
        this.count = count;
    }

    public String getCategory() {
        return category;
    }

    public int getCount() {
        return count;
    }
}
