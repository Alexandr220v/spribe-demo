package utils;

import io.restassured.specification.RequestSpecification;
import java.util.Optional;

import static io.restassured.RestAssured.given;

public class RequestManager {
    private static final ThreadLocal<RequestSpecification> requestSpecification = new ThreadLocal<>();

    private RequestManager() {
    }

    public static void setBaseRequestSpec(String url) {
        RequestSpecification spec = given()
                .baseUri(url);
        requestSpecification.set(spec);
    }

    public static RequestSpecification getRequestSpecification(String url) {
        Optional.ofNullable(requestSpecification.get()).orElseGet(() -> {setBaseRequestSpec(url);
                    return requestSpecification.get();
                });
        return requestSpecification.get();
    }

    public static void removeRequestSpec() {
        requestSpecification.remove();
    }
}
