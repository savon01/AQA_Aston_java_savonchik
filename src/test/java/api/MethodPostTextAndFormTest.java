package api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static java.util.Collections.emptyMap;
import static org.hamcrest.Matchers.*;


public class MethodPostTextAndFormTest {
    private static String URL = "https://postman-echo.com/";

    @Test
    public void testMethodPostText() {

        String requestBody = "{test: value}";

        Response response = given()
                .body(requestBody)
                .contentType(ContentType.TEXT)
                .when()
                .post(URL + "post")
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
                .body("headers.content-length", equalTo("13"))
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("headers.x-amzn-trace-id", notNullValue())
                .body("headers.content-type", equalTo("text/plain; charset=ISO-8859-1"))
                .body("headers.user-agent", equalTo("Apache-HttpClient/4.5.13 (Java/11.0.24)"))
                .body("headers.accept", equalTo("*/*"))
                .body("headers.accept-encoding", equalTo("gzip,deflate"))
                .body("json", nullValue())
                .body("url", equalTo("http://postman-echo.com/post"));
    }

    @Test
    public void testMethodPostForm() {

        String formData = "foo1=bar1&foo2=bar2";

        Response response = given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .body(formData)
                .when()
                .post(URL + "post")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().response();

        response.prettyPrint();

        response.then().body("args", equalTo(emptyMap()))
                .body("data", equalTo(""))
                .body("files", equalTo(emptyMap()))
                .body("form.foo1", equalTo("bar1"))
                .body("form.foo2", equalTo("bar2"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.x-forwarded-proto", equalTo("http"))
                .body("headers.x-request-start", notNullValue())
                .body("headers.connection", equalTo("close"))
                .body("headers.content-length", equalTo("19"))
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("headers.x-amzn-trace-id", notNullValue())
                .body("headers.user-agent", equalTo("Apache-HttpClient/4.5.13 (Java/11.0.24)"))
                .body("headers.accept", equalTo("*/*"))
                .body("headers.accept-encoding", equalTo("gzip,deflate"))
                .body("headers.content-type", equalTo("application/x-www-form-urlencoded; charset=UTF-8"))
                .body("json.foo1", equalTo("bar1"))
                .body("json.foo2", equalTo("bar2"))
                .body("url", equalTo("http://postman-echo.com/post"));
    }
}
