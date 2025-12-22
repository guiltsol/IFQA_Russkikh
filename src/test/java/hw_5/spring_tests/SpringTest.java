package hw_5.spring_tests;

import hw_5.steps.SpringStep;
import hw_5.utils.Config;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class SpringTest extends SpringWebHook {

    private final SpringStep steps = new SpringStep();
    private final String wrongName = Config.get("wrongName");
    private final String wrongPass = Config.get("wrongPass");
    private final String expectedFailLogin = Config.get("expectedFailLogin");
    private final String expectedFailPass = Config.get("expectedFailPass");
    private final String expectedLogoutFail = Config.get("expectedLogoutFail");
    private final String expectedLogoutSuccess = Config.get("expectedLogoutSuccess");

    @Test
    void fullAuthFlow() {
        steps.register();
        steps.loginFail(wrongName, expectedFailLogin);
        steps.passFail(wrongPass, expectedFailPass);
        UUID token = steps.loginSuccess();
        steps.logoutFail(expectedLogoutFail);
        steps.logout(token, expectedLogoutSuccess);
    }
}