package com.thoughtworks.ketsu.domain.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.jongo.marshall.jackson.oid.MongoId;

import java.util.List;

public class Order {

    @MongoId
    private ObjectId id;
    @JsonProperty("user_id")
    private String userId;
    private String name;
    private String address;
    private String phone;
    @JsonProperty("total_price")
    private double totalPrice;
    @JsonProperty("order_items")
    private List<OrderItem> items;

    public ObjectId getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }


}
