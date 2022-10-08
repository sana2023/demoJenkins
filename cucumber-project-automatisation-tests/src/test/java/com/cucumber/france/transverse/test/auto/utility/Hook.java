package com.cucumber.france.transverse.test.auto.utility;

import com.cucumber.project.transverse.test.auto.utility.ConfigFileReader;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


import java.io.IOException;

public class Hook {


    public static WebDriver driver;

    private SeleniumDriver seleniumDriver = new SeleniumDriver();

    private final ConfigFileReader configFileReader = ConfigFileReader.get();
    private final String webSite = configFileReader.getPropertyValue("applicationUrl");
    private final Logger LOG = LogManager.getLogger(Hook.class);

    public Hook() throws Exception {
    }

    @Before
    public void setUp(cucumber.api.Scenario scenario) {
      //  configFileReader.addConfig("config/test.properties");
        Log.startScenario(scenario.getName());
        CucumberParams.scenario = scenario;
        driver = seleniumDriver.getDriver();
        driver.get("https://www.udemy.com/");
        scenario.embed(driver.getCurrentUrl().getBytes(), "text/plain");
        driver.manage().window().maximize();
    }

    @After
    public void tearDown(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            scenario.embed(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png");
            scenario.embed(driver.getCurrentUrl().getBytes(), "text/plain");
        }
       Log.endScenario(scenario.getName());
        driver.close();
        driver.quit();
        driver = null;
    }

    public static WebDriver getDriver() {
        return driver;
    }
}


