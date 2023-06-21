package cucumber.Options;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features",
        //the below tags is tagging concept(To run particular test)
        //tags = "@DeletePlace",
        plugin = "json:target/jsonReports/cucumber-reports.json",
        glue = {"scopeDefination"}

        )
public class TestRunner {
}
