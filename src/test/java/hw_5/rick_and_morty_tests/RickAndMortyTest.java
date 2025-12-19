package hw_5.rick_and_morty_tests;

import hw_5.api.Specifications;
import hw_5.constants.EnvConstants;
import hw_5.steps.RickAndMortySteps;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RickAndMortyTest {

    private static final RickAndMortySteps rickAndMortySteps = new RickAndMortySteps();
    String charName = "Morty Smith";

    @BeforeAll
    public static void setUp() {
        RestAssured.requestSpecification = Specifications.baseRequestSpec(EnvConstants.RICKANDMORTY_URL);
        RestAssured.responseSpecification = Specifications.rickAndMortyBaseResponseSpecSuccess();
    }

    @Test
    @DisplayName("Запрос персонажа по имени 'Morty Smith'")
    public void getListCharName() {
        rickAndMortySteps.getListCharByName(charName);
        rickAndMortySteps.getLastEpisodeIdFromChars();
        rickAndMortySteps.getLastCharFromEpisode();
        rickAndMortySteps.getCharacterById();
        rickAndMortySteps.compareCharacters();
    }
}
