package hw_5.rick_and_morty_tests;

import hw_5.steps.RickAndMortySteps;
import hw_5.utils.Config;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RickAndMortyTest extends RickAndMortyWebHook {

    private static final RickAndMortySteps rickAndMortySteps = new RickAndMortySteps();
    private final String charName = Config.get("charName");

    @Test
    @DisplayName("Запрос персонажа по имени 'Morty Smith'")
    public void getListCharName() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        rickAndMortySteps.getListCharByName(charName);
        rickAndMortySteps.getLastEpisodeIdFromChars();
        rickAndMortySteps.getLastCharFromEpisode();
        rickAndMortySteps.getCharacterById();
        rickAndMortySteps.compareCharacters();
    }
}
