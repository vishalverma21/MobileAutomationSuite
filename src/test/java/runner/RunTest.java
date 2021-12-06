package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

//@RunWith(Cucumber.class)

@CucumberOptions(plugin = {"pretty", "html:target/cucumber-reports/cucumber-pretty",
        "json:target/cucumber-reports/CucumberTestReport.json",
        "rerun:target/cucumber-reports/rerun.txt",
        "com.cucumber.listener.ExtentCucumberFormatter:target/extent.html"},
        glue = "",
        features = "src/test/resources/",
        dryRun = false,
        tags = {"@Regression4"},

        monochrome = true)
public class RunTest extends AbstractTestNGCucumberTests {

};
