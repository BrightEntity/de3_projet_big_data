package de3.vanelle_fiorini.projet_big_data.service.impl;

import com.mongodb.client.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MongoDBClientServiceImpl {
    MongoClient mongoClient;

    @Autowired
    public MongoDBClientServiceImpl(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    public void addResultsToDatabase() {
        System.out.println(mongoClient.listDatabaseNames().map(n -> n + "\n"));
    }
}
