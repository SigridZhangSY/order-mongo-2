package com.thoughtworks.ketsu.domain.product;

import org.bson.types.ObjectId;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductRepository {
    Optional<Product> createProduct(Map<String, Object> info);
    List<Product> listProducts();
    Optional<Product> findById(String id);
}
