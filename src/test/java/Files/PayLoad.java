package Files;

public class PayLoad {

    public static String AddPlace(){

        String  AddAddress = "#81, Arquam Town, Ring Road Bangalore";
        return "{\n" +
                "  \"location\": {\n" +
                "    \"lat\": -38.383494,\n" +
                "    \"lng\": 33.427362\n" +
                "  },\n" +
                "  \"accuracy\": 50,\n" +
                "  \"name\": \"Suhail Pasha\",\n" +
                "  \"phone_number\": \"(+91) 9740596588\",\n" +
                "  \"address\": \"#81, Arquam Town, Ring Road Bangalore\",\n" +
                "  \"types\": [\n" +
                "    \"Dummy Dummy\",\n" +
                "    \"shop\"\n" +
                "  ],\n" +
                "  \"website\": \"http://dummy.com\",\n" +
                "  \"language\": \"French-IN\"\n" +
                "}\n";
    }

    public static String CoursePrice(){

        return "{\n" +
                "  \"dashboard\": {\n" +
                "    \"purchaseAmount\": 11520,\n" +
                "    \"website\": \"rahulshettyacademy.com\"\n" +
                "  },\n" +
                "  \"courses\": [\n" +
                "    {\n" +
                "      \"title\": \"Selenium Python\",\n" +
                "      \"price\": 50,\n" +
                "      \"copies\": 6\n" +
                "    },\n" +
                "    {\n" +
                "      \"title\": \"Cypress\",\n" +
                "      \"price\": 40,\n" +
                "      \"copies\": 4\n" +
                "    },\n" +
                "    {\n" +
                "      \"title\": \"RPA\",\n" +
                "      \"price\": 45,\n" +
                "      \"copies\": 10\n" +
                "    },\n" +
                "    {\n" +
                "      \"title\": \"Dinga\",\n" +
                "      \"price\": 105,\n" +
                "      \"copies\": 10\n" +
                "    },\n" +
                "    {\n" +
                "      \"title\": \"Dingii\",\n" +
                "      \"price\": 501,\n" +
                "      \"copies\": 10\n" +
                "    },\n" +
                "    {\n" +
                "      \"title\": \"Dangui\",\n" +
                "      \"price\": 455,\n" +
                "      \"copies\": 10\n" +
                "    }\n" +
                "    \n" +
                "  ]\n" +
                "}";
    }

    public static String addBooks(String isbn, String aisle){
        String payload = "{\n" +
                "\n" +
                "\"name\":\"Learn Appium Automation with Java\",\n" +
                "\"isbn\":\""+isbn+"\",\n" +
                "\"aisle\":\""+aisle+"\",\n" +
                "\"author\":\"Rahul \"\n" +
                "}\n" +
                " \n";
        return payload;
    }

    public static String deleteBooks(String isbn, String aisle){
        return "{\"isbn\":\""+isbn+"\",\n" +
                "\"aisle\":\""+aisle+"\"\n}";
    }
}
