package hw_5.api.rick_and_morty;

import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class RickAndMortyApi extends BaseRickAndMortyApi {

    public static ValidatableResponse getRequest(String url, String endpoint) {
        return given()
                .when()
                .get(url + endpoint)
                .then();
    }
}
