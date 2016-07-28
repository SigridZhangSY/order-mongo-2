package com.thoughtworks.ketsu.infrastructure.repository;

import com.thoughtworks.ketsu.domain.product.Product;
import com.thoughtworks.ketsu.domain.product.ProductRepository;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;

import javax.inject.Inject;
import java.util.Map;
import java.util.Optional;

public class ProductRepositoryImpl implements ProductRepository {
    @Inject
    Datastore datastore;

    @Override
    public Optional<Product> createProduct(Map<String, Object> info) {
        Product product = new Product(info.get("name").toString(),
                                    info.get("description").toString(),
                                    Double.valueOf(info.get("price").toString()));
        Key<Product> productKey = datastore.save(product);
        Product fetch = datastore.get(Product.class, productKey.getId());
        return Optional.ofNullable(fetch);
    }
}
