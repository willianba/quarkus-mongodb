package org.acme.rest.json;

import java.util.List;

import io.quarkus.mongodb.panache.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;

@MongoEntity(collection = "fruits")
public class Fruit extends PanacheMongoEntity {
  private String name;
  private String description;

  public String getName() {
    return name.toUpperCase();
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public static List<Fruit> findByName(String name) {
    return list("name", name);
  }
}
