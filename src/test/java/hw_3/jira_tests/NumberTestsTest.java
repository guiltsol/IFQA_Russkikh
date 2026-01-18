package hw_3.jira_tests;

import hw_3.pages.DashboardPage;
import hw_3.pages.LoginPage;
import hw_3.pages.ProjectTestPage;
import hw_3.utils.CustomProperties;
import hw_3.webhooks.WebHook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NumberTestsTest extends WebHook {

    private final LoginPage loginPage = new LoginPage();
    private final DashboardPage dashboardPage = new DashboardPage();
    private final ProjectTestPage projectTestPage = new ProjectTestPage();

    private final String login = CustomProperties.getProps().getProperty("login");
    private final String password = CustomProperties.getProps().getProperty("password");

    @Test
    @DisplayName("Проверка общего кол-ва задач в проекте Test и их подсчет")
    public void tasksNumberCounter() {
        loginPage.authorizationInJira(login, password);
        loginPage.checkAuthorization();

        dashboardPage.goInTest();
        dashboardPage.checkTheTransitionToTheProject();

        projectTestPage.switchToAllTasks();
        projectTestPage.createNewTask();
        projectTestPage.checkNumberTasks();
    }
}
