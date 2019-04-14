import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

    @RunWith(Cucumber.class)
    @CucumberOptions(
            features = {"src/test/java/Features"},
            plugin = {"pretty", "html:target/cucumber"
                    , "json:target/cucumber.json"},
            tags = {"@login"},
            glue = {"stepDefs"},
            monochrome = true
    )
    public class RunCukesTest {

    }

