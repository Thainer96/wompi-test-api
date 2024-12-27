package testWompi.stepDefinitions;

import context.TestContext;
import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import utils.Utils;

import static io.restassured.RestAssured.given;

public class CommonsThen {

    private final TestContext testContext;

    public CommonsThen(TestContext testContext) {
        this.testContext = testContext;
    }

    @Given("permite la ejecucion del servicio {string} de manera correcta")
    public void executeService(String typeService){
        executeGet(testContext.getFullUrlByServiceMerchants()+testContext.getPublicKey());
    }

    @Given("permite la ejecucion del servicio TRANSACTIONS de manera correcta")
    public void executeServiceTransactions(){
        String urlComplement = Utils.getPropertyValue("test.properties", "TRANSACTIONS");
        Response response = executePost(testContext.getUrlBase()+urlComplement, testContext.getAuthorizationBearer(),
                testContext.getBodyTransaction());
        System.out.println(response.getBody().asString());
    }

    public Response executePost(String url, String authorization, String body){
        RestAssured.baseURI = url;
        Response response = given()
                .header("Authorization", authorization)
                .contentType(ContentType.JSON)
                .body(body)
                .post()
                .then()
                .extract()
                .response();
        validation201Http(response, url);
        return response;
    }

    public Response executeGet(String url){
        Response response = RestAssured.get(url);
        validation200Http(response, url);
        return response;
    }

    public void validation201Http(Response response, String url){
        if(response.statusCode()!=HttpStatus.SC_CREATED){
            throw new RuntimeException("error executing the next Service, status code"
                    +  response.statusCode() + url +
                    response.body().asString());
        }
    }

    public void validation200Http(Response response, String url){
        if(response.statusCode()!=HttpStatus.SC_OK){
            System.out.println("error executing the next Service, status code" +  response.statusCode() + url);
        }
    }
}
