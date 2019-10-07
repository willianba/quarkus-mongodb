package org.acme.rest.json.reactive;

import java.util.List;
import java.util.concurrent.CompletionStage;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.acme.rest.json.Fruit;

import io.quarkus.mongodb.ReactiveMongoClient;
import io.quarkus.mongodb.ReactiveMongoCollection;

@ApplicationScoped
public class ReactiveFruitService {
  @Inject
  private ReactiveMongoClient mongoClient;

  public CompletionStage<List<Fruit>> list() {
    return this.getCollection().find().toList().run();
  }

  public CompletionStage<Void> add(Fruit fruit) {
    return this.getCollection().insertOne(fruit);
  }

  private ReactiveMongoCollection<Fruit> getCollection() {
    return mongoClient.getDatabase("fruit").getCollection("fruit", Fruit.class);
  }
}
