package hw_3.jira_tests;

import hw_3.pages.DashboardPage;
import hw_3.pages.LoginPage;
import hw_3.pages.ProjectTestPage;
import hw_3.pages.TestSeleniumATHomeworkPage;
import hw_3.utils.CustomProperties;
import hw_3.webhooks.WebHook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CheckoutTestSeleniumATHomeworkTest extends WebHook {

    private final LoginPage loginPage = new LoginPage();
    private final DashboardPage dashboardPage = new DashboardPage();
    private final ProjectTestPage projectTestPage = new ProjectTestPage();
    private final TestSeleniumATHomeworkPage testSeleniumATHomeworkPage = new TestSeleniumATHomeworkPage();

    private final String login = CustomProperties.getProps().getProperty("login");
    private final String password = CustomProperties.getProps().getProperty("password");
    private final String request = CustomProperties.getProps().getProperty("request");
    private final String status = CustomProperties.getProps().getProperty("statusDo");
    private final String version = CustomProperties.getProps().getProperty("version");

    @Test
    @DisplayName("Проверка статуса и версии задачи - TestSeleniumATHomework")
    public void taskStatusCheck() {
        loginPage.authorizationInJira(login, password);
        loginPage.checkAuthorization();

        dashboardPage.goInTest();
        dashboardPage.checkTheTransitionToTheProject();

        projectTestPage.switchToAllTasks();
        projectTestPage.createNewTask();
        projectTestPage.checkNumberTasks();

        projectTestPage.quickSearch(request);
        testSeleniumATHomeworkPage.checkStatusAndVersion(status, version);
    }
}
