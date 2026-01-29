package hw_5.utils;

import io.qameta.allure.Allure;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class MaskingAllureFilter implements Filter {

    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec,
                           FilterContext ctx) {

        String body = requestSpec.getBody() != null ? requestSpec.getBody().toString() : "";
        String maskedBody = body.replaceAll(
                "\"password\"\\s*:\\s*\".*?\"",
                "\"password\":\"******\""
        );
        Allure.addAttachment("Request", "application/json", maskedBody);
        Response response = ctx.next(requestSpec, responseSpec);
        StringBuilder responseAttachment = new StringBuilder();
        responseAttachment.append("Status code: ").append(response.getStatusCode()).append("\n");
        response.getHeaders().forEach(header ->
                responseAttachment.append(header.getName())
                        .append(": ")
                        .append(header.getValue())
                        .append("\n")
        );
        responseAttachment.append("Body:\n")
                .append(response.getBody() != null ? response.getBody().asPrettyString() : "");
        Allure.addAttachment("Response", "text/plain", responseAttachment.toString());
        return response;
    }
}
