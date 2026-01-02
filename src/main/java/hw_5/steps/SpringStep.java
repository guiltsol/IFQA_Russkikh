package hw_5.steps;

import com.fasterxml.jackson.databind.node.ObjectNode;
import hw_5.api.spring.SpringApi;
import hw_5.utils.JsonEditor;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Затем;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Также;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;

import java.util.UUID;

public class SpringStep {

    private static final ObjectNode BASE_JSON = JsonEditor.readObjectNodeFromFile("src/test/resources/user.json");

    private UUID rightToken;

    @Дано("^json файл user.json, который отправляем POST-запросом, т.е. регистрируемся$")
    public void register() {
        SpringApi.postRequest("/api/register", BASE_JSON)
                .statusCode(HttpStatus.SC_OK);
    }

    @Затем(("^пробуем авторизироваться с неправильным логином, например - '(.*)'. Ожидаем ответ - '(.*)'$"))
    public void loginFail(String username, String expected) {
        ObjectNode requestJson = JsonEditor.createCopy(BASE_JSON);
        JsonEditor.change(requestJson, "username", username);

        String response = SpringApi.postRequest("/api/login", requestJson)
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .extract()
                .asString();

        Assertions.assertEquals(expected, response);
    }

    @Также("^пробуем авторизироваться с неправильным паролем, наприемр - '(.*)'. Ожидаем ответ - '(.*)'$")
    public void passFail(String pass, String expected) {
        ObjectNode requestJson = JsonEditor.createCopy(BASE_JSON);
        JsonEditor.change(requestJson, "password", pass);

        String response = SpringApi.postRequest("/api/login", requestJson)
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .extract()
                .asString();

        Assertions.assertEquals(expected, response);
    }

    @Затем("^авторизуемся с корректными данными из user.json$")
    public void loginSuccess() {
        String response = SpringApi.postRequest("/api/login", BASE_JSON)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .asString();

        rightToken = UUID.fromString(response.split(":")[1].trim());
    }

    @И("^пробуем выйти из учетной записи с неверным токеном. Ожидаем ответ - '(.*)'$")
    public void logoutFail(String expected) {
        UUID uuid = UUID.randomUUID();
        String response = SpringApi.getRequest("/api/logout", uuid)
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .extract()
                .asString();

        Assertions.assertEquals(expected, response);
    }

    @Затем("^выходим из учетной записи с корректным токеном. Ожидаем ответ - '(.*)'$")
    public void logout(String expected) {
        String response = SpringApi.getRequest("/api/logout", rightToken)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .asString();
        Assertions.assertEquals(expected, response);
    }
}
