package JsonProcess;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class BodyJsonManager {
    private static final String BODIES_FILE_PATH = "src/test/resources/JsonBodies/bodiesJson.json";
    private final Map bodies;
    private JsonExtractor jsonExtractor;

    public BodyJsonManager() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            jsonExtractor = new JsonExtractor();
            String jsonContent = new String(Files.readAllBytes(Paths.get(BODIES_FILE_PATH)));
            bodies = mapper.readValue(jsonContent, Map.class);
        } catch (Exception e) {
            throw new RuntimeException("Error initializing BodyJsonManager: " + e.getMessage(), e);
        }
    }

    public String buildBodyWithData(String bodyName, Map<String, Object> placeholders) throws Exception {

        String JsonTemplate = jsonExtractor.getBodyJsonTemplate(bodyName);
        if (JsonTemplate==null || JsonTemplate.isEmpty()) {
            throw new IllegalArgumentException("Body not found: " + bodyName);
        }
        for (Map.Entry<String, Object> entry : placeholders.entrySet()) {
            JsonTemplate = JsonTemplate.replace("{{" + entry.getKey() + "}}", entry.getValue().toString());

        }
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode rootNode = objectMapper.readTree(JsonTemplate);
        if(bodyName.equals("bodyTransaction")){
            ((ObjectNode) rootNode).set("amount_in_cents", new IntNode(rootNode.path("amount_in_cents").asInt()));
        }

        return objectMapper.writeValueAsString(rootNode);

    }

    public String mergeJson(String templateJson, String paymentMethodJson) {
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode templateNode = null;
        JsonNode paymentMethodNode = null;
        try {
            templateNode = objectMapper.readTree(templateJson);
            paymentMethodNode = objectMapper.readTree(paymentMethodJson);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        ((ObjectNode) templateNode).set("payment_method", paymentMethodNode.path("payment_method"));

        try {
            return objectMapper.writeValueAsString(templateNode);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String buildPaymentMethodJson(String paymentMethod, Map<String, Object> data, String... keys) {
        Set<String> keysConserved = new HashSet<>(Arrays.asList(keys));
        Map<String, Object> newJsonDataPayment = new HashMap<>();
        for (String clave : keysConserved) {
            if (data.containsKey(clave)) {
                newJsonDataPayment.put(clave, data.get(clave));
            }
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ObjectNode rootNode = objectMapper.createObjectNode();
            JsonNode paymentDataNode = objectMapper.readTree((buildBodyWithData(
                    "bodyPaymentMethod." + paymentMethod, newJsonDataPayment)));
            rootNode.set("payment_method", paymentDataNode);
            System.out.println(rootNode);
            return objectMapper.writeValueAsString(rootNode);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
