package hw_3;

import hw_3.pages.DashboardPage;
import hw_3.pages.LoginPage;
import hw_3.pages.ProjectTestPage;
import hw_3.pages.TestSeleniumATHomeworkPage;
import hw_3.utils.CustomProperties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckoutTestSeleniumATHomeworkTest extends TestConfig {

    private final LoginPage loginPage = new LoginPage();
    private final DashboardPage dashboardPage = new DashboardPage();
    private final ProjectTestPage projectTestPage = new ProjectTestPage();
    private final TestSeleniumATHomeworkPage testSeleniumATHomeworkPage = new TestSeleniumATHomeworkPage();

    private final String login = CustomProperties.getProps().getProperty("login");
    private final String password = CustomProperties.getProps().getProperty("password");
    private final String request = CustomProperties.getProps().getProperty("request");
    private final String status = CustomProperties.getProps().getProperty("statusDo");
    private final String version = CustomProperties.getProps().getProperty("version");
    private final String checkTest1 = CustomProperties.getProps().getProperty("checkTest1");
    private final String checkTest2 = CustomProperties.getProps().getProperty("checkTest2");

    @Test
    @DisplayName("Проверка статуса и версии задачи - TestSeleniumATHomework")
    public void taskStatusCheck() {
        loginPage.authorizationInJira(login, password);
        String titleDashboardPage = dashboardPage.getTitle();
        assertEquals(titleDashboardPage, checkTest1);

        dashboardPage.goInTest();
        String titleProjectPage = projectTestPage.getTitle();
        assertEquals(titleProjectPage, checkTest2);

        projectTestPage.switchToAllTasks();
        Integer oldAllTasks = projectTestPage.parseNumber();
        projectTestPage.createNewTask();
        Integer newAllTasks = projectTestPage.parseNumber();
        assertEquals(oldAllTasks + 1, newAllTasks);

        projectTestPage.quickSearch(request);
        assertEquals(status, testSeleniumATHomeworkPage.parseStatus());
        assertEquals(version, testSeleniumATHomeworkPage.parseVersion());
    }
}
