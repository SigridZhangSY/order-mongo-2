package com.thoughtworks.ketsu.infrastructure.repository;

import com.mongodb.*;
import com.thoughtworks.ketsu.domain.product.Product;
import com.thoughtworks.ketsu.domain.product.ProductRepository;
import org.bson.types.ObjectId;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ProductRepositoryImpl implements ProductRepository {
//    @Inject
//    Datastore datastore;

    @Inject
    Jongo jongo;

//    @Inject
//    DB db;

    @Override
    public Optional<Product> createProduct(Map<String, Object> info) {

        //use jongo to save and find
        MongoCollection collection = jongo.getCollection("products");
        info.put("_id", new ObjectId());
        WriteResult result = collection.insert(info);
        Product fetch = collection.findOne((ObjectId)info.get("_id")).as(Product.class);
        return Optional.ofNullable(fetch);

        //test to get id after insert
//        Product product = new Product(info.get("name").toString(),
//                                    info.get("description").toString(),
//                                    Double.valueOf(info.get("price").toString()));
//        ObjectId id = (ObjectId)collection.insert(new BasicDBObject(info)).getUpsertedId();
//        WriteResult result = collection.save(product);
//        result = collection.insert(info);


//        BasicDBObject document = new BasicDBObject(info);
//        WriteResult result = collection.insert(info);
//        System.out.println("id ********** " + document.get("_id"));

        //use morphia to save and find
//        Product product = new Product(info.get("name").toString(),
//                                    info.get("description").toString(),
//                                    Double.valueOf(info.get("price").toString()));
//        Key<Product> productKey = datastore.save(product);
//        Product fetch = datastore.get(Product.class, productKey.getId());
//        return Optional.ofNullable(fetch);
    }

    @Override
    public List<Product> listProducts() {
        MongoCollection collection = jongo.getCollection("products");
        MongoCursor<Product> cursor = collection.find().as(Product.class);
        List<Product> productList = new ArrayList<>();
        for(Product product: cursor)
            productList.add(product);
        return productList;
    }
}
