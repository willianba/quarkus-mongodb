package org.acme.rest.json.imperactive;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.acme.rest.json.Fruit;

@Path("/fruits")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FruitResource {
  @Inject
  private FruitService fruitService;

  @GET
  public List<Fruit> list() {
    return fruitService.list();
  }

  @POST
  public List<Fruit> add(Fruit fruit) {
    fruitService.add(fruit);
    return this.list();
  }
}
