Feature: Validating Place API

# Previous it was only Scenario, but now its 'Scenario Outline' bcoz we have to pass data dynamically [Data driven mechanism] &
# multiple data sets [Parameterization](in Examples: )
# to send (code becomes more Generic)
@AddPlace @Regression
  Scenario Outline: Verify if the place is being successfully added using AddPlaceAPI
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When user calls "AddPlaceAPI" with "POST" http request
    Then the API call got success and status code is 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_id created map to "<name>" using "getPlaceAPI"

    Examples:
    |name   | |language     |  |address    |
    |Suhail | | English-EN  |  |Test Road  |
   # |Pasha | | Hindi  |  |Kamish Road  |
   # |Sameer | | Hindi  |  |Nagpur Road  |
   # |Zalke | | Hindi  |  |Maharastra Road  |

@DeletePlace @Regression
  Scenario: Verify if the place is deleted successfully
# This test is only suits when the above TC is executed & it will give place_id & to that place we are trying to delete the Place
  #but what if we want to run only this TC or the above will not run then we should go to 'Hooks' Concept
  # In hooks class we have to write the code in such a way that place_id should generate & we have did that... refer Hooks.java
     Given Delete place payload
        When user calls "DeletePlaceAPI" with "POST" http request
       Then the API call got success and status code is 200
     And "status" in response body is "OK"

