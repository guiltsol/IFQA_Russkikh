package hw_3.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class ProjectTestPage {

    private final SelenideElement descriptionField = $x("//textarea[@class='iic-widget__summary focus-visible']").as("Поле ввода описания задачи");
    private final SelenideElement title = $x("//span[@id='issues-subnavigation-title']").as("Заголовок страницы по выбранному фильтру");
    private final SelenideElement btnGetAllTasks = $x("//button[@id='subnav-trigger']").as("Кнопка переключения фильтра");
    private final SelenideElement AllTasks = $x("//a[text() = 'Все задачи']").as("Кнопка выбора 'Все задачи' проекта");
    private final SelenideElement numberTasks = $x("//div[@class='showing']/span[contains(text(),'из')]").as("Раздел кол-ва задачи на проекте");
    private final SelenideElement btnCreateNewTask = $x("//div[@class='iic-trigger']/button[@class='aui-button aui-button-subtle']").as("Кнопка быстрого создания задачи(плюсик)");
    private final SelenideElement inputSearch = $x("//input[@id='quickSearchInput']").as("Поле поиска сверху");

    public String getTitle() {
        return title.shouldBe(visible, Duration.ofSeconds(10)).getText();
    }

    public void switchToAllTasks() {
        String value = numberTasks.shouldBe(visible, Duration.ofSeconds(10)).getText();
        btnGetAllTasks.shouldBe(visible, Duration.ofSeconds(10)).click();
        AllTasks.shouldBe(visible, Duration.ofSeconds(10)).click();
        numberTasks.shouldNot(text(value), Duration.ofSeconds(10));
    }

    public void quickSearch(String text) {
        inputSearch.sendKeys(text + Keys.RETURN);
    }

    public Integer parseNumber() {
        String[] value = numberTasks.shouldBe(visible, Duration.ofSeconds(10)).getText().split("\\D+");
        return Integer.parseInt(value[value.length - 1]);
    }

    public void createNewTask() {
        String value = numberTasks.shouldBe(visible, Duration.ofSeconds(10)).getText();
        btnCreateNewTask.shouldBe(visible, Duration.ofSeconds(10)).click();
        descriptionField.shouldBe(visible, Duration.ofSeconds(10)).sendKeys("Test" + Keys.RETURN);
        numberTasks.shouldNot(text(value), Duration.ofSeconds(10));
    }
}
