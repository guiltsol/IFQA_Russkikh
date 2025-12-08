package hw_3.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import java.time.Duration;
import static com.codeborne.selenide.Selenide.$x;

public class ProjectTestPage {

    private final SelenideElement descriptionField = $x("//textarea[@class='iic-widget__summary focus-visible']");
    private final SelenideElement title = $x("//span[@id='issues-subnavigation-title']");
    private final SelenideElement btnGetAllTasks = $x("//button[@id='subnav-trigger']");
    private final SelenideElement AllTasks = $x("//a[text() = 'Все задачи']");
    private final SelenideElement numberTasks = $x("//div[@class='showing']/span[contains(text(),'из')]");
    private final SelenideElement btnCreateNewTask =$x("//div[@class='iic-trigger']/button[@class='aui-button aui-button-subtle']");
    private final SelenideElement refreshBtn = $x("//a[@title='Обновить результаты']");
    private final SelenideElement inputSearch = $x("//input[@id='quickSearchInput']");

    public String getTitle(){
        title.shouldBe(Condition.visible, Duration.ofSeconds(10));
        return title.getText();
    }

    public void switchToAllTasks() {
        btnGetAllTasks.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
        AllTasks.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
    }

    public void quickSearch(String text) {
        inputSearch.sendKeys(text + Keys.RETURN);
    }

    public Integer parseNumber() {
        refreshBtn.shouldBe(Condition.visible,Duration.ofSeconds(10)).click();
        String [] value = numberTasks.shouldBe(Condition.visible, Duration.ofSeconds(10)).getText().split("\\D+");
        return Integer.parseInt(value[value.length-1]);
    }

    public void createNewTask() {
        btnCreateNewTask.shouldBe(Condition.visible,Duration.ofSeconds(10)).click();
        descriptionField.shouldBe(Condition.visible,Duration.ofSeconds(10)).sendKeys("Test" + Keys.RETURN);
        refreshBtn.shouldBe(Condition.visible,Duration.ofSeconds(10)).click();
    }
}
