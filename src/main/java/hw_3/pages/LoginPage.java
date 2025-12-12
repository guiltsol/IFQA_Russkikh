package hw_3.pages;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {

    private final SelenideElement loginInput = $x("//input[@id='login-form-username']").as("Поле ввода логина");
    private final SelenideElement passwordInput = $x("//input[@id='login-form-password']").as("Поле ввода пароля");
    private final SelenideElement authorizationButton = $x("//input[@value='Войти']").as("Кнопка 'войти'");
    private final SelenideElement loginLabel = $x("//label[@id='usernamelabel']").as("Лейбл поле логина");
    private final SelenideElement passLabel = $x("//label[@id='passwordlabel']").as("Лейбл поле пароля");

    public void authorizationInJira(String login, String password) {
        authorizationButton.shouldBe(visible, Duration.ofSeconds(10));
        loginLabel.shouldBe(visible, Duration.ofSeconds(10));
        passLabel.shouldBe(visible, Duration.ofSeconds(10));
        loginInput.shouldBe(visible, Duration.ofSeconds(10)).setValue(login);
        passwordInput.shouldBe(visible, Duration.ofSeconds(10)).setValue(password);
        authorizationButton.click();
    }
}
