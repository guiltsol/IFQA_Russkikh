package hw_3;

import hw_3.pages.DashboardPage;
import hw_3.pages.LoginPage;
import hw_3.pages.ProjectTestPage;
import hw_3.utils.CustomProperties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumberTestsTest extends TestConfig{

    private final LoginPage loginPage = new LoginPage();
    private final DashboardPage dashboardPage = new DashboardPage();
    private final ProjectTestPage projectTestPage = new ProjectTestPage();

    private final String login = CustomProperties.getProps().getProperty("login");
    private final String password = CustomProperties.getProps().getProperty("password");
    private final String checkTest1 = "Назначенные мне";
    private final String checkTest2 = "Открытые задачи";


    @Test
    @DisplayName("Проверка общего кол-ва задач в проекте Test и их подсчет")
    public void tasksNumberCounter() {
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
    }
}
