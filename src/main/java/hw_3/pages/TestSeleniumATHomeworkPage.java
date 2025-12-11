package hw_3.pages;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class TestSeleniumATHomeworkPage {

    private final SelenideElement statusInfo = $x("//span[@id='status-val']/span").as("Информация о статусе задачи");
    private final SelenideElement versionInfo = $x("//span[@class='shorten']/a").as("Информация о версии задачи");

    public String parseStatus() {
        return statusInfo.shouldBe(visible, Duration.ofSeconds(10)).getText();
    }

    public String parseVersion() {
        return versionInfo.shouldBe(visible, Duration.ofSeconds(10)).getText();
    }
}
