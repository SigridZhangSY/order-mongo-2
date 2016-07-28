package com.thoughtworks.ketsu.domain.product;

import org.bson.types.ObjectId;

public class Product {

    private String id;
    private String name;
    private String description;
    private double price;

    public Product(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }
}
