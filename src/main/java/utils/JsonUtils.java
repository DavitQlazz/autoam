package utils;

import com.jayway.jsonpath.JsonPath;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonUtils {
    public static String getIcon(String locator, String fileName) {
        JsonNode parseElement = readJson(fileName).get("icons");
        return parseElement.get(locator.toLowerCase()).textValue();
    }

    private static JsonNode readJson(String name) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json;
        try {
            String map = Files.readString(Path.of("src/main/java/mappings/" + name + ".json"), Charset.defaultCharset());
            json = mapper.readTree(map);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return json;
    }
}
