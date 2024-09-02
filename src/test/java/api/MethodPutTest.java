package api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import okhttp3.Request;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static java.util.Collections.emptyMap;
import static org.hamcrest.Matchers.*;

public class MethodPutTest {
    private static String URL = "https://postman-echo.com/";

    @Test
    public void testMethodPut() {
        String requestBody = "This is expected to be sent back as part of response body.";

        Response response = given()
                .body(requestBody)
                .contentType(ContentType.TEXT)
                .when()
                .put(URL + "put")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().response();

        response.then().body("args", equalTo(emptyMap()))
                .body("data", equalTo(requestBody))
                .body("files", equalTo(emptyMap()))
                .body("form", equalTo(emptyMap()))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.x-forwarded-proto", equalTo("http"))
                .body("headers.x-request-start", notNullValue())
                .body("headers.connection", equalTo("close"))
                .body("headers.content-length", equalTo("58"))
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("headers.x-amzn-trace-id", notNullValue())
                .body("headers.content-type", equalTo("text/plain; charset=ISO-8859-1"))
                .body("headers.user-agent", equalTo("Apache-HttpClient/4.5.13 (Java/11.0.24)"))
                .body("headers.accept", equalTo("*/*"))
                .body("headers.accept-encoding", equalTo("gzip,deflate"))
                .body("json", nullValue())
                .body("url", equalTo("http://postman-echo.com/put"));
    }
}
