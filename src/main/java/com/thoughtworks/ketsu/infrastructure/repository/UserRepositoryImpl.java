package com.thoughtworks.ketsu.infrastructure.repository;

import com.mongodb.WriteResult;
import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.domain.user.UserRepository;
import org.bson.types.ObjectId;
import org.jongo.Jongo;
import org.jongo.MongoCollection;

import javax.inject.Inject;
import java.util.Map;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

    @Inject
    Jongo jongo;

    @Override
    public Optional<User> createUser(Map<String, Object> info) {

        MongoCollection collection = jongo.getCollection("users");
        info.put("_id", new ObjectId());
        WriteResult result = collection.insert(info);
        User user = collection.findOne((ObjectId) info.get("_id")).as(User.class);
        return Optional.ofNullable(user);
    }
}
