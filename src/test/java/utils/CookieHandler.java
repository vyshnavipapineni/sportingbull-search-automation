package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CookieHandler {
    public static void handleCookies(WebDriver driver) {

            try {
                // Look for "Cookie Settings" first
                List<WebElement> cookieSettings = driver.findElements(
                        By.xpath("//a[contains(@class,'ccb__edit') and normalize-space(text())='Cookie Settings']"));

                if (!cookieSettings.isEmpty()) {
                    // Prefer Cookie Settings flow
                    WebElement cookieSettingsButton = WaitUtils.getWait(driver).until(
                            ExpectedConditions.elementToBeClickable(cookieSettings.get(0)));
                    cookieSettingsButton.click();
                    Log.info("Clicked 'Cookie Settings'.");

                    // Then wait for Save Settings button and click it
                    WebElement saveSettingsButton = WaitUtils.getWait(driver).until(
                            ExpectedConditions.elementToBeClickable(
                                    By.id("ccm__footer__consent-modal-submit")));
                    saveSettingsButton.click();
                    Log.info("Clicked 'Save settings'.");

                }
                else {
                    // Otherwise, check for Accept All button
                    List<WebElement> acceptAll = driver.findElements(By.cssSelector("button.consent-give"));
                    if (!acceptAll.isEmpty()) {
                        WebElement acceptBtn = WaitUtils.getWait(driver).until(
                                ExpectedConditions.elementToBeClickable(acceptAll.get(0)));
                        acceptBtn.click();
                        Log.info("Clicked 'Accept all cookies'.");
                    }

                    else {
                        Log.info("No cookie popup found — proceeding.");
                    }

                }
            }

            catch (Exception e) {
                Log.info("Error while handling cookies — proceeding: " + e.getMessage());
            }

        }
    }



