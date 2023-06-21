import io.restassured.path.json.JsonPath;
import jdk.jfr.ContentType;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

public class GraphQL {
    public static void main(String[] args) {

        //Query
       String response = given().log().all().header("Content-Type","application/json")
                .body("{\"query\":\"query($characterId:Int!, $locationId : Int!,$episodeId :Int!)\\n{\\n  character(characterId: $characterId) {\\n    name\\n    id\\n    type\\n    status\\n    species\\n    gender\\n    image\\n  }\\n  location(locationId: $locationId) {\\n    name\\n    type\\n  }\\n  episode(episodeId:$episodeId) {\\n    name\\n    air_date\\n    episode\\n  }\\n  characters(filters: {name: \\\"Tanishq\\\"}) {\\n    info {\\n      count\\n      pages\\n    }\\n    result {\\n      name\\n      status\\n      image\\n    }\\n  }\\n}\\n\",\"variables\":{\"characterId\":87,\"locationId\":106,\"episodeId\":66}}")
                .when().post("http://rahulshettyacademy.com/gq/graphql")
                .then().extract().response().asString();

        System.out.println(response);
        JsonPath js = new JsonPath(response);
       String expectedName = js.get("data.character.name");
        Assert.assertEquals(expectedName,"Dinga");

        //Mutation
        String response1 = given().log().all().header("Content-Type","application/json")
                .body("{\"query\":\"mutation($characterName:String!, $locationName : String!,$episodeName :String!)\\n{\\n  createLocation(location: {name: $locationName, type: \\\"Tings\\\", dimension: \\\"southZone\\\"}) {\\n    id\\n  }\\n  createCharacter(character:{name:$characterName,type:\\\"zest\\\",status:\\\"About to die\\\",species:\\\"Pernimal\\\",gender:\\\"Male\\\",image:\\\"jpeg\\\",originId:8,locationId:8})\\n{\\n  id\\n}\\n  createEpisode(episode:{name:$episodeName,air_date:\\\"14-12-2000\\\",episode:\\\"1234\\\"})\\n  {\\n    id\\n  }\\n  deleteEpisodes(episodeIds:72)\\n  {\\n    episodesDeleted\\n  }\\n}\\n\",\"variables\":{\"locationName\":\"Miami\",\"characterName\":\"Hexa\",\"episodeName\":\"Murugesh\"}}")
                        .when().post("http://rahulshettyacademy.com/gq/graphql")
                .then().extract().response().asString();
        System.out.println(response1);




    }

}
