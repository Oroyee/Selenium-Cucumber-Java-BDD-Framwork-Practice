package StepDefinitions;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;

import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/Features",glue={"StepDefinitions"},
monochrome = true,
plugin = {"pretty",
        "json:target/HtmlReports/cucumber.html",
        "html:target/JSONReports/cucumber.json",
        "junit:target/JUnitReports/report.xml"},
tags="@smoketest"
		)
public class TestRunner {

}
