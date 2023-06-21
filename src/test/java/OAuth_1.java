import io.restassured.path.json.JsonPath;
import org.apache.http.io.SessionOutputBuffer;

import static io.restassured.RestAssured.given;

public class OAuth_1 {
    public static void main(String args[]){

        String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AbUR2VNg2tvLMtggGyMvtnOobzfpkUHp3bdu5JU8rwXFQjR-XJZIZieaQlUIOBlFHdC_Mg&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";
        String partialURL = url.split("code=")[1];
        String Code = partialURL.split("&scope")[0];
        System.out.println(Code);

        String accessTokenResponse =  given().urlEncodingEnabled(false)
                .queryParams("code",Code)
                .queryParam("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
                .queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
                .queryParams("grant_type","authorization_code")
                .when().log().all()
                .post("https://www.googleapis.com/oauth2/v4/token").asString();
        System.out.println(accessTokenResponse);
        //to parse the Json & to get the Access token from the response
        JsonPath js = new JsonPath(accessTokenResponse);
        String accessToken = js.getString("access_token");

        String response = given()
                .queryParam("access_token",accessToken)
                .when().log().all()
                .get("https://rahulshettyacademy.com/getCourse.php").asString();

        System.out.println(response);

    }
}

 /* The below is the response for my referense to convert Json in website
        URL :
        https://jsoneditoronline.org/#left=local.zimalo&right=local.rumaju
        * { "instructor": "RahulShetty", "url": "rahulshettycademy.com", "services": "projectSupport", "expertise": "Automation",
"courses": { "webAutomation": [ { "courseTitle": "Selenium Webdriver Java", "price": "50" }, { "courseTitle": "Cypress",
"price": "40"
},
{
"courseTitle": "Protractor",
"price": "40"
}
],
"api": [
{
"courseTitle": "Rest Assured Automation using Java",
"price": "50"
},
{
"courseTitle": "SoapUI Webservices testing",
"price": "40"
}
],
"mobile": [
{
"courseTitle": "Appium-Mobile Automation using Java",
"price": "50"
}
]
},
"linkedIn": "https://www.linkedin.com/in/rahul-shetty-trainer/"
}
        * */