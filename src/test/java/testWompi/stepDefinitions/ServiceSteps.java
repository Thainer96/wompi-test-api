package testWompi.stepDefinitions;

import JsonProcess.BodyJsonManager;
import JsonProcess.JsonExtractor;
import context.TestContext;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import utils.Environments;
import utils.Utils;

import java.util.*;

public class ServiceSteps {

    private final TestContext testContext;
    private final BodyJsonManager bodyJsonManager;


    public ServiceSteps(TestContext testContext) {
        this.testContext = testContext;
        this.bodyJsonManager = new BodyJsonManager();
    }

    @Given("se encuentre en la url del servicio {string} con su llave publica {string}")
    public void buildService(String service, String publicKey){
        String url = Utils.getPropertyValue("test.properties", service);
        testContext.setFullUrlByServiceMerchants(testContext.getUrlBase() + url);
        Map<String, String> credentials = Environments.getCredentialsEnvironment(testContext.getEnvironment());
        testContext.setPublicKey(credentials.get(publicKey));
    }

    @When("se encuentre en la url del servicio {string} con el body {string}")
    public void buildBodyTransaction(String api, String bodyName, DataTable featureData) throws Exception {
        Map<String, Object> data = new HashMap<>();
        featureData.asMaps(String.class, Object.class).forEach(row -> row.forEach(data::put));
        JsonExtractor extractor = new JsonExtractor();
        String paymentMethodJson = getPaymentMethodByDataFeature(data);
        data.put("acceptance_token", extractor.extractValue(testContext.getResponseMerchants().getBody().asString(),
                "data.presigned_acceptance.acceptance_token"));
        data.put("accept_personal_auth",extractor.extractValue(testContext.getResponseMerchants().getBody().asString(),
                "data.presigned_personal_data_auth.acceptance_token"));
        data.put("signature", Utils.generateSignature(data, testContext.getEnvironment()));
        testContext.setBodyTransaction(bodyJsonManager.mergeJson(bodyJsonManager.buildBodyWithData(bodyName, data), paymentMethodJson));

    }

    public String getPaymentMethodByDataFeature(Map<String, Object> data)  {
        String paymentMethod = "";
        if (data.containsKey("type")) {
            paymentMethod = (String) data.get("type");
        }
        return deleteKeysSpecificPaymentMethod(paymentMethod, data);
    }

    public String deleteKeysSpecificPaymentMethod(String paymentMethod, Map<String, Object> data ){
        String body = "";
        switch (paymentMethod) {
            case "NEQUI":
                body = bodyJsonManager.buildPaymentMethodJson(paymentMethod, data, "type", "phone_number");
                break;
            case "BANCOLOMBIA_QR":
                body = bodyJsonManager.buildPaymentMethodJson(paymentMethod, data, "type", "payment_description", "sandbox_status");
                break;
            case "PCOL":
                body = bodyJsonManager.buildPaymentMethodJson(paymentMethod, data, "type", "sandbox_status");
                break;
            case "BANCOLOMBIA_TRANSFER":
                body = bodyJsonManager.buildPaymentMethodJson(paymentMethod, data, "type", "payment_description", "user_type");
                break;
        }
        return body;
    }



}
