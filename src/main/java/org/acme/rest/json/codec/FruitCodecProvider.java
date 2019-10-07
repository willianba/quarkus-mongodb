package org.acme.rest.json.codec;

import org.acme.rest.json.Fruit;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;

@SuppressWarnings("unchecked")
public class FruitCodecProvider implements CodecProvider {
  @Override
  public <T> Codec<T> get(Class<T> clazz, CodecRegistry registry) {
    if (clazz == Fruit.class) {
      return (Codec<T>) new FruitCodec();
    }
    return null;
  }
}
