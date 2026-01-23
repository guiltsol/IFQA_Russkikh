package hw_3.jira_tests;

import hw_3.pages.DashboardPage;
import hw_3.pages.LoginPage;
import hw_3.utils.CustomProperties;
import hw_3.webhooks.WebHook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@DisplayName("Тестирование перехода в проект - Test")
public class ProjectTestTest extends WebHook {

    private final LoginPage loginPage = new LoginPage();
    private final DashboardPage dashboardPage = new DashboardPage();

    private final String login = CustomProperties.getProps().getProperty("login");
    private final String password = CustomProperties.getProps().getProperty("password");

    @Test
    @Tag("ID-2")
    @DisplayName("Проверка перехода в проект Test")
    public void goToProjectTest() {
        loginPage.authorizationInJira(login, password);
        loginPage.checkAuthorization();

        dashboardPage.goInTest();
        dashboardPage.checkTheTransitionToTheProject();
    }
}
