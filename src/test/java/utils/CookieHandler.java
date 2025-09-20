package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CookieHandler {
    public static void handleCookies(WebDriver driver) {
        try {
            WebElement cookieSettingsButton = WaitUtils.getWait(driver).until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//a[contains(@class,'ccb__edit') and text()='Cookie Settings']")));
            cookieSettingsButton.click();

            WebElement saveSettingsButton = WaitUtils.getWait(driver).until(
                    ExpectedConditions.elementToBeClickable(
                            By.id("ccm__footer__consent-modal-submit")));
            saveSettingsButton.click();

            Log.info("Cookie settings handled successfully.");
        } catch (Exception e) {
            Log.info("Cookie banner not found or already dismissed.");
        }
    }
}
