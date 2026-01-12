package hw_3.jira_tests;

import hw_3.pages.LoginPage;
import hw_3.utils.CustomProperties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AuthorizationTest extends TestConfig {

    private final LoginPage loginPage = new LoginPage();

    private final String login = CustomProperties.getProps().getProperty("login");
    private final String password = CustomProperties.getProps().getProperty("password");

    @Test
    @DisplayName("Проверка авторизации")
    public void authorizationTest() {
        loginPage.authorizationInJira(login, password);
        loginPage.checkAuthorization();
    }
}
