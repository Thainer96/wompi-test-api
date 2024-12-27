package context;

import io.restassured.response.Response;

public class TestContext {

    private String environment;
    private String urlBase;
    private String fullUrlByServiceMerchants;
    private String publicKey;
    private Response responseMerchants;

    private String AuthorizationBearer;

    private String bodyTransaction;

    public String getBodyTransaction() {
        return bodyTransaction;
    }

    public void setBodyTransaction(String bodyTransaction) {
        this.bodyTransaction = bodyTransaction;
    }

    public String getAuthorizationBearer() {
        return AuthorizationBearer;
    }

    public void setAuthorizationBearer(String authorizationBearer) {
        AuthorizationBearer = authorizationBearer;
    }


    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getUrlBase() {
        return urlBase;
    }

    public void setUrlBase(String urlBase) {
        this.urlBase = urlBase;
    }

    public String getFullUrlByServiceMerchants() {
        return fullUrlByServiceMerchants;
    }

    public void setFullUrlByServiceMerchants(String fullUrlByServiceMerchants) {
        this.fullUrlByServiceMerchants = fullUrlByServiceMerchants;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public Response getResponseMerchants() {
        return responseMerchants;
    }

    public void setResponseMerchants(Response responseMerchants) {
        this.responseMerchants = responseMerchants;
    }
}
