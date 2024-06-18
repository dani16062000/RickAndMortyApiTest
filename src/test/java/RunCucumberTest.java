import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = { "pretty","json:target/surefire-reports/cucumber.json" ,
                "html:target/html-report.html"},
        features = {"src/test/resources/"}
)
        public class RunCucumberTest {
}
