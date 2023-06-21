package resources;

import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {
    public AddPlace addPlacePayload(String name, String language, String address)
    {
        AddPlace add = new AddPlace();
        add.setAccuracy(50);
        add.setName(name);
        add.setPhone_number("(+91) 983 893 3937");
        add.setAddress(address);
        add.setWebsite("http://google.com");
        add.setLanguage(language);

        //Created the object for Location class to access the setMethods
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
        return add;
    }

    public String deletePlacePayload(String placeId){
       return "{\r\n    \"place_id\":\""+placeId+"\"\r\n}";

    }
}
