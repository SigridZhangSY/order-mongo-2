package com.thoughtworks.ketsu.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.inject.Injector;
import com.mongodb.WriteResult;
import com.thoughtworks.ketsu.domain.order.Order;
import com.thoughtworks.ketsu.domain.product.ProductRepository;
import com.thoughtworks.ketsu.infrastructure.ParameterCheck;
import com.thoughtworks.ketsu.infrastructure.records.Record;
import com.thoughtworks.ketsu.web.jersey.Routes;
import org.bson.types.ObjectId;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.jongo.marshall.jackson.oid.MongoId;
import org.jongo.marshall.jackson.oid.MongoObjectId;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class User implements Record{


    @Inject
    @JsonIgnore
    Injector injector;

    @Inject
//    @JsonIgnore
    ProductRepository productRepository;



    @JsonProperty("name")
    private String name;

    @MongoId
    private ObjectId _id;
//
//
    @Inject
    @JsonIgnore
    Jongo jongo;

//    public Optional<Order> createOrder(Map<String, Object> info){
//
//        MongoCollection collection = jongo.getCollection("orders");
//
//        info.put("user_id", id.toString());
//
//        List<Map<String, Object>> items = (List<Map<String, Object>>) info.get("order_items");
//        double total_price = 0;
//        for (Map<String, Object> item : items){
//            double price = productRepository.findById(item.get("product_id").toString()).get().getPrice();
//            item.put("amount", price * Integer.valueOf(item.get("quantity").toString()));
//            total_price += price;
//        }
//
//        info.put("total_price", total_price);
//        ObjectId orderId = new ObjectId();
//        info.put("_id", orderId);
//
//        WriteResult result = collection.insert(info);
//        Order order = collection.findOne(orderId).as(Order.class);
//        if(order != null)
//            injector.injectMembers(order);
//        return Optional.ofNullable(order);
//    }


    public ObjectId getId() {
        return _id;
    }

    public String getName() {
        return name;
    }




    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return toJson(routes);
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return new HashMap<String, Object>(){{
            put("id", _id.toString());
            put("uri", routes.userUri(User.this));
            put("name", name);
        }};
    }
}
