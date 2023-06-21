package Files;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import org.testng.Assert;
import pojo.WebAutomation;
import pojo.getCourse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OAuth extends getCourse {

    public static void main(String args[]) throws InterruptedException {
        // The below code is commented bcoz Google is not allowing to automate the Gmail, So we have done the commented code Manually
        // and we have taken the 'code' & pasted in url variable

//        System.setProperty("webdriver.chrome.driver","C://ChromeDriver//chromedriver.exe");
//        WebDriver driver = new ChromeDriver();
//
//        driver.get("https://accounts.google.com/v3/signin/identifier?opparams=%253Fauth_url%253Dhttps%25253A%25252F%25252Faccounts.google.com%25252Fo%25252Foauth2%25252Fv2%25252Fauth&dsh=S-1593122918%3A1685632109968554&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&o2v=2&redirect_uri=https%3A%2F%2Frahulshettyacademy.com%2FgetCourse.php&response_type=code&scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&service=lso&flowName=GeneralOAuthFlow&continue=https%3A%2F%2Faccounts.google.com%2Fsignin%2Foauth%2Fconsent%3Fauthuser%3Dunknown%26part%3DAJi8hAMpOa0MueTzqlHcWkXQdDq8k6avsm4qAcDMKNn34rPD9P4aSusgB2Ks8hdK28d1jrBX7bcYKgnHWS7XbvrjgHjKD-VgXW9hHAQB4oArKbKnNEcVpjjUHGY_hVNQoM0aHjTjRNb8QMGIrYss671ZzYItIGgfePn0bHS7Q7k6mOBE-wyjVm8Z1n3wh_8LSqt_shpxQPKrm-MraRX2K4VSD5gY7oqz-AJcfZrgJreo4YMe3eBWcZtH5DXiRUtbCZ6ae-yA2wFdIfEzD-4Na0FX9QbzquQ3LdmK-oQb_BtH8nVXEMhRlIHo-PiXiLKHbzvgTRdHLy-or-sEIAamFC3MhDeWwxNnR2biwwwQ_ey1rqAXMkCkJf3r3r5MfcngkRSiBeFVU8WWf2jXSoMoGfGhtp2GwiRdFVgVoY4pBbgSvlt9k6_6ONYkaI4oBT6I-vF1ZibwS9UpskLKJ7lJOqKVPrBiRZ-DpA%26as%3DS-1593122918%253A1685632109968554%26client_id%3D692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com%23&app_domain=https%3A%2F%2Frahulshettyacademy.com&rart=ANgoxccCBFM3NnmfKiLhZSAcNEwN_7S6fbMumngHWMHvJxQ6QQlEVPsRxz3WzZLKzHs5h0d3tuTDquaD40PBiKD0sSh3gLzBSA");
//        driver.findElement(By.xpath("//input[@type='email']")).sendKeys("suhailpasha.lnr@gmail.com");
//        driver.findElement(By.xpath("//input[@type='email']")).sendKeys(Keys.ENTER);
//        Thread.sleep(3000);
//        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Suhail@8951872946");
//        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(Keys.ENTER);
//        Thread.sleep(4000);
       //String url = driver.getCurrentUrl();
//       String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AbUR2VM_rRObTS7DIxxOERauu4OQcAPfzV9R-fTlMy0hTUqxcNRDjxp5hhHHd3cz2Dot8A&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";
//       String partialURL = url.split("code=")[1];
//       String Code = partialURL.split("&scope")[0];
        String Code = "4%2F0AbUR2VNMZ5-sdBQQ2yDqmIq81e7hjvMTsbNAhl8fBgPDrzD8zV2q8uWM7jLm3VCiaFrOpw";
        System.out.println(Code);



//        given()
//                .queryParams("scope","https://www.googleapis.com/auth/userinfo.email")
//                .queryParams("auth_url","https://accounts.google.com/o/oauth2/v2/auth")
//                .queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
//                .queryParams("response_type","code")
//                .queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
//                .when().get("https://accounts.google.com/o/oauth2/v2/auth").asString();
////

        // urlEncodingEnabled(false) bcoz the above CODE is having % percentile it, by default rest assured performs Encoding
        // if encoding happens % is converted to some numeric number & our CODE will become change, so we have explicitly tell
        //Rest Assured not to perform Encoding
      String accessTokenResponse =  given().urlEncodingEnabled(false)
                .queryParams("code",Code)
                .queryParam("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
                .queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
                .queryParams("grant_type","authorization_code")
                        .when().log().all()
              .post("https://www.googleapis.com/oauth2/v4/token").asString();

      // For Last querry of this code
        //Expected Array for comparision
        String[] coursesTitle = {"Selenium Webdriver Java", "Cypress", "Protractor"};

      //to parse the Json & to get the Access token from the response
        JsonPath js = new JsonPath(accessTokenResponse);
        String accessToken = js.getString("access_token");

        getCourse gc=given().queryParam("access_token", accessToken).expect().defaultParser(Parser.JSON)
                .when()
                .get("https://rahulshettyacademy.com/getCourse.php").as(getCourse.class);

        System.out.println(gc.getLinkedIn());
        System.out.println(gc.getInstructor());
        //To print the Protracter cource Price
        System.out.println(gc.getCourses().getWebAutomation().get(2).getCourseTitle());
        List<WebAutomation> webAutomationCources =  gc.getCourses().getWebAutomation();
        for (int i=0; i<webAutomationCources.size(); i++){
           if( webAutomationCources.get(i).getCourseTitle().equalsIgnoreCase("Protractor")){

               System.out.println( webAutomationCources.get(i).getPrice());
           }
        }
        //To Print all the courseTitle in WebAutomation Course
        for(int i=0;i<webAutomationCources.size();i++)
        {
            System.out.println(webAutomationCources.get(i).getCourseTitle());
        }



        //To Print all the courseTitle whose Price is less than 50 or equal to 40
        for (int i=0; i<webAutomationCources.size();i++)
        {
           // int price = Integer.parseInt(webAutomationCources.get(i).getPrice());
           if (Integer.parseInt(webAutomationCources.get(i).getPrice()) < 50) {
               System.out.println(webAutomationCources.get(i).getCourseTitle());

           }
        }
        //Q. To check the Expected Course titles are equal to actual course Titles(Response) of Web Automation

        //Used Array List bcoz in feature we dont know how may courses are added so for dynamic purpose we used ArrayList
        //ArrayList provides the functionality of a dynamic array where the size is not fixed as an array
        ArrayList<String> a = new ArrayList <String>();

        for(int i= 0; i<webAutomationCources.size();i++){

            //here the course title will add i =n to the ArrayList fo every iteration
           a.add( webAutomationCources.get(i).getCourseTitle());
        }
        //our expected Coursetitles are in 'Arrays'(courseTitle) but our actual courseTitles are in ArrayList
        //It is easy to compare ArrayList to ArrayList OR Array to Array, So we will convert above Array(CurseTitle) to ArrayList
        List<String> expected = Arrays.asList(coursesTitle);
        //Checking Both the ArrayList weather it is equal or Not
        //a=> Actual result & expected=> is expected result.
        Assert.assertTrue(a.equals(expected));



    }
}