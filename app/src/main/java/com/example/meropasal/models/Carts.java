package com.example.meropasal.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Carts {

    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("user")
    @Expose
    private Users user;

    @SerializedName("product")
    @Expose
    private Product product;

    @SerializedName("order_status")
    @Expose
    private String order_status;

    private boolean expandable;

    public Carts(String id, Users user, Product product, String order_status) {
        this.id = id;
        this.user = user;
        this.product = product;
        this.order_status = order_status;
    }
    private Integer v;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public boolean isExpandable() {
        return expandable;
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }
}
