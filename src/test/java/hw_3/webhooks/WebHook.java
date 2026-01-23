package hw_3.webhooks;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import hw_3.utils.CustomProperties;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.PageLoadStrategy;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class WebHook {

   @BeforeAll
   public static void loadConfig() {
      CustomProperties.loadProperties();
   }

   @BeforeAll
   public static void setupAllureReports() {
      SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
              .screenshots(Boolean.parseBoolean(CustomProperties.getProps().getProperty("allure.screenshots")))
              .savePageSource(Boolean.parseBoolean(CustomProperties.getProps().getProperty("allure.save.page.source")))
      );
   }

   @BeforeAll
   public static void initBrowser() {
      Configuration.pageLoadStrategy = PageLoadStrategy.EAGER.toString();
      Configuration.browser = CustomProperties.getProps().getProperty("browser");
      System.setProperty("webdriver.chrome.driver", CustomProperties.getProps().getProperty("path.to.web.driver"));
      Configuration.timeout = Integer.parseInt(CustomProperties.getProps().getProperty("timeout"));
      Selenide.open(CustomProperties.getProps().getProperty("main.url"));
      getWebDriver().manage().window().maximize();
   }

   @AfterEach
   public void afterTest() {
      Selenide.closeWebDriver();
   }
}
