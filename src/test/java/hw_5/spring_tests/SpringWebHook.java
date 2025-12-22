package hw_5.spring_tests;

import hw_5.api.Specifications;
import hw_5.constants.EnvConstants;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class SpringWebHook {

    @BeforeAll
    public static void setUp() {
        RestAssured.requestSpecification = Specifications.baseRequestSpec(EnvConstants.SPRING_URL);
    }
}
