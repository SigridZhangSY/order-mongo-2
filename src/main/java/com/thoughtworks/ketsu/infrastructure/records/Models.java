package com.thoughtworks.ketsu.infrastructure.records;

import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.thoughtworks.ketsu.domain.product.ProductRepository;
import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.domain.user.UserRepository;
import com.thoughtworks.ketsu.infrastructure.ParameterCheck;
import com.thoughtworks.ketsu.infrastructure.repository.ProductRepositoryImpl;
import com.thoughtworks.ketsu.infrastructure.repository.UserRepositoryImpl;
import org.jongo.Jongo;

import javax.inject.Inject;

public class Models extends AbstractModule {

    @Override
    protected void configure() {




        String dbname = System.getenv().getOrDefault("MONGODB_DATABASE", "mongodb_store");
        String host = System.getenv().getOrDefault("MONGODB_HOST", "localhost");
        String username = System.getenv().getOrDefault("MONGODB_USER", "admin");
        String password = System.getenv().getOrDefault("MONGODB_PASS", "mypass");
        String connectURL = String.format(
                "mongodb://%s:%s@%s/%s",
                username,
                password,
                host,
                dbname
        );


        MongoClient mongoClient = new MongoClient( new MongoClientURI(connectURL));


        DB db = mongoClient.getDB("mongodb_store");
//        bind(DB.class).toInstance(db);

        //morphia
//        final Morphia morphia = new Morphia();
//         tell Morphia where to find your classes
//         can be called multiple times with different packages or classes
//        morphia.mapPackage("com.thoughtworks.ketsu.domain");
        // create the Datastore connecting to the default port on the local host
//        final Datastore datastore = morphia.createDatastore(mongoClient, "mongodb_store");
//        datastore.ensureIndexes();
//        bind(Datastore.class).toInstance(datastore);

        //jongo
        Jongo jongo = new Jongo(db);
        bind(Jongo.class).toInstance(jongo);

        ParameterCheck parameterCheck = new ParameterCheck();
        bind(ParameterCheck.class).toInstance(parameterCheck);

        bind(ProductRepository.class).to(ProductRepositoryImpl.class);
        bind(UserRepository.class).to(UserRepositoryImpl.class);


    }

}
