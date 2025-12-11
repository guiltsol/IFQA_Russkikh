package hw_3.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class DashboardPage {

    private final SelenideElement title = $x("//h3[text() = 'Назначенные мне']").as("Заголовок 'Назначенные мне' на странице дашборда");
    private final SelenideElement dropList = $x("//a[@id='browse_link']").as("Выпадающий список по кнопке 'Проекты'");
    private final SelenideElement testButton = $x("//a[@id='admin_main_proj_link_lnk']").as("Кнопка выбоа проекта 'Test'");

    public String getTitle() {
        return title.shouldBe(Condition.visible, Duration.ofSeconds(10)).getText();
    }

    public void goInTest() {
        dropList.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
        testButton.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
    }
}
