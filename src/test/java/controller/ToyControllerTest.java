package controller;

import dto.ToyRequestDTO;
import dto.ToyResponseDTO;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import java.net.URL;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class ToyControllerTest {

  @TestHTTPEndpoint(ToyController.class)
  @TestHTTPResource
  URL url;

  @Test
  void getHelloTest() {
    System.out.println("url string is " + url.toString());
    given()
        .when()
        .get(url.toString() + "/hello")
        .then()
        .statusCode(200)
        .body(is("Hello world"));
  }

  @Test
  void postTest() {
    var request = new ToyRequestDTO("testing-NAME");
    var response = given()
        .contentType(ContentType.JSON)
        .body(request)
        .when()
        .post(url.toString())
        .then()
        .statusCode(Response.Status.CREATED.getStatusCode())
        .extract()
        .as(ToyResponseDTO.class);

    assertEquals(request.getName(), response.name());
  }

  @Test
  void deleteTest() {
    given()
        .contentType(ContentType.JSON)
        .body(new ToyRequestDTO("to delete"))
        .when()
        .post(url.toString());

    given()
        .when()
        .delete(url.toString() + "/2")
        .then()
        .statusCode(Response.Status.NO_CONTENT.getStatusCode());
  }
}