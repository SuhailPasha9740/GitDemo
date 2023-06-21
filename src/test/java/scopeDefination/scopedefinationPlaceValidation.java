package scopeDefination;


import io.cucumber.java.en.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class scopedefinationPlaceValidation extends Utils {

    //Initialized Globally bcoz to access the variable all over the class
    RequestSpecification res;
    ResponseSpecification resspec;
    Response response;
   static String place_id;

    TestDataBuild data = new TestDataBuild();

    @Given("Add Place Payload with {string} {string} {string}")
    public void add_place_payload_with(String name, String language, String address) throws IOException {
        // Write code here that turns the phrase above into concrete actions

       // TestDataBuild data = new TestDataBuild();

        //FW ==> Previous it was req & now it is requestSpecification (extended Utils class)
        res =  given().spec(requestSpecification())
                //before in body we use to put payload here only but now java object(add) is created Json by using Serilization concept, POJO classes
                //FW => previous it was add & now it is data.addPlacePayload
                .body(data.addPlacePayload(name,language,address));
    }

    @When("user calls {string} with {string} http request")
    public void user_calls_with_http_request(String resource, String httpMethod) {

        //Constructor will be called with valueOf resource which we pass
       APIResources resourceAPI = APIResources.valueOf(resource);
        System.out.println( resourceAPI.getResource());

        if (httpMethod.equalsIgnoreCase("POST")) {
            response = res.when().post(resourceAPI.getResource());
        } else if (httpMethod.equalsIgnoreCase("GET")) {
            response =  res.when().get(resourceAPI.getResource());
        }


       // resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

        //removed the belo code bcoz we are doing this validation in @Then
//                .then().spec(resspec).extract().response();
    }
    @Then("the API call got success and status code is {int}")
    public void the_api_call_got_success_and_status_code_is(Integer int1) {
        // Write code here that turns the phrase above into concrete actions

        assertEquals(response.getStatusCode(),200);

    }
    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyValue,String ExpectedValue) {
        // Write code here that turns the phrase above into concrete actions
//       String resp = response.asString();
//       JsonPath js = new JsonPath(resp);

//Previously the above code was used & now we have optimized more by adding that code in to the utils file (getJsonPath())
      assertEquals(getJsonPath(response,keyValue) , ExpectedValue);
    }

    @Then("verify place_id created map to {string} using {string}")
    public void verify_place_id_created_map_to_using(String expectedName, String resource) throws IOException {

        //This is to get the place_id from the response & created the new reusable method(getJsonPath) in utils class
        place_id = getJsonPath(response,"place_id");
        //This is new requirement that 'get Place'
        //for every new request we need basic requirement that is given when then
        //given is 'res' in res we have already written the code in @Given so we have reused the 'res' variable after that
        // .queryParam is respected to this TC
        res =  given().spec(requestSpecification()).queryParam("place_id",place_id);
        user_calls_with_http_request(resource,"GET");
        String actualName = getJsonPath(response,"name");
        assertEquals(actualName,expectedName);



    }

    @Given("Delete place payload")
    public void deletePlacePayload() throws IOException {

        res =  given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
    }
}
