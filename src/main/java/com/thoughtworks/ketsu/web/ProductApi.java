package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.product.Product;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("products")
public class ProductApi {


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postProduct(@Context Routes routes){
        Product product = new Product("1");
        return Response.created(routes.productUri(product)).build();
    }
}
