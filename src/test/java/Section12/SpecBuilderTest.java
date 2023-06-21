package Section12;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SpecBuilderTest {

    public static void main(String[] args) {



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
        //the below myList is having shop & shop-keeper in its list at run time
        List<String> myList = new ArrayList<String>();
        myList.add("shop");
        myList.add("Shop-Keeper");
        add.setTypes(myList);

        //We have optimise the code & used the most repeted code in one variable & used for further use
        // We have used RequestSpecBuilder & ResponseSpecBuilder for optimize our code

        RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addQueryParam("key","qaclick123")
                .setContentType(ContentType.JSON).build();
        ResponseSpecification resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

        RequestSpecification res =  given().log().all().spec(req)
               //before in body we use to put payload here only but now java object(add) is created Json by using Serilization concept, POJO classes
                .body(add);

            Response response =   res.when().post("/maps/api/place/add/json")
                .then().log().all().spec(resspec).extract().response();
        String responseString = response.asString();

        System.out.println(responseString);

    }
}
