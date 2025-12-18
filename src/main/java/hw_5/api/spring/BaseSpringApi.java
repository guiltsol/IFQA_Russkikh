package hw_5.api.spring;

import hw_5.api.Specifications;
import hw_5.constants.EnvConstants;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;

public class BaseSpringApi {

    public BaseSpringApi() {
        RestAssured.requestSpecification = Specifications.baseRequestSpec(EnvConstants.SPRING_URL);
        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .build();
    }
}
