package hw_5.rick_and_morty_tests;

import hw_5.api.Specifications;
import hw_5.api.episode.Episode;
import hw_5.constants.EnvConstants;
import hw_5.model.Character;
import hw_5.steps.RickAndMortySteps;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

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
        List<Map<String, List<String>>> listOfMortys = rickAndMortySteps.getListCharByName(charName).getList("results");
        int lastEpisodesId = rickAndMortySteps.getLastEpisodeIdFromChars(listOfMortys);
        Episode episode = rickAndMortySteps.getDataById("episode", lastEpisodesId, Episode.class, HttpStatus.SC_OK);
        int lastCharactersId = rickAndMortySteps.getLastCharFromEpisode(episode);
        Character lastCharacter = rickAndMortySteps.getDataById("character", lastCharactersId,
                Character.class, HttpStatus.SC_OK);
        rickAndMortySteps.compareCharacters(listOfMortys, lastCharacter);
    }
}
