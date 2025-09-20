package hooks;

import base.BaseTest;
import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import utils.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Hooks extends BaseTest {


    @Before
    public void setUp(Scenario scenario) throws IOException {
        BaseTest.initDriver();
       WebDriver driver= getDriver();
        driver.get(ConfigReader.getProperty("url"));
        CookieHandler.handleCookies(driver);

        // Create ExtentTest for scenario
        ExtentTest test = ExtentManager.createTest(scenario.getName());
        test.info("Scenario started: " + scenario.getName());
    }

    @After
    public void tearDown(Scenario scenario) {
        WebDriver driver = getDriver();

        if (scenario.isFailed() && driver != null) {
            String path = ScreenshotUtils.takeScreenshot(driver, scenario.getName());
            try {
                ExtentManager.getTest().fail("Scenario Failed")
                        .addScreenCaptureFromPath(path);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            ExtentManager.getTest().pass("Scenario Passed");
        }

        // Quit driver
        BaseTest.quitDriver();

        // Remove test for thread safety
        ExtentManager.removeTest();

        // Flush report
        ExtentManager.flush();
    }
}
