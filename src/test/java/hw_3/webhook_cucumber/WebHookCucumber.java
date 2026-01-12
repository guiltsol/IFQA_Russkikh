package hw_3.webhook_cucumber;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import hw_3.utils.CustomProperties;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.PageLoadStrategy;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class WebHookCucumber {

    @Before
    public void initBrowser() {
        CustomProperties.loadProperties();
        Configuration.pageLoadStrategy = PageLoadStrategy.EAGER.toString();
        Configuration.browser = CustomProperties.getProps().getProperty("browser");
        System.setProperty("webdriver.chrome.driver", CustomProperties.getProps().getProperty("pathToWebDriver"));
        Configuration.timeout = Integer.parseInt(CustomProperties.getProps().getProperty("timeout"));
        Selenide.open(CustomProperties.getProps().getProperty("main.url"));
        getWebDriver().manage().window().maximize();
    }

    @After
    public void afterTest() {
        Selenide.closeWebDriver();
    }
}
