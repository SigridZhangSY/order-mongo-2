package com.thoughtworks.ketsu.support;

import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestHelper {

    public static Map<String, Object> productMap(String name){
        return new HashMap<String, Object>(){{
            put("name", name);
            put("description", "red");
            put("price", 1.2);
        }};
    }

    public static Map<String, Object> userMap(String name){
        return new HashMap<String, Object>(){{
            put("name", name);
        }};
    }

    public static Map<String, Object> orderMap( String productId){
        return new HashMap<String, Object>(){{
            put("name", "kayla");
            put("address", "beijing");
            put("phone", "12300000000");
            List<Map<String, Object>> items = new ArrayList<>();
            items.add(new HashMap<String, Object>(){{
                put("product_id", productId);
                put("quantity", 2);
            }});
            put("order_items", items);
        }};
    }

}
