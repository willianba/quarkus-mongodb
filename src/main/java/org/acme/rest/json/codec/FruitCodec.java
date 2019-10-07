package org.acme.rest.json.codec;

import java.util.UUID;

import com.mongodb.MongoClient;

import org.acme.rest.json.Fruit;
import org.bson.BsonReader;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.bson.BsonWriter;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.CollectibleCodec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

public class FruitCodec implements CollectibleCodec<Fruit> {
  private final Codec<Document> documentCodec;

  public FruitCodec() {
    this.documentCodec = MongoClient.getDefaultCodecRegistry().get(Document.class);
  }

  @Override
  public void encode(BsonWriter writer, Fruit fruit, EncoderContext encoderContext) {
    Document doc = new Document();
    doc.put("name", fruit.getName());
    doc.put("description", fruit.getDescription());
    documentCodec.encode(writer, doc, encoderContext);
  }

  @Override
  public Class<Fruit> getEncoderClass() {
    return Fruit.class;
  }

  @Override
  public Fruit decode(BsonReader reader, DecoderContext decoderContext) {
    Document doc = documentCodec.decode(reader, decoderContext);
    Fruit fruit = new Fruit();
    if (doc.getString("id") != null) {
      fruit.setId(doc.getString("id"));
    }
    fruit.setName(doc.getString("name"));
    fruit.setDescription(doc.getString("description"));
    return fruit;
  }

  @Override
  public Fruit generateIdIfAbsentFromDocument(Fruit document) {
    if (!documentHasId(document)) {
      document.setId(UUID.randomUUID().toString());
    }
    return document;
  }

  @Override
  public boolean documentHasId(Fruit document) {
    return document.getId() != null;
  }

  @Override
  public BsonValue getDocumentId(Fruit document) {
    return new BsonString(document.getId());
  }
}
