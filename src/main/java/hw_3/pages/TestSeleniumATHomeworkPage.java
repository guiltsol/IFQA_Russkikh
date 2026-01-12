package hw_3.pages;

import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.ru.И;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static org.junit.Assert.assertEquals;

public class TestSeleniumATHomeworkPage {

    private final SelenideElement statusInfo = $x("//span[@id='status-val']/span").as("Информация о статусе задачи");
    private final SelenideElement versionInfo = $x("//span[@class='shorten']/a").as("Информация о версии задачи");

    public String parseStatus() {
        return statusInfo.shouldBe(visible, Duration.ofSeconds(10)).getText();
    }

    public String parseVersion() {
        return versionInfo.shouldBe(visible, Duration.ofSeconds(10)).getText();
    }

    @И("^проверям, что статус у задачи - '(.*)' и исправить в версиях - '(.*)'$")
    public void checkStatusAndVersion(String status, String version) {
        assertEquals(status, parseStatus());
        assertEquals(version, parseVersion());
    }
}
