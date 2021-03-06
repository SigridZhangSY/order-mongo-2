package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.product.Product;
import com.thoughtworks.ketsu.domain.product.ProductRepository;
import com.thoughtworks.ketsu.infrastructure.ParameterCheck;
import com.thoughtworks.ketsu.web.exception.InvalidParameterException;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Path("products")
public class ProductApi {


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postProduct(Map<String, Object> info,
                                @Context Routes routes,
                                @Context ProductRepository productRepository,
                                @Context ParameterCheck parameterCheck){
        List<String> fields = new ArrayList<>();
        fields.add("name");
        fields.add("description");
        fields.add("price");
        List<String> empty = parameterCheck.emptyCheck(fields, info);
        if(empty.size() > 0)
            throw new InvalidParameterException(empty);
        Product product = productRepository.createProduct(info).get();
        return Response.created(routes.productUri(product)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> listProducts(@Context ProductRepository productRepository){
        return productRepository.listProducts();
    }

    @GET
    @Path("{productId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Product findProductById(@PathParam("productId") String id,
                                   @Context ProductRepository productRepository){
        return productRepository.findById(id).orElseThrow(
                () -> new NotFoundException("can not find product by id."));
    }
}