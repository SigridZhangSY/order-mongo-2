package com.thoughtworks.ketsu.infrastructure.records;

import com.google.inject.AbstractModule;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;


public class MongoModels extends AbstractModule{
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

        final Morphia morphia = new Morphia();

        // tell Morphia where to find your classes
        // can be called multiple times with different packages or classes
        morphia.mapPackage("com.thoughtworks.ketsu.domain");

        // create the Datastore connecting to the default port on the local host
        final Datastore datastore = morphia.createDatastore(new MongoClient(new MongoClientURI(connectURL)), "mongodb_store");
        datastore.ensureIndexes();
        bind(Datastore.class).toInstance(datastore);
    }
}
