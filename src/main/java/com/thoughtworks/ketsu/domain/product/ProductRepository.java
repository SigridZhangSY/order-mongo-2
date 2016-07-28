package com.thoughtworks.ketsu.domain.product;

import org.bson.types.ObjectId;

import java.util.Map;
import java.util.Optional;

public interface ProductRepository {
    Optional<Product> createProduct(Map<String, Object> info);
}
