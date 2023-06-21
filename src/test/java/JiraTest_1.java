import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import java.io.File;

import static io.restassured.RestAssured.*;

public class JiraTest_1 {

    public static void main(String[] args){

        RestAssured.baseURI = "http://localhost:8080";

        //SessionFilter class is used to remember the response from the below Authenticate code
        SessionFilter session = new SessionFilter();

        //Creating a Session to get the Session ID.
        //Login Scenario
       String response =  given()
                .header("Content-Type","application/json")
                .body("{ \"username\": \"suhailpasha.kiz\", \"password\": \"Suhail@8951872946\" }")
                .filter(session)
                .when().post("/rest/auth/1/session")
                .then().log().all().statusCode(200).extract().response().asString();

        //The below code is to Add the comment
String expectedMessage = "Hi How Are You !!!!@!!!";
       String addCommentResponse =  given()
                .pathParams("Id","SUH-1").header("Content-Type","application/json")
                .body("{\n" +
                "    \"body\": \""+expectedMessage+"\",\n" +
                "    \"visibility\": {\n" +
                "        \"type\": \"role\",\n" +
                "        \"value\": \"Administrators\"\n" +
                "    }\n" +
                "}")
             .filter(session)
                .when().post("/rest/api/2/issue/{Id}/comment")
                .then().log().all().assertThat().statusCode(201).extract().response().asString();

       // this is for Validation
        JsonPath js = new JsonPath(addCommentResponse);
       String commentId = js.getString("id");

       /*
        // Update Comment
        String response2 =   given().pathParams("key", "SUH-1")
                .pathParam("id", "10011")
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "    \"body\": \"This is my updated comment from Script.\",\n" +
                        "    \"visibility\": {\n" +
                        "        \"type\": \"role\",\n" +
                        "        \"value\": \"Administrators\"\n" +
                        "    }\n" +
                        "}")
                .filter(session)
                .when().put("/rest/api/2/issue/{key}/comment/{id}")
                .then().assertThat().statusCode(200).extract().response().asString();
*/

/*
        //Add Attachments

         given().header("X-Atlassian-Token","no-check").filter(session)
                 .header("Content-Type","multipart/form-data")
                 .pathParam("id","SUH-1")
                 //V.imp The below code is to add an attachment from our local server
                 .multiPart("file",new File("Sample.txt"))
                 .when().post("/rest/api/2/issue/{id}/attachments")

                 .then().log().all().assertThat().statusCode(200);
*/
         //Get Issue

      String issueDetailsResponse =  given().pathParam("id","SUH-1")
              .queryParam("fields","comment")
                .header("Content-Type", "application/json")
                .filter(session)
                .when().get("/rest/api/2/issue/{id}")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();
        System.out.println(issueDetailsResponse);

        JsonPath js1 = new JsonPath(issueDetailsResponse);
       int commentCount = js1.getInt("fields.comment.comments.size()");

       for (int i = 0; i<commentCount;i++)
       {
          String commentIdIssue =  js1.get("fields.comment.comments["+i+"].id").toString();
          if(commentIdIssue.equalsIgnoreCase(commentId)){
             String message =  js1.get("fields.comment.comments["+i+"].body").toString();
              System.out.println(message);
              Assert.assertEquals(message,expectedMessage);
          }
       }


    }
}
