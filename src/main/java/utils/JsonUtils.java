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

//    private static String parse(String locator, String fileName) {
//        String[] locators = locator.toLowerCase().split("/");
//        String json;
//        json = readJson(fileName);
//        StringBuilder concat = new StringBuilder("$");
//
//        for (String s : locators) {
//            concat.append("['").append(s).append("']");
//        }
//        concat.append("['locator']");
//        String read = JsonPath.parse(json).read(concat.toString());
//        System.out.println(read);
//        return read;
//    }

//    public static By getBy(String locator, String fileName) {
//        String parseElement = parse(locator, fileName);
//        String element = StringUtils.substringBetween(parseElement, "(\"", "\")");
//        String by = StringUtils.substringBetween(parseElement, "by.", "(");
//        switch (by) {
//            case "id":
//                return By.id(element);
//            case "xpath":
//                return By.xpath(element);
//            case "className":
//                return By.className(element);
//        }
//        return null;
//    }

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
