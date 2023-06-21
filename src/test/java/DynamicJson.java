import Files.PayLoad;
import Files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.xml.crypto.Data;

import static org.hamcrest.Matchers.*;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class DynamicJson {

    //1. Dynamically build json payload with external data input
    @Test(dataProvider = "BookData")
    public void addBook(String isbn, String aisle)
    {
        RestAssured.baseURI = "http://216.10.245.166";
       String response = given()
               .header("Content-Type","application/json")
                .body(PayLoad.addBooks(isbn,aisle))
                .when()
               .post("/Library/Addbook.php")
                .then()
               .log().all().assertThat().statusCode(200)
                .header("Server","Apache")
               .body("Msg", equalTo("successfully added"))
                       .extract().asString();

       JsonPath js =  ReusableMethods.rawToJson(response);
      String ID =  js.get("ID");
        System.out.println(ID);

    }

    /*
    This Code is to delete the book for the Added Books, Facing some Problem  Need to write the code
    @Test(dataProvider = "BookData")
    public void deleteBook(String isbn, String aisle ){
        RestAssured.baseURI = "http://216.10.245.166";

      String deleteResponse = given().header("Content-Type","application/json")
                .body(PayLoad.deleteBooks(isbn,aisle))
                .when().post("/Library/DeleteBook.php")
                .then().log().all()
                .statusCode(200)
              //  .body("msg",equalTo("book is successfully deleted"))
                .header("Server","Apache").extract().asString();

        JsonPath js =  ReusableMethods.rawToJson(deleteResponse);
        String msg =  js.get("msg");
        System.out.println(msg);
    }
    */

    //2.Parameterize the API Test with multiple data sets
    @DataProvider(name = "BookData")
    public Object[][] getData(){
       return  new Object[][]  {{"ASDF12","MNZB"},{"QAZ124","QWSX"},{"PLMR","091ks"}};
    }
}
