package hw_5.spring_tests;

import hw_5.api.Specifications;
import hw_5.constants.EnvConstants;
import hw_5.steps.SpringStep;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class SpringTest {

    private final SpringStep steps = new SpringStep();
    private final String wrongName = "User";
    private final String wromgPass = "pass";
    private final String expectedFailLogin = "not found";
    private final String expectedFailPass = "not right pass";
    private final String expectedLogoutFail = "not found";
    private final String expectedLogoutSuccess = "success logout";

    @BeforeAll
    public static void setUp() {
        RestAssured.requestSpecification = Specifications.baseRequestSpec(EnvConstants.SPRING_URL);
    }

    @Test
    void fullAuthFlow() {

        steps.register();

        steps.loginFail(wrongName, expectedFailLogin);

        steps.passFail(wromgPass, expectedFailPass);

        UUID token = steps.loginSuccess();

        steps.logoutFail(expectedLogoutFail);

        steps.logout(token, expectedLogoutSuccess);
    }
}