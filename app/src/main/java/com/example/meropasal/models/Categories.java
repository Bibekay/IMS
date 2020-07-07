package com.example.meropasal.models;

public class Categories {
    private  String category_name;
    private String category_image;
    private String _id;

    public Categories(String category_name, String category_image, String _id) {
        this.category_name = category_name;
        this.category_image = category_image;
        this._id = _id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_image() {
        return category_image;
    }

    public void setCategory_image(String category_image) {
        this.category_image = category_image;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
