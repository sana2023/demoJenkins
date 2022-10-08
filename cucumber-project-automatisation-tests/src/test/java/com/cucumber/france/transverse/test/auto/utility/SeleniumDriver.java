package com.cucumber.france.transverse.test.auto.utility;
import com.cucumber.project.transverse.test.auto.utility.ConfigFileReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.URL;

import static org.testng.Assert.fail;

public class SeleniumDriver {
    private final ConfigFileReader configFileReader = ConfigFileReader.get();
    private String mode;
    private WebDriver driver;

    private void initBrowserChrome() throws IOException {
        mode = configFileReader.getPropertyValue("webdriver.mode");

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setBrowserName(configFileReader.getPropertyValue("browsername"));
        ChromeOptions chromeOpt = new ChromeOptions();
        chromeOpt.addArguments("--start-maximized");
        chromeOpt.addArguments("--enable-automation");
        chromeOpt.addArguments("--no-sandbox");
        chromeOpt.addArguments("--disable-infobars");
        chromeOpt.addArguments("--disable-browser-side-navigation");
        chromeOpt.addArguments("--disable-gpu");
        chromeOpt.addArguments("--disable-dev-shm-usage");

        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOpt);
        switch (mode) {
            case "local":

                System.setProperty(configFileReader.getPropertyValue("webdriver.chrome.clef"), configFileReader.getPropertyValue("webdriver.chrome.path"));
                driver = new ChromeDriver(chromeOpt);
                driver.manage().window().maximize();
              // Dimension dimension = new Dimension(1950, 1000);
              // driver.manage().window().setSize(dimension);
                break;
            case "remote":

                driver = new RemoteWebDriver(new URL(configFileReader.getPropertyValue("webdriver.grid.url")), capabilities);
                ((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
                break;
            default:
                fail(String.format("webdriver: mode connexion inconnue [%s]", mode));
        }
    }


    private void initBrowserIE() throws Exception {
        mode = configFileReader.getPropertyValue("webdriver.mode");
        DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
        capabilities.setBrowserName(configFileReader.getPropertyValue("browsername"));
        capabilities.setVersion("11");
        capabilities.setJavascriptEnabled(true);
        capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
        capabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
        capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
        capabilities.setCapability(InternetExplorerDriver.LOG_LEVEL, configFileReader.getPropertyValue("webdriver.log.level"));
        switch (mode) {
            case "local":
                System.setProperty(configFileReader.getPropertyValue("webdriver.ie.clef"), configFileReader.getPropertyValue("webdriver.ie.path"));
                driver = new InternetExplorerDriver();
                driver.manage().window().maximize();
                break;
            case "remote":
                driver = new RemoteWebDriver(new URL(configFileReader.getPropertyValue("webdriver.grid.url")), capabilities);
                ((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
                break;
            default:
                fail(String.format("webdriver: mode connexion inconnue [%s]", mode));
        }
    }

    private void initBrowserEdge() throws Exception {
        mode = configFileReader.getPropertyValue("webdriver.mode");
        DesiredCapabilities capabilities = DesiredCapabilities.edge();
        capabilities.setBrowserName(configFileReader.getPropertyValue("browsername"));
        switch (mode) {
            case "local":
                System.setProperty(configFileReader.getPropertyValue("webdriver.edge.clef"), configFileReader.getPropertyValue("webdriver.edge.path"));
                driver = new EdgeDriver();
                driver.manage().window().maximize();
                break;
            case "remote":
                driver = new RemoteWebDriver(new URL(configFileReader.getPropertyValue("webdriver.grid.url")), capabilities);
                ((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
                break;
            default:
                fail(String.format("webdriver: mode connexion inconnue [%s]", mode));
        }
    }

    public SeleniumDriver() throws Exception {
        super();
        mode = configFileReader.getPropertyValue("webdriver.mode");
        String[] browser = configFileReader.getPropertyValue("browsername").split(",");
        for (String b : browser) {
            switch (b) {
                case "ie":
                    initBrowserIE();
                    break;
                case "edge":
                    initBrowserEdge();
                    break;
                default:
                    initBrowserChrome();
                    break;
            }

        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}
