package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		glue = "stepDefintions",
		features = "src/test/resources/Features",
		dryRun = false,
		tags = "@SearchData",
		monochrome = true,
		plugin = {"pretty","html:target/test-report.html"}
		)
public class RunnerClass {

}
