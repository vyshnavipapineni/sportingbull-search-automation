package stepDefinitions;

import base.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.SearchPage;
import utils.ConfigReader;

import java.io.IOException;

public class SearchSteps {
    WebDriver driver;
    SearchPage searchPage;
    @Given("I am on the SportingBull homepage")
    public void i_am_on_the_sporting_bull_homepage()  {
        driver = BaseTest.getDriver(); ;

        searchPage = new SearchPage(driver);

    }

    @When("I click the search button")
    public void i_click_the_search_button() {
        searchPage.clickSearchButton();
    }

    @When("I enter {string} in the search box")
    public void i_enter_in_the_search_box(String searchText) {
        searchPage.enterSearchText(searchText);
    }



    @When("I search for {string}")
    public void i_search_for(String query) {
        searchPage.enterSearchText(query);
    }

    @Then("I should see a message {string}")
    public void i_should_see_a_message(String expectedMessage) {
        String actualMessage = searchPage.getNoResultsMessageByText(expectedMessage);
        Assert.assertEquals(actualMessage, expectedMessage);
    }





}
