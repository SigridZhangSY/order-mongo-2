package com.thoughtworks.ketsu.infrastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParameterCheck {
    public List<String> emptyCheck(List<String> fields, Map<String, Object> info){
        List<String> emptyfields = new ArrayList<>();
        for(String field : fields){
            if(info.getOrDefault(field, "").toString().isEmpty())
                emptyfields.add(field);
        }
        return emptyfields;
    }
}
