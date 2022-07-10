package StepDefinitions;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;

import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/Features/",glue={"StepDefinitions"},
monochrome = true,
plugin = {"pretty",
        "html:target/HtmlReports/cucumber.html",
        "json:target/JSONReports/cucumber.json",
        "junit:target/JUnitReports/report.xml"}
//tags="@restfulapi,@swaglabslogin,@expediasearch"
		)
public class TestRunner {

}
