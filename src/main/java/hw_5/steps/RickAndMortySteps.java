package hw_5.steps;

import hw_5.api.episode.Episode;
import hw_5.api.rick_and_morty.RickAndMortyApi;
import hw_5.constants.EnvConstants;
import hw_5.model.Character;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;

import java.util.*;

public class RickAndMortySteps {

    private List<Map<String, List<String>>> listOfMortys;
    private int lastEpisodeId;
    private Episode lastEpisode;
    private int lastCharacterId;
    private Character lastCharacter;

    public static int extractNumber(String text) {
        return Integer.parseInt(text.substring(text.lastIndexOf("/") + 1));
    }

    public void getListCharByName(String name) {

        String endpoint = "/character/?name=" + name;

        listOfMortys = RickAndMortyApi.getRequest(EnvConstants.RICKANDMORTY_URL, endpoint)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .jsonPath()
                .getList("results");
    }

    public void getLastEpisodeIdFromChars() {
        Set<Integer> episodeIds = new TreeSet<>();
        for (Map<String, List<String>> charData : listOfMortys) {
            List<String> episodes = charData.get("episode");
            for (String episodeUrl : episodes) {
                episodeIds.add(extractNumber(episodeUrl));
            }
        }
        lastEpisodeId = Collections.max(episodeIds);
        lastEpisode = RickAndMortyApi.getRequest(EnvConstants.RICKANDMORTY_URL, "/episode/" + lastEpisodeId)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(Episode.class);
    }

    public void getLastCharFromEpisode() {
        List<String> characterUrls = lastEpisode.characters;
        List<Integer> characterIds = new ArrayList<>();
        for (String url : characterUrls) {
            characterIds.add(extractNumber(url));
        }
        lastCharacterId = characterIds.get(characterIds.size() - 1);
    }

    public void getCharacterById() {
        lastCharacter = RickAndMortyApi.getRequest(EnvConstants.RICKANDMORTY_URL, "/character/" + lastCharacterId)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(Character.class);
    }

    public void compareCharacters() {
        for (Map<String, List<String>> charData : listOfMortys) {
            Assertions.assertEquals(lastCharacter.getSpecies(), charData.get("species"));
            Assertions.assertNotEquals(lastCharacter.getLocation(), charData.get("location"));
        }
    }
}
