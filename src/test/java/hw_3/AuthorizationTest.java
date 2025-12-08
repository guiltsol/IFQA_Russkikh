package hw_3;

import hw_3.pages.DashboardPage;
import hw_3.pages.LoginPage;
import hw_3.utils.CustomProperties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthorizationTest extends TestConfig {

    private final LoginPage loginPage = new LoginPage();
    private final DashboardPage dashboardPage = new DashboardPage();

    private final String login = CustomProperties.getProps().getProperty("login");
    private final String password = CustomProperties.getProps().getProperty("password");
    private final String checkTest1 = "Назначенные мне";

    @Test
    @DisplayName("Проверка авторизации")
    public void authorizationTest() {
        loginPage.authorizationInJira(login, password);
        String titleDashboardPage = dashboardPage.getTitle();
        assertEquals(titleDashboardPage, checkTest1);
    }
}
