
import java.io.File;
import Files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.RestAssured.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import Files.PayLoad;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

public class Basics {

    public static void main(String[] args){

        RestAssured.baseURI = "https://rahulshettyacademy.com";

      String Addresposne =   given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
                .body(PayLoad.AddPlace())
                .when().log().all().post("/maps/api/place/add/json")
                .then().assertThat().statusCode(200).header("Server","Apache/2.4.41 (Ubuntu)")
                .body("scope", equalTo("APP")).extract().response().asString();

        System.out.println(Addresposne);

        JsonPath js = new JsonPath(Addresposne);
        String PlaceID = js.getString("place_id");
        System.out.println(PlaceID);


        String newAddress = "2nd coss Arquam Town, Tumkur, 572105";
        given().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body("{\n" +
                        "\"place_id\":\""+ PlaceID +"\",\n" +
                        "\"address\":\""+ newAddress +"\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}\n")

                .when().put("maps/api/place/update/json")
                .then().log().all().assertThat().statusCode(200)
                .body("msg", equalTo("Address successfully updated"))
                .header("Server","Apache/2.4.41 (Ubuntu)");

        //Get Place
        String GetURL = "https://rahulshettyacademy.com/maps/api/place/get/json";
         String getResponse = given().queryParam("key","qaclick123")
                .queryParam("place_id",PlaceID)
                .when().get(GetURL)
                .then().log().all().assertThat().statusCode(200).extract().response().asString();

       JsonPath js1 =  ReusableMethods.rawToJson(getResponse);
        String ActualAddress = js1.getString("address");
        PayLoad pll = new PayLoad();
        Assert.assertEquals(ActualAddress,newAddress);

    }


}
