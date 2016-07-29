package com.thoughtworks.ketsu.web;


import com.thoughtworks.ketsu.domain.order.Order;
import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.infrastructure.ParameterCheck;
import com.thoughtworks.ketsu.web.exception.InvalidParameterException;
import com.thoughtworks.ketsu.web.jersey.Routes;
import org.bson.types.ObjectId;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserApi {
    private User user;



    public UserApi(User user) {
        this.user = user;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User findUser(){
        return user;
    }

    @POST
    @Path("orders")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createOrder(Map<String, Object> info,
                                @Context Routes routes,
                                @Context ParameterCheck parameterCheck){
        List<String> fields = new ArrayList<>();
        fields.add("name");
        fields.add("address");
        fields.add("phone");
        fields.add("order_items");
        List<String> empty = parameterCheck.emptyCheck(fields, info);
        if(empty.size() > 0)
            throw new InvalidParameterException(empty);
        List<Map<String, Object>> items = (List)info.get("order_items");
        for(Map<String, Object> item : items){
            fields.clear();
            empty.clear();
            fields.add("product_id");
            fields.add("quantity");
            empty = parameterCheck.emptyCheck(fields, item);
            if(empty.size() > 0)
                throw new InvalidParameterException(empty);
        }
        Order order = user.createOrder(info).get();
        System.out.println(order);
        System.out.println(routes);
        return Response.created(routes.orderUri(order)).build();
    }

    @GET
    @Path("orders")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Order> listOrders(){
        return user.listOrders();
    }

    @GET
    @Path("orders/{orderId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String findOrder(){
        return "Ok";
    }
}
