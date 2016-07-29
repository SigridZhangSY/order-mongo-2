package com.thoughtworks.ketsu.infrastructure.repository;

import com.mongodb.WriteResult;
import com.sun.org.apache.xpath.internal.operations.Or;
import com.thoughtworks.ketsu.domain.order.Order;
import com.thoughtworks.ketsu.domain.product.Product;
import org.bson.types.ObjectId;
import org.jongo.Jongo;

import javax.inject.Inject;
import java.util.Map;
import java.util.Optional;

public class UserRepository {

    @Inject
    Jongo jongo;
    public Optional<Order> createProduct(Map<String, Object> info){
        info.put("_id", new ObjectId());
        WriteResult result = jongo.getCollection("orders").insert(info);
        Order order = jongo.getCollection("orders").findOne((ObjectId) info.get("_id")).as(Order.class);
        return Optional.ofNullable(order);
    }

}
