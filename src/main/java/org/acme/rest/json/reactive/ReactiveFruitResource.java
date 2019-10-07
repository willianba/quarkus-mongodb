package org.acme.rest.json.reactive;

import java.util.List;
import java.util.concurrent.CompletionStage;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.acme.rest.json.Fruit;

@Path("/reactive_fruits")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReactiveFruitResource {
  @Inject
  private ReactiveFruitService fruitService;

  @GET
  public CompletionStage<List<Fruit>> list() {
    return fruitService.list();
  }

  @POST
  public CompletionStage<List<Fruit>> add(Fruit fruit) {
    fruitService.add(fruit);
    return this.list();
  }
}
