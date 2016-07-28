package com.thoughtworks.ketsu.support;

import java.util.HashMap;
import java.util.Map;

public class TestHelper {

    public static Map<String, Object> productMap(String name){
        return new HashMap<String, Object>(){{
            put("name", name);
            put("description", "red");
            put("price", 1.2);
        }};
    }
}
