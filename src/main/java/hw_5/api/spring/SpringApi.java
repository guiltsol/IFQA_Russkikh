package hw_5.api.spring;

import io.restassured.response.ValidatableResponse;

import java.util.UUID;

import static io.restassured.RestAssured.given;

public class SpringApi extends BaseSpringApi {

    public static ValidatableResponse postRequest(String endpoint, Object body) {
        return given()
                .contentType("application/json")
                .body(body)
                .log().body()
                .when()
                .post(endpoint)
                .then();
    }

    public static ValidatableResponse getRequest(String endpoint, UUID token) {
        return given()
                .header("Authorization", token)
                .log().headers()
                .when()
                .get(endpoint)
                .then();
    }
}
