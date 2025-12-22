package hw_5.steps;

import com.fasterxml.jackson.databind.node.ObjectNode;
import hw_5.api.spring.SpringApi;
import hw_5.utils.JsonEditor;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;

import java.util.UUID;

public class SpringStep {

    private static final ObjectNode BASE_JSON = JsonEditor.readObjectNodeFromFile("src/test/resources/user.json");

    public void register() {
        SpringApi.postRequest("/api/register", BASE_JSON)
                .statusCode(HttpStatus.SC_OK)
                .log().all();
    }

    public void loginFail(String username, String expected) {
        ObjectNode requestJson = JsonEditor.createCopy(BASE_JSON);
        JsonEditor.change(requestJson, "username", username);

        String response = SpringApi.postRequest("/api/login", requestJson)
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .log().all()
                .extract()
                .asString();

        Assertions.assertEquals(expected, response);
    }

    public void passFail(String pass, String expected) {
        ObjectNode requestJson = JsonEditor.createCopy(BASE_JSON);
        JsonEditor.change(requestJson, "password", pass);

        String response = SpringApi.postRequest("/api/login", requestJson)
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .log().all()
                .extract()
                .asString();

        Assertions.assertEquals(expected, response);
    }

    public UUID loginSuccess() {
        String response = SpringApi.postRequest("/api/login", BASE_JSON)
                .statusCode(HttpStatus.SC_OK)
                .log().all()
                .extract()
                .asString();

        return UUID.fromString(response.split(":")[1].trim());
    }

    public void logoutFail(String expected) {
        UUID uuid = UUID.randomUUID();
        String response = SpringApi.getRequest("/api/logout", uuid)
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .log().all()
                .extract()
                .asString();

        Assertions.assertEquals(expected, response);
    }

    public void logout(UUID token, String expected) {
        String response = SpringApi.getRequest("/api/logout", token)
                .statusCode(HttpStatus.SC_OK)
                .log().all()
                .extract()
                .asString();
        Assertions.assertEquals(expected, response);
    }
}
