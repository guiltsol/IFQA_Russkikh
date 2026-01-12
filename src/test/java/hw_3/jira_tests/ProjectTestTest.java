package hw_3.jira_tests;

import hw_3.pages.DashboardPage;
import hw_3.pages.LoginPage;
import hw_3.pages.ProjectTestPage;
import hw_3.utils.CustomProperties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProjectTestTest extends TestConfig {

    private final LoginPage loginPage = new LoginPage();
    private final DashboardPage dashboardPage = new DashboardPage();
    private final ProjectTestPage projectTestPage = new ProjectTestPage();

    private final String login = CustomProperties.getProps().getProperty("login");
    private final String password = CustomProperties.getProps().getProperty("password");
    private final String checkTest1 = CustomProperties.getProps().getProperty("checkTest1");
    private final String checkTest2 = CustomProperties.getProps().getProperty("checkTest2");

    @Test
    @DisplayName("Проверка перерехода в проект Test")
    public void goToProjectTest() {
        loginPage.authorizationInJira(login, password);
        String titleDashboardPage = dashboardPage.getTitle();
        assertEquals(titleDashboardPage, checkTest1);

        dashboardPage.goInTest();
        String titleProjectPage = projectTestPage.getTitle();
        assertEquals(titleProjectPage, checkTest2);
    }
}
