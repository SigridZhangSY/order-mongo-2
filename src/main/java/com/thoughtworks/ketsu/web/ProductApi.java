package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.product.Product;
import com.thoughtworks.ketsu.domain.product.ProductRepository;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

@Path("products")
public class ProductApi {


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postProduct(Map<String, Object> info,
                                @Context Routes routes,
                                @Context ProductRepository productRepository){
        Product product = productRepository.createProduct(info).get();
        return Response.created(routes.productUri(product)).build();
    }
}