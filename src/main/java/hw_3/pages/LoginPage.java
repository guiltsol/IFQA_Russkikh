package hw_3.pages;

import com.codeborne.selenide.SelenideElement;
import hw_3.utils.CustomProperties;
import io.qameta.allure.Param;
import io.qameta.allure.Step;
import io.qameta.allure.model.Parameter;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.SetValueOptions.withText;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginPage {

    private final SelenideElement loginInput = $x("//input[@id='login-form-username']").as("Поле ввода логина");
    private final SelenideElement passwordInput = $x("//input[@id='login-form-password']").as("Поле ввода пароля");
    private final SelenideElement authorizationButton = $x("//input[@value='Войти']").as("Кнопка 'войти'");
    private final SelenideElement loginLabel = $x("//label[@id='usernamelabel']").as("Лейбл поле логина");
    private final SelenideElement passLabel = $x("//label[@id='passwordlabel']").as("Лейбл поле пароля");

    private final DashboardPage dashboardPage = new DashboardPage();

    private final String checkTest1 = CustomProperties.getProps().getProperty("check.test1");

    @Step("Авторизуемся в аккаунт под логином - {login} и паролем - *****")
    public void authorizationInJira(String login, @Param(mode = Parameter.Mode.MASKED) String password) {
            authorizationButton.shouldBe(visible, Duration.ofSeconds(10));
            loginLabel.shouldBe(visible, Duration.ofSeconds(10));
            passLabel.shouldBe(visible, Duration.ofSeconds(10));
            loginInput.shouldBe(visible, Duration.ofSeconds(10)).setValue(login);
        passwordInput.shouldBe(visible, Duration.ofSeconds(10)).
                setValue(withText(password).sensitive());
            authorizationButton.click();
    }

    @Step("проверяем, прошла ли успешно авторизация")
    public void checkAuthorization() {
        String titleDashboardPage = dashboardPage.getTitle();
        assertEquals(titleDashboardPage, checkTest1);
    }
}
