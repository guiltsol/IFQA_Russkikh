package hw_5.api.spring;

import hw_5.utils.MaskingAllureFilter;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.ValidatableResponse;

import java.util.UUID;

import static io.restassured.RestAssured.given;

public class SpringApi extends BaseSpringApi {

    public static ValidatableResponse postRequest(String endpoint, Object body) {
        return given()
                .filter(new MaskingAllureFilter())  // Используем наш фильтр вместо AllureRestAssured
                .contentType("application/json")
                .body(body)
                .when()
                .post(endpoint)
                .then();
    }

    public static ValidatableResponse getRequest(String endpoint, UUID token) {
        return given()
                .filter(new AllureRestAssured())
                .header("Authorization", token)
                .when()
                .get(endpoint)
                .then();
    }
}
