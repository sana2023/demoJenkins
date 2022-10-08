package com.cucumber.france.transverse.test.auto.step_definitions;


import com.cucumber.france.transverse.test.auto.utility.ScenarioContext;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import org.openqa.selenium.WebDriver;

public class DashboardSteps {

    ScenarioContext scenarioContext;


    public DashboardSteps(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;


    }

    @Given("je vais sur l'application Udemy")
    public void jeVaisSurLApplicationUdemy() throws InterruptedException {

    }

    @And("je vois le logo bien affiché sur le site")
    public void jeVoisLeLogoBienAffichéSurLeSite() throws InterruptedException {

    }

}

