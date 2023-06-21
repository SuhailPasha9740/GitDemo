package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {

        //The below instance variable is set to static bcoz it should use as a single instance for all our TC
    // if we dont specify static then for every data set our if condition will become null only, then there is no use of putting that if cond'n
         public static RequestSpecification req;
        public RequestSpecification requestSpecification() throws IOException {

            //This condition is bcoz, if we provide multiple data sets in .fraturefile under Examples: section
            //in logging.txt file for every data sets it is replacing with latest data so we provided
            //if req is null then go inside & create logging.txt file & print the log after logs is printed we are avoiding to overrite tha log for next time
            if(req == null)
            {
                //Logging the logs in to a File
                //added '.addFilter(RequestLoggingFilter.logRequestTo())' method
                PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
                 req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
                        .addQueryParam("key","qaclick123")
                        .addFilter(RequestLoggingFilter.logRequestTo(log))
                        .addFilter(ResponseLoggingFilter.logResponseTo(log))
                        .setContentType(ContentType.JSON).build();
                return req;
            }
            //This is bcoz for second data sets simply return the log without creating or overwriting the logging file
            return req;
        }

   // The below method is to get the baseURL from .properties file, as it os same to all our Test so declared globally & used to all out Tests
        public static String getGlobalValue(String key) throws IOException {
            Properties prop = new Properties();
            FileInputStream fis = new FileInputStream("C:/Users/USER/Downloads/RestAssuredTestProject4.3/APIFramework/src/test/java/resources/global.properties");
             prop.load(fis);
            return prop.getProperty(key);
        }

        public String getJsonPath(Response response, String keyValue){
          String resp =  response.asString();
          JsonPath js = new JsonPath(resp);
         return js.get(keyValue).toString();
        }
}
