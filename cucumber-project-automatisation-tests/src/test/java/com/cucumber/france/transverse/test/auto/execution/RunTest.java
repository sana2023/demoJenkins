package com.cucumber.france.transverse.test.auto.execution;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

@CucumberOptions(tags = {"@tnr"}, features = {"src//test//resources//features"}

        , glue = {"com.cucumber.france.transverse.test.auto.step_definitions", "com.cucumber.france.transverse.test.auto.utility"}
        , monochrome = true
        , plugin = {"pretty", "html:target/test-report/html",
        "json:target/test-report/cucumber.json",
        "junit:target/cucumber.xml",
        "rerun:target/rerun.txt"}
)

@Test
public class RunTest extends AbstractTestNGCucumberTests {
    @AfterClass
    public static void genererRapportDeTest() {
    }
}