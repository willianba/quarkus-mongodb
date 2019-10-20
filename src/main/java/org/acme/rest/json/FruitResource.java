package org.acme.rest.json;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/fruits")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FruitResource {
  @GET
  public List<Fruit> list() {
    return Fruit.listAll();
  }

  @POST
  public Response create(Fruit fruit) {
    fruit.persist();
    return Response.status(201).build();
  }

  @GET
  @Path("/{id}")
  public Fruit get(String id) {
    return Fruit.findById(id);
  }

  @PUT
  @Path("/{id}")
  public void update(@PathParam("id") String id, Fruit fruit) {
    fruit.update();
  }

  @DELETE
  @Path("/{id}")
  public void delete(String id) {
    Fruit fruit = Fruit.findById(id);
    fruit.delete();
  }

  @GET
  @Path("/search/{name}")
  public List<Fruit> search(String name) {
    return Fruit.findByName(name);
  }

  @GET
  @Path("/count")
  public Long count() {
    return Fruit.count();
  }
}