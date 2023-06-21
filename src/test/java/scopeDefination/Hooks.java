package scopeDefination;

//import org.junit.Before;
import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {

    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {

        //write a code that will give Place_id
        //Execute this code only when place_id is null

        scopedefinationPlaceValidation m = new scopedefinationPlaceValidation();
        if (scopedefinationPlaceValidation.place_id == null)
        {
            m.add_place_payload_with("Hook_Suhail", "Hook_English", "Hook_Tumkur");
            m.user_calls_with_http_request("AddPlaceAPI", "POST");
            m.verify_place_id_created_map_to_using("Hook_Suhail", "getPlaceAPI");
        }

    }

}
