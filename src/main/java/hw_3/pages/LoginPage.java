package hw_3.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import java.time.Duration;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {

    private final SelenideElement loginInput = $x("//input[@id='login-form-username']").as("Поле логина");
    private final SelenideElement passwordInput = $x("//input[@id='login-form-password']").as("Поле пароля");
    private final SelenideElement authorizationButton = $x("//input[@value='Войти']");

    public void authorizationInJira(String login, String password){
        loginInput.shouldBe(Condition.visible, Duration.ofSeconds(10)).sendKeys(login);
        passwordInput.shouldBe(Condition.visible, Duration.ofSeconds(10)).sendKeys(password);
        authorizationButton.click();
    }
}
