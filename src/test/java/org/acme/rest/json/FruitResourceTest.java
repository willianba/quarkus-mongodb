package org.acme.rest.json;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class FruitResourceTest {
  @Test
  public void testHelloEndpoint() {
    given()
    .when()
      .get("/")
    .then()
      .statusCode(200);
  }
}
