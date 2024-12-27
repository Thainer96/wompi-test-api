package utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Properties;

public class Utils {

    public static String getPropertyValue(String filePath, String key) {
        Properties properties = new Properties();
        try (InputStream in = Utils.class.getClassLoader().getResourceAsStream((filePath))) {
            if (in == null) {
                throw new RuntimeException("file not found: " + filePath);
            }
            properties.load(in);
        } catch (IOException e) {
            throw new RuntimeException("Error loading properties file: " + filePath, e);
        }

        return properties.getProperty(key);
    }

    public static String generateSignature(Map<String, Object> data, String environment) throws NoSuchAlgorithmException {
        Map<String, String> credentials = Environments.getCredentialsEnvironment(environment);
        String reference = getStringOrDefault(data, "reference");
        String amountInCents = getStringOrDefault(data, "amount_in_cents");
        String currency = getStringOrDefault(data, "currency");
        String integrity = credentials.getOrDefault("integrity", "");
        String input = reference + amountInCents + currency + integrity;
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(hashBytes);
    }

    private static String getStringOrDefault(Map<String, Object> data, String key) {
        Object value = data.getOrDefault(key, "");
        return value != null ? value.toString() : "";
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }
}
