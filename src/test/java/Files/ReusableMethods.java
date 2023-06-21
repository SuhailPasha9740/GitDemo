package Files;

import io.restassured.path.json.JsonPath;

public class ReusableMethods {

    public static JsonPath rawToJson(String getResponse) {

        JsonPath js = new JsonPath(getResponse);
        return js;
    }
}
