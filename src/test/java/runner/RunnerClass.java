package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		glue = "stepDefintions",
		features = "src/test/resources/Features",
		dryRun = false,
		//tags = "@TimestoreLogin",
		monochrome = true,
		plugin = {"pretty","html:target/test-reports1.html"}
		)
public class RunnerClass {

}
