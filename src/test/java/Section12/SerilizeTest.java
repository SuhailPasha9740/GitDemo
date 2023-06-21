package Section12;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;

public class SerilizeTest {

    public static void main(String[] args) {

        RestAssured.baseURI = "https://rahulshettyacademy.com";

        AddPlace add = new AddPlace();
        add.setAccuracy(50);
        add.setName("Frontline house");
        add.setPhone_number("(+91) 983 893 3937");
        add.setAddress("29, side layout, cohen 09");
        add.setWebsite("http://google.com");
        add.setLanguage("French-IN");

        //Created the object for Location calss to access the setMethods
        Location l = new Location();
        l.setLat(-38.383494);
        l.setLng(33.427362);
        add.setLocation(l);

        //Created the list to add the data, bcoz TYPE is having data in array
        //the below myList is having shop & shop-keeper in its list in run time
        List<String> myList = new ArrayList<String>();
        myList.add("shop");
        myList.add("Shop-Keeper");
        add.setTypes(myList);

       Response res =  given().log().all()
                .queryParam("key","qaclick123")
               //before in body we use to put payload here only but now java object(add) is created Json by using Serilization concept, POJO classes
                .body(add)
                .when().post("/maps/api/place/add/json")
                .then().assertThat().statusCode(200).extract().response();
        String responseString = res.asString();

        System.out.println(responseString);

    }
}
