package com.thoughtworks.ketsu.infrastructure.repository;

import com.google.inject.Injector;
import com.mongodb.WriteResult;
import com.thoughtworks.ketsu.domain.order.Order;
import com.thoughtworks.ketsu.domain.product.Product;
import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.domain.user.UserRepository;
import org.bson.types.ObjectId;
import org.jongo.FindOne;
import org.jongo.Jongo;
import org.jongo.MongoCollection;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import java.util.Map;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

    @Inject
    Jongo jongo;

    @Inject
    Injector injector;

    @Override
    public Optional<User> createUser(Map<String, Object> info) {

        MongoCollection collection = jongo.getCollection("users");
        info.put("_id", new ObjectId());

        WriteResult result = collection.save(info);

        FindOne res = collection.findOne((ObjectId) result.getUpsertedId());

//        User newUser = new User();
        User user = res.as(User.class);
        //********* inject object into guice *******
        injector.injectMembers(user);

//        user.testInject();
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findUserById(String id) {
        MongoCollection collection = jongo.getCollection("users");

        ObjectId objectId;
        try {
            objectId = new ObjectId(id);
        }catch (Exception e){
            throw new NotFoundException("can not find user by id");
        }

        User user = collection.findOne(objectId).as(User.class);
        if(user != null)
         injector.injectMembers(user);

        return Optional.ofNullable(user);
    }
}
