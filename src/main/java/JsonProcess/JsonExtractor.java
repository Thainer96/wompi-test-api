package JsonProcess;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonExtractor {
    private final ObjectMapper objectMapper;

    public JsonExtractor() {
        this.objectMapper = new ObjectMapper();
    }

    public String getBodyJsonTemplate(String jsonPath) throws Exception {
        String jsonContent = new String(Files.readAllBytes(Paths.get(
                "src/test/resources/JsonBodies/bodiesJson.json")));
        return extractValue(jsonContent,jsonPath);
    }


    public String extractValue(String json, String jsonPath) throws Exception {
        JsonNode rootNode = objectMapper.readTree(json);
        String[] pathParts = jsonPath.split("\\.");

        JsonNode currentNode = rootNode;
        for (String part : pathParts) {
            currentNode = currentNode.path(part);
            if (currentNode.isMissingNode()) {
                throw new Exception("No se encontró el camino especificado: " + jsonPath);
            }
        }

        return currentNode.isContainerNode() ? currentNode.toString() : currentNode.asText();
    }

}
