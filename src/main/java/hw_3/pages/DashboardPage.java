package hw_3.pages;

import com.codeborne.selenide.SelenideElement;
import hw_3.utils.CustomProperties;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DashboardPage {

    private final SelenideElement title = $x("//h3[text() = 'Назначенные мне']").as("Заголовок 'Назначенные мне' на странице дашборда");
    private final SelenideElement dropList = $x("//a[@id='browse_link']").as("Выпадающий список по кнопке 'Проекты'");
    private final SelenideElement testButton = $x("//a[@id='admin_main_proj_link_lnk']").as("Кнопка выбоа проекта 'Test'");

    private final String checkTest2 = CustomProperties.getProps().getProperty("checkTest2");

    private final ProjectTestPage projectTestPage = new ProjectTestPage();

    public String getTitle() {
        return title.shouldBe(visible, Duration.ofSeconds(10)).getText();
    }

    @Step("переходим в проект Test")
    public void goInTest() {
        dropList.shouldBe(visible, Duration.ofSeconds(10)).click();
        testButton.shouldBe(visible, Duration.ofSeconds(10)).click();
    }

    @Step("проверяем успешность перехода в проект Test")
    public void checkTheTransitionToTheProject() {
        String titleProjectPage = projectTestPage.getTitle();
        assertEquals(titleProjectPage, checkTest2);
    }
}
