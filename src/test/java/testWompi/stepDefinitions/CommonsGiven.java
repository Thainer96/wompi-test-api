package testWompi.stepDefinitions;

import context.TestContext;
import io.cucumber.java.en.Given;
import utils.Environments;
import utils.Utils;

import java.util.Map;

public class CommonsGiven {

    private ServiceSteps serviceSteps;
    private CommonsThen commonsThen;
    private final TestContext testContext;

    public CommonsGiven(TestContext testContext) {
        this.testContext = testContext;
        this.serviceSteps = new ServiceSteps(testContext);
        this.commonsThen = new CommonsThen(testContext);
    }

    @Given("un usuario en ambiente de pruebas {string}")
    public void selectEnvironment(String environment){
        testContext.setEnvironment(environment);
        testContext.setUrlBase(Utils.getPropertyValue("test.properties", environment));
        Map<String, String> credentials = Environments.getCredentialsEnvironment(testContext.getEnvironment());
        testContext.setAuthorizationBearer("Bearer " +credentials.get("public_key"));

    }

    @Given("un usuario con tokens validos")
    public void executeServiceMerchants(){
        serviceSteps.buildService("MERCHANTS", "public_key");
        testContext.setResponseMerchants(commonsThen.executeGet(
                testContext.getFullUrlByServiceMerchants()+testContext.getPublicKey()));
    }


}
