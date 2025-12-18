package hw_5.api.rick_and_morty;

import hw_5.api.Specifications;
import hw_5.constants.EnvConstants;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;

public class BaseRickAndMortyApi {

    public BaseRickAndMortyApi() {
        RestAssured.requestSpecification = Specifications.baseRequestSpec(EnvConstants.RICKANDMORTY_URL);
        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .build();
    }
}
