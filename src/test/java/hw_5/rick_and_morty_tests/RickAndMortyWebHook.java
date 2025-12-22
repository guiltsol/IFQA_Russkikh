package hw_5.rick_and_morty_tests;

import hw_5.api.Specifications;
import hw_5.constants.EnvConstants;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class RickAndMortyWebHook {

    @BeforeAll
    public static void setUp() {
        RestAssured.requestSpecification = Specifications.baseRequestSpec(EnvConstants.RICKANDMORTY_URL);
        RestAssured.responseSpecification = Specifications.rickAndMortyBaseResponseSpecSuccess();
    }
}
