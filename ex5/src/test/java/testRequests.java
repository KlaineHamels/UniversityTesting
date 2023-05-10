import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;


public class testRequests {

    @BeforeAll
    public static void setup() {

        RestAssured.baseURI = "https://petstore.swagger.io/v2";

        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBodyPost)
                .when()
                .post("/pet")
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("11", response.jsonPath().getString("id"));
        Assertions.assertEquals("doggie", response.jsonPath().getString("name"));

    }


    private static String requestBodyPost = "{\n" +
            "  \"id\": 11,\n" +
            "  \"category\": {\n" +
            "    \"id\": 0,\n" +
            "    \"name\": \"string\"\n" +
            "  },\n" +
            "  \"name\": \"doggie\",\n" +
            "  \"photoUrls\": [\n" +
            "    \"string\"\n" +
            "  ],\n" +
            "  \"tags\": [\n" +
            "    {\n" +
            "      \"id\": 0,\n" +
            "      \"name\": \"string\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"status\": \"available\"\n" +
            "}";


    private static String requestBodyPut = "{\n" +
            "    \"id\": 24,\n" +
            "  \"category\": {\n" +
            "    \"id\": 0,\n" +
            "    \"name\": \"string\"\n" +
            "  },\n" +
            "  \"name\": \"catmeow\",\n" +
            "  \"photoUrls\": [\n" +
            "    \"string\"\n" +
            "  ],\n" +
            "  \"tags\": [\n" +
            "    {\n" +
            "      \"id\": 0,\n" +
            "      \"name\": \"string\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"status\": \"available\"\n" +
            "}";

    @Test
    public void testGet() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/pet/11")
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("11", response.jsonPath().getString("id"));
    }

    @Test
    public void testPut() {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBodyPut)
                .when()
                .put("/pet")
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("catmeow", response.jsonPath().getString("name"));
        Assertions.assertEquals("24", response.jsonPath().getString("id"));
    }

}
