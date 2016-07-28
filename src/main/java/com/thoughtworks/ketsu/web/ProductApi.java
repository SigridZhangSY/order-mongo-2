package com.thoughtworks.ketsu.web;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("products")
public class ProductApi {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postProdcut(){
        return Response.status(201).build();
    }
}
