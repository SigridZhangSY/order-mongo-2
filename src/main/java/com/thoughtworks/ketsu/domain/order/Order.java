package com.thoughtworks.ketsu.domain.order;

import org.bson.types.ObjectId;

import java.util.List;

public class Order {

    private ObjectId id;
    private String userId;
    private String name;
    private String address;
    private String phone;
    private double totalPrice;
    private List<OrderItem> items;

    public ObjectId getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }


}
