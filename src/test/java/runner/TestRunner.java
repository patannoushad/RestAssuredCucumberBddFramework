package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

import static io.cucumber.testng.CucumberOptions.SnippetType.CAMELCASE;

@CucumberOptions(
plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
        ,features = {"src/test/resources"}
        ,glue = {"stepdefinations"}
        ,snippets = CAMELCASE
        ,monochrome=true
        ,tags = "@api"
)
public class TestRunner extends AbstractTestNGCucumberTests {

}
