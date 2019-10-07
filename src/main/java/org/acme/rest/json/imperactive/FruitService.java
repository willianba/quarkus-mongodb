package org.acme.rest.json.imperactive;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import org.acme.rest.json.Fruit;

@ApplicationScoped
public class FruitService {
  @Inject
  private MongoClient mongoClient;

  public List<Fruit> list() {
    List<Fruit> list = new ArrayList<>();
    MongoCursor<Fruit> cursor = this.getCollection().find().iterator();
    try {
      while (cursor.hasNext()) {
        list.add(cursor.next());
      }
    } finally {
      cursor.close();
    }
    return list;
  }

  public void add(Fruit fruit) {
    this.getCollection().insertOne(fruit);
  }

  private MongoCollection<Fruit> getCollection() {
      return mongoClient.getDatabase("fruit").getCollection("fruit", Fruit.class);
  }
}
