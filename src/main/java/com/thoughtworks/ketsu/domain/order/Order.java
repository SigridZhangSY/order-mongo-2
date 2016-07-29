package com.thoughtworks.ketsu.domain.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.thoughtworks.ketsu.domain.product.Product;
import com.thoughtworks.ketsu.infrastructure.records.Record;
import com.thoughtworks.ketsu.web.jersey.Routes;
import org.bson.types.ObjectId;
import org.jongo.Jongo;
import org.jongo.marshall.jackson.oid.MongoId;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order implements Record{

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

    @JsonIgnore
    @Inject
    Jongo jongo;

    public ObjectId getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return new HashMap<String, Object>(){{
            put("uri", routes.orderUri(Order.this));
            put("name", name);
            put("address", address);
            put("phone", phone);
            put("total_price", totalPrice);
            put("create_at", id.getDate());
            List<Map<String, Object>> orderItems = new ArrayList<Map<String, Object>>();
            for(int i = 0; i < items.size(); i++){
                Map<String, Object> map = new HashMap<>();
                map.put("product_id", String.valueOf(items.get(i).getProductId()));
                map.put("uri", routes.productUri(
                        jongo.getCollection("products").findOne(new ObjectId(String.valueOf(items.get(i).getProductId()))).as(Product.class)
                ));
                map.put("quantity", items.get(i).getQuantity());
                map.put("amount", items.get(i).getAmount());
                orderItems.add(map);
            }
            put("order_items", orderItems);
        }};
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return new HashMap<String, Object>() {{
            put("uri", routes.orderUri(Order.this));
            put("name", name);
            put("address", address);
            put("phone", phone);
            put("total_price", totalPrice);
            put("create_at", id.getDate());
        }};
    }

}
