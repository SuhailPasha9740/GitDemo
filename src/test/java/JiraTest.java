import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

public class JiraTest {

    public static void main(String args[]){

        RestAssured.baseURI = "http://localhost:8080";

        given().log().all()
                .header("Content-Type","application/json").pathParam("key", "10007")
                .body("{\n" +
                        "    \"body\": \"This Is my First comment through REST Script\",\n" +
                        "    \"visibility\": {\n" +
                        "        \"type\": \"role\",\n" +
                        "        \"value\": \"Administrators\"\n" +
                        "    }\n" +
                        "}").post("/rest/api/2/issue/{key}/comment");
    }
}
