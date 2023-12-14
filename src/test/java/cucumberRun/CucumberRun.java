package cucumberRun;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/cucumberFeature/Homepage.feature",
        glue = {"WebPages.cucumberHomePage"}, // Adjust to your actual package structure
        plugin = {"pretty", "html:target/cucumber-reports"}
)
public class CucumberRun {

}
