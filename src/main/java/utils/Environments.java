package utils;

import java.util.HashMap;
import java.util.Map;

public class Environments {

    private static final Map<String, Map<String,String>> environmentApiCredentials = new HashMap<>();

    static {
        Map<String, String> CREDENTIALS_UAT = Map.of(
                "public_key", Utils.getPropertyValue("config.properties", "public_key_uat"),
                "private_key", Utils.getPropertyValue("config.properties", "private_key_uat"),
                "event", Utils.getPropertyValue("config.properties", "event_uat"),
                "integrity", Utils.getPropertyValue("config.properties", "integrity_uat")
        );


        environmentApiCredentials.put("UAT",CREDENTIALS_UAT);
    }

    public static Map<String, String>  getCredentialsEnvironment(String environment){
        return  environmentApiCredentials.get(environment);
    }
}
