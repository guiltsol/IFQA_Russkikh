package hw_5.steps;

import hw_5.api.episode.Episode;
import hw_5.api.rick_and_morty.RickAndMortyApi;
import hw_5.constants.EnvConstants;
import hw_5.model.Character;
import io.restassured.path.json.JsonPath;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;

import java.util.*;

public class RickAndMortySteps {

    public static int extractNumber(String text) {
        return Integer.parseInt(text.substring(text.lastIndexOf("/") + 1));
    }

    public JsonPath getListCharByName(String name) {

        String endpoint = "/character/?name=" + name;

        return RickAndMortyApi.getRequest(EnvConstants.RICKANDMORTY_URL, endpoint)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .jsonPath();
    }

    public int getLastEpisodeIdFromChars(List<Map<String, List<String>>> characters) {

        Set<Integer> episodeIds = new TreeSet<>();

        for (Map<String, List<String>> charData : characters) {
            List<String> episodes = charData.get("episode");
            for (String episodeUrl : episodes) {
                episodeIds.add(extractNumber(episodeUrl));
            }
        }
        return Collections.max(episodeIds);
    }

    public int getLastCharFromEpisode(Episode episode) {
        List<String> characterUrls = episode.characters;
        List<Integer> characterIds = new ArrayList<>();
        for (String url : characterUrls) {
            characterIds.add(extractNumber(url));
        }
        return characterIds.get(characterIds.size() - 1);
    }

    public <T> T getDataById(String resourcetype, int id, Class<T> responseType, int expectedStatusCode) {
        String endpoint = "/" + resourcetype + "/" + id;
        return RickAndMortyApi.getRequest(EnvConstants.RICKANDMORTY_URL, endpoint)
                .statusCode(expectedStatusCode)
                .extract()
                .body()
                .as(responseType);
    }

    public void compareCharacters(List<Map<String, List<String>>> characters, Character lastCharacter) {
        for (Map<String, List<String>> charData : characters) {
            Assertions.assertEquals(lastCharacter.getSpecies(), charData.get("species"));
            Assertions.assertNotEquals(lastCharacter.getLocation(), charData.get("location"));
        }
    }
}
