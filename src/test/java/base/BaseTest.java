package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utils.ConfigReader;
import utils.Log;


import java.io.IOException;



public class BaseTest {
    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void initDriver() throws IOException {
        String browser = ConfigReader.getProperty("browser").trim();

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver.set(new ChromeDriver());
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver.set(new FirefoxDriver());
        } else {
            throw new RuntimeException("Browser not supported: " + browser);
        }

        getDriver().manage().window().maximize();
        Log.info("Driver initialized for browser: " + browser);
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
            Log.info("Driver closed");

        }
    }
}
