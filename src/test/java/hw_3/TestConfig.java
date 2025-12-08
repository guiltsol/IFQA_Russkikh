package hw_3;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import hw_3.utils.CustomProperties;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.PageLoadStrategy;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class TestConfig {

   @BeforeAll
   public static void loadConfig() {
      CustomProperties.loadProperties();
   }

   @BeforeAll
   public static void initBrowser() {
      Configuration.pageLoadStrategy = PageLoadStrategy.EAGER.toString();
      Configuration.browser = CustomProperties.getProps().getProperty("browser");
      Configuration.timeout = Integer.parseInt(CustomProperties.getProps().getProperty("timeout"));

      Selenide.open(CustomProperties.getProps().getProperty("main.url"));
      getWebDriver().manage().window().maximize();
   }

   @AfterEach
   public void afterTest() {
      Selenide.closeWebDriver();
   }
}
