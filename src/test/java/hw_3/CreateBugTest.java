package hw_3;

import hw_3.pages.*;
import hw_3.utils.CustomProperties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateBugTest extends TestConfig {

    private final LoginPage loginPage = new LoginPage();
    private final DashboardPage dashboardPage = new DashboardPage();
    private final ProjectTestPage projectTestPage = new ProjectTestPage();
    private final TestSeleniumATHomeworkPage testSeleniumATHomeworkPage = new TestSeleniumATHomeworkPage();
    private final CreateBugPage createBugPage = new CreateBugPage();

    private final String login = CustomProperties.getProps().getProperty("login");
    private final String password = CustomProperties.getProps().getProperty("password");

    private final String projectName = "Test";
    private final String titleTask = "New_Bug_AT2";
    private final String description = "Something wrong!";
    private final String mark = "123";
    private final String environment = "Something env!";
    private final String task = "Test-184712";
    private final String epic = "epic";
    private final String sprint = "Спринт 1";
    private final String request = "TestSeleniumATHomework";
    private final String statusForTestSeleniumATHomeworkPage = "СДЕЛАТЬ";
    private final String statusForBug = "ГОТОВО";
    private final String version = "Version 2.0";
    private final String checkTest1 = "Назначенные мне";
    private final String checkTest2 = "Открытые задачи";

    @Test
    @DisplayName("Проверка жизненного цикла бага")
    public void newBugMaker() {
        loginPage.authorizationInJira(login, password);
        String titleDashboardPage = dashboardPage.getTitle();
        assertEquals(titleDashboardPage, checkTest1);

        dashboardPage.goInTest();
        String titleProjectPage = projectTestPage.getTitle();
        assertEquals(titleProjectPage,checkTest2);

        projectTestPage.switchToAllTasks();
        Integer oldAllTasks = projectTestPage.parseNumber();
        projectTestPage.createNewTask();
        Integer newAllTasks = projectTestPage.parseNumber();
        assertEquals(oldAllTasks + 1, newAllTasks);

        projectTestPage.quickSearch(request);
        assertEquals(statusForTestSeleniumATHomeworkPage, testSeleniumATHomeworkPage.parseStatus());
        assertEquals(version, testSeleniumATHomeworkPage.parseVersion());

        createBugPage.bugHistory(projectName,titleTask,description,mark,environment,task,epic,sprint);
        assertEquals(statusForBug,testSeleniumATHomeworkPage.parseStatus());
    }
}
