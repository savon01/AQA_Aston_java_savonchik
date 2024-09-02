package api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static java.util.Collections.emptyMap;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

public class MethodGetTest {

    private static String URL = "https://postman-echo.com/";

    @Test
    public void testMethodGet() {
        Response response = given()
                .when()
                .contentType(ContentType.JSON)
                .get(URL + "get")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().response();

        response.prettyPrint();

        response.then().body("args", equalTo(emptyMap()))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.x-forwarded-proto", equalTo("http"))
                .body("headers.x-request-start", notNullValue())
                .body("headers.connection", equalTo("close"))
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("headers.x-amzn-trace-id", notNullValue())
                .body("headers.user-agent", equalTo("Apache-HttpClient/4.5.13 (Java/11.0.24)"))
                .body("headers.accept", equalTo("*/*"))
                .body("url", equalTo("http://postman-echo.com/get"));
    }

}
