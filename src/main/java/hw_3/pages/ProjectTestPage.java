package hw_3.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class ProjectTestPage {

    private final SelenideElement descriptionField = $x("//textarea[@class='iic-widget__summary focus-visible']").as("Поле ввода описания задачи");
    private final SelenideElement title = $x("//span[@id='issues-subnavigation-title']").as("Заголовок страницы по выбранному фильтру");
    private final SelenideElement btnGetAllTasks = $x("//button[@id='subnav-trigger']").as("Кнопка переключения фильтра");
    private final SelenideElement AllTasks = $x("//a[text() = 'Все задачи']").as("Кнопка выбора 'Все задачи' проекта");
    private final SelenideElement numberTasks = $x("//div[@class='showing']/span[contains(text(),'из')]").as("Раздел кол-ва задачи на проекте");
    private final SelenideElement btnCreateNewTask = $x("//div[@class='iic-trigger']/button[@class='aui-button aui-button-subtle']").as("Кнопка быстрого создания задачи(плюсик)");
    private final SelenideElement refreshBtn = $x("//a[@title='Обновить результаты']").as("Кнопка обновления тасок");
    private final SelenideElement inputSearch = $x("//input[@id='quickSearchInput']").as("Поле поиска сверху");
    private final SelenideElement divInfo = $x("//div[@class='details-layout']");

    public String getTitle() {
        return title.shouldBe(Condition.visible, Duration.ofSeconds(10)).getText();
    }

    public void switchToAllTasks() {
        btnGetAllTasks.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
        AllTasks.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
        divInfo.shouldBe(Condition.visible, Duration.ofSeconds(10)).shouldBe(Condition.enabled, Duration.ofSeconds(10))
                .shouldBe(Condition.clickable, Duration.ofSeconds(10));
        refreshBtn.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
    }

    public void quickSearch(String text) {
        inputSearch.sendKeys(text + Keys.RETURN);
    }

    public Integer parseNumber() {
        divInfo.shouldBe(Condition.visible, Duration.ofSeconds(10)).shouldBe(Condition.enabled, Duration.ofSeconds(10))
                .shouldBe(Condition.clickable, Duration.ofSeconds(10));
        refreshBtn.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
        String[] value = numberTasks.shouldBe(Condition.visible, Duration.ofSeconds(10)).getText().split("\\D+");
        return Integer.parseInt(value[value.length - 1]);
    }

    public void createNewTask() {
        btnCreateNewTask.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
        descriptionField.shouldBe(Condition.visible, Duration.ofSeconds(10)).sendKeys("Test" + Keys.RETURN);
        divInfo.shouldBe(Condition.visible, Duration.ofSeconds(10)).shouldBe(Condition.enabled, Duration.ofSeconds(10))
                .shouldBe(Condition.clickable, Duration.ofSeconds(10));
        refreshBtn.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
    }
}
