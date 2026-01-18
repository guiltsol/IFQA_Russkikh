package hw_3.jira_tests;

import hw_3.pages.*;
import hw_3.utils.CustomProperties;
import hw_3.webhooks.WebHook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CreateBugTest extends WebHook {

    private final LoginPage loginPage = new LoginPage();
    private final DashboardPage dashboardPage = new DashboardPage();
    private final ProjectTestPage projectTestPage = new ProjectTestPage();
    private final TestSeleniumATHomeworkPage testSeleniumATHomeworkPage = new TestSeleniumATHomeworkPage();
    private final CreateBugPage createBugPage = new CreateBugPage();

    private final String login = CustomProperties.getProps().getProperty("login");
    private final String password = CustomProperties.getProps().getProperty("password");
    private final String projectName = CustomProperties.getProps().getProperty("projectName");
    private final String titleTask = CustomProperties.getProps().getProperty("titleTask");
    private final String description = CustomProperties.getProps().getProperty("description");
    private final String priority = CustomProperties.getProps().getProperty("priority");
    private final String mark = CustomProperties.getProps().getProperty("mark");
    private final String environment = CustomProperties.getProps().getProperty("environment");
    private final String task = CustomProperties.getProps().getProperty("task");
    private final String sprint = CustomProperties.getProps().getProperty("sprint");
    private final String request = CustomProperties.getProps().getProperty("request");
    private final String version = CustomProperties.getProps().getProperty("version");
    private final String statusDo = CustomProperties.getProps().getProperty("statusDo");
    private final String statusWork = CustomProperties.getProps().getProperty("statusWork");
    private final String statusOK = CustomProperties.getProps().getProperty("statusOk");
    private final String type_task = CustomProperties.getProps().getProperty("type_task");

    @Test
    @DisplayName("Проверка жизненного цикла бага")
    public void newBugMaker() {
        loginPage.authorizationInJira(login, password);
        loginPage.checkAuthorization();

        dashboardPage.goInTest();
        dashboardPage.checkTheTransitionToTheProject();

        projectTestPage.switchToAllTasks();
        projectTestPage.createNewTask();
        projectTestPage.checkNumberTasks();

        projectTestPage.quickSearch(request);
        testSeleniumATHomeworkPage.checkStatusAndVersion(statusDo, version);

        createBugPage.bugHistory(projectName, titleTask, description, priority, mark, environment, task, sprint, type_task);
        createBugPage.switchingTheBugState(statusDo, statusWork, statusOK);
    }
}
