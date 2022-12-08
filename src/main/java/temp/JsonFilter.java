package temp;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.function.Function;

public class JsonFilter {

  private static final Function<String, File> basePath =
      (file) -> new File("src/test/resources/files/" + file);

  public static String readFile() throws IOException {
    return FileUtils.readFileToString(basePath.apply("log.json"), StandardCharsets.UTF_8);
  }

  public static void writeFile(byte[] bytes) throws IOException {
    FileUtils.writeByteArrayToFile(basePath.apply("new.json"), bytes);
  }

  public static byte[] parseToJson(String json) throws IOException {
    json = json.replaceAll("\n", "");
    String jsonElement = new Gson().toJson(json);
//    JsonArray array = jsonElement.get("item").getAsJsonArray();
//    JsonArray arrayCleaned = jsonElement.getAsJsonObject().get("item").getAsJsonArray();

//    array.forEach(e -> {
//      if (e.getAsJsonObject().get("name").getAsString().endsWith(".js")) {
//        arrayCleaned.remove(e);
//      }
//    });

    //    List<JsonNode> actualList = Lists.newArrayList(entries);
    //    actualList.forEach(
    //        e -> {
    //          ((ObjectNode) e).remove("_initiator");
    ////          ((ObjectNode) e.get("_initiator")).remove("type");
    //        });

    //    entries.forEachRemaining(e -> {
    //      ((ObjectNode) e).remove("_initiator");
    //    });

//    Configuration configuration =
//        Configuration.builder().jsonProvider(new GsonJsonProvider()).build();
//    JsonPath jsonPath = JsonPath.compile("[?(@._resourceType == 'xhr')]");
//    JsonArray jsonPathArray = jsonPath.read(new Gson().toJson(json), configuration);
    //    String s = jsonPathArray.toString().replaceAll("\n" + "                ", "");
    return "".getBytes(StandardCharsets.UTF_8);
  }

  public static void main(String[] args) throws IOException {
    writeFile(parseToJson(readFile()));
  }
}
