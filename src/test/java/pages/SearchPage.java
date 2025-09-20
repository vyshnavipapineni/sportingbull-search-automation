package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WaitUtils;

import java.util.List;

public class SearchPage {

    WebDriver driver;
    WebDriverWait wait;

    public SearchPage(WebDriver driver){
        this.driver=driver;
        this.wait= WaitUtils.getWait(driver);
        PageFactory.initElements(driver,this);

    }



    @FindBy(xpath = "//div[contains(@class,'search-button')]")
    private WebElement searchButton;

    @FindBy(id = "search-input")
    private WebElement searchInput;

    @FindBy(xpath = "//div[contains(@class,'search-results-count')]")
    private WebElement resultsCount;


    @FindBy(css = "div.search-no-results")
    private List<WebElement> noResultsMessages;


    public void clickSearchButton() {

        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }

    public void enterSearchText(String text) {
        WebElement input = wait.until(ExpectedConditions.visibilityOf(searchInput));
        input.clear();
        input.sendKeys(text);
        input.sendKeys(Keys.ENTER);

    }



    public String getNoResultsMessageByText(String expectedText) {
        try {
            // wait until at least one no-results element is visible
            List<WebElement> elements = wait.until(
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(
                            By.cssSelector("div.search-no-results")
                    )
            );

            for (WebElement el : elements) {
                if (el.isDisplayed()) {
                    String text = el.getText().trim();
                    System.out.println("Candidate message: " + text);
                    if (text.contains(expectedText)) {
                        return text;
                    }
                }
            }

            System.out.println("No element contained expected text: " + expectedText);
            return null;

        } catch (TimeoutException e) {
            System.out.println("No results message did not appear in time.");
            return null;
        }
    }



}
