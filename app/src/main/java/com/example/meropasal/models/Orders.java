package com.example.meropasal.models;

public class Orders {
    String _id;
    public Products product;
    public Users user;
    String order_status;
    private boolean expandable;

    public Orders(String _id, Products product, Users user, String order_status) {
        this._id = _id;
        this.product = product;
        this.user = user;
        this.order_status = order_status;
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

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }
}
