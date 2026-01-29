package hw_5.steps;

import com.fasterxml.jackson.databind.node.ObjectNode;
import hw_5.api.spring.SpringApi;
import hw_5.utils.Config;
import hw_5.utils.JsonEditor;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Затем;
import io.cucumber.java.ru.И;
import io.qameta.allure.Step;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;

import java.util.UUID;

public class SpringStep {

    private static final ObjectNode BASE_JSON = JsonEditor.readObjectNodeFromFile("src/test/resources/user.json");

    private UUID rightToken;

    @Step("процесс регистрации")
    @Дано("^файл user.json, который отправляем POST-запросом, т.е. регистрируемся$")
    public void register() {
        SpringApi.postRequest("/api/register", BASE_JSON)
                .statusCode(HttpStatus.SC_OK);
    }

    @Step("авторизация с неправильным логином")
    @Затем("^пробуем авторизоваться с неправильным логином$")
    public void loginFail() {
        ObjectNode requestJson = JsonEditor.createCopy(BASE_JSON);
        String username = Config.get("wrong.name");
        String expected = Config.get("expected.fail.login");
        JsonEditor.change(requestJson, "username", username);
        String response = SpringApi.postRequest("/api/login", requestJson)
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .extract()
                .asString();
        Assertions.assertEquals(expected, response);
    }

    @Step("авторизация с неправильным паролем")
    @И("^пробуем авторизоваться с неправильным паролем$")
    public void passFail() {
        ObjectNode requestJson = JsonEditor.createCopy(BASE_JSON);
        String pass = Config.get("wrong.pass");
        JsonEditor.change(requestJson, "password", pass);
        String expected = Config.get("expected.fail.pass");
        String response = SpringApi.postRequest("/api/login", requestJson)
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .extract()
                .asString();

        Assertions.assertEquals(expected, response);
    }

    @Step("корректная авторизация")
    @Затем("^авторизуемся с корректными данными$")
    public void loginSuccess() {
        String response = SpringApi.postRequest("/api/login", BASE_JSON)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .asString();

        rightToken = UUID.fromString(response.split(":")[1].trim());
    }

    @Step("выход из учетной записи с неверным токеном")
    @И("^пробуем выйти из учетной записи с неверным токеном$")
    public void logoutFail() {
        UUID uuid = UUID.randomUUID();
        String expected = Config.get("expected.logout.fail");
        String response = SpringApi.getRequest("/api/logout", uuid)
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .extract()
                .asString();

        Assertions.assertEquals(expected, response);
    }

    @Step("выход из учетной записи с корректным токеном")
    @Затем("^выходим из учетной записи с корректным токеном$")
    public void logout() {
        String expected = Config.get("expected.logout.success");
        String response = SpringApi.getRequest("/api/logout", rightToken)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .asString();
        Assertions.assertEquals(expected, response);
    }
}
