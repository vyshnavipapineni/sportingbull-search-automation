package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Log;
import utils.WaitUtils;

import java.util.List;
import java.util.stream.Collectors;

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

    @FindBy(xpath = "//a[contains(@class,'sports-search-competitions__item')]")
    private List<WebElement> resultItems;


    public void clickSearchButton() {

        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }

    public void enterSearchText(String text) {
        WebElement input = wait.until(ExpectedConditions.visibilityOf(searchInput));
        input.clear();
        input.sendKeys(text);
        input.sendKeys(Keys.ENTER);

    }
    public List<String> getResultTitles() {
        wait.until(ExpectedConditions.visibilityOfAllElements(resultItems));
        return resultItems.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public String getNoResultsMessageByText(String expectedText) {
        try {
            Log.info("Attempting to locate no-results message with text: " + expectedText);
            // Wait for the element with the expected text to become visible.
            WebElement messageElement = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//*[contains(text(), '" + expectedText + "')]")
                    )
            );

            System.out.println("The message element text is: " + messageElement.getText());

            String actualText = messageElement.getText().trim();
            Log.info("No-results message found and visible: " + actualText);
            return actualText;

        } catch (TimeoutException e) {
            // This error indicates the element never appeared within the timeout.
            Log.error("Expected no-results message not found: " + expectedText);
            return null;
        } catch (Exception e) {
            // Catch any other unexpected errors.
            Log.error("An unexpected error occurred while searching for the message: " + e.getMessage());
            return null;
        }
    }


}
