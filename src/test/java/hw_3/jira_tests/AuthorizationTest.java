package hw_3.jira_tests;

import hw_3.pages.LoginPage;
import hw_3.utils.CustomProperties;
import hw_3.webhooks.WebHook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@DisplayName("Тестирование авторизации")
public class AuthorizationTest extends WebHook {

    private final LoginPage loginPage = new LoginPage();

    private final String login = CustomProperties.getProps().getProperty("login");
    private final String password = CustomProperties.getProps().getProperty("password");

    @Test
    @Tag("ID-1")
    @DisplayName("Проверка авторизации")
    public void authorizationTest() {
        loginPage.authorizationInJira(login, password);
        loginPage.checkAuthorization();
    }
}
