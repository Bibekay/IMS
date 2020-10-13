package com.example.meropasal.models;

public class Product {
    String _id;
    public String category;
    private String product_name;
    private String product_image;
    private String description;
    private String price;
    private boolean expandable;

    public Product(String product_name, String description, String price, String product_image, boolean expandable) {
        this.product_name = product_name;

        this.description = description;
        this.price = price;
        this.product_image = product_image;
        this.expandable = expandable;
    }

    public Product(String product_name, String description, String price, String product_image) {
        this.product_name = product_name;
        this.description = description;
        this.price = price;
        this.product_image = product_image;
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

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
