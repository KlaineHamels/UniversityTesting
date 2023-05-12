import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;


public class testRequests {

    @BeforeAll
    public static void setup() {

        RestAssured.baseURI = "https://petstore.swagger.io/v2";

        JSONObject requestBody = new JSONObject();
        requestBody.put("id", 11);
        requestBody.put("name","doggie");
        requestBody.put("status"," ");

        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody.toString())
                .when()
                .post("/pet")
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("11", response.jsonPath().getString("id"));
        Assertions.assertEquals("doggie", response.jsonPath().getString("name"));

    }



    @Test
    public void testGet() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/pet/11")
                .then()
                .extract().response();

        assertEquals(200, response.statusCode());
        assertEquals("11", response.jsonPath().getString("id"));
    }

    @Test
    public void testPut() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("id", 24);
        requestBody.put("name","catty");

        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody.toString())
                .when()
                .put("/pet6t")
                .then()
                .extract().response();

        assertEquals(200, response.statusCode());
        assertEquals("catty", response.jsonPath().getString("name"));
        assertEquals("24", response.jsonPath().getString("id"));
    }

    @Test
    public void testInvalidId() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/pet/25824двыаыавп%^$#%fcфывар")
                .then()
                .extract().response();

        assertEquals(404, response.statusCode());
    }

    @Test
    public void testLongId() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/pet/2984928341342395420435902395028394718927401395")
                .then()
                .extract().response();

        assertEquals(404, response.statusCode());
    }


}
