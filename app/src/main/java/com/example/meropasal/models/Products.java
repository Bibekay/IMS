package com.example.meropasal.models;

public class Products {
    public Categories category;
    private String product_name;
    private String product_image;
    private String description;
    private String price;
    private boolean expandable;

    public Products(String product_name, String product_image, String description, String price, boolean expandable) {
        this.product_name = product_name;
        this.product_image = product_image;
        this.description = description;
        this.price = price;
        this.expandable = expandable;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isExpandable() {
        return expandable;
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }


    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }
}
