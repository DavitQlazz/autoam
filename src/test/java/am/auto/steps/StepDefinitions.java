package am.auto.steps;

import elements.HomePageElements;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static elements.Element.find;
import static elements.Elements.findAll;
import static utils.BaseUtils.open;

public class StepDefinitions extends HomePageElements {
    @Given("the {string} page")
    public void thePage(String url) {
        open(url);
    }

    @And("a cancel the notification popup")
    public void aCancelTheNotificationPopup() {
        find(popupCancelButton).click();
    }

    @When("I select {string} as a car brand")
    public void iSelectAsACarBrand(String text) {
        find(makeFilter).selectByText(text);
    }

    @And("apply the filter")
    public void applyTheFilter() {
        find(searchButton).click();
    }

    @Then("I should see only the {string} brand cars on the appeared list")
    public void iShouldSeeThe(String text) {
        findAll(resultTitles).allContain(text);
    }

    @When("I set the upper bound of price to {string} dollars")
    public void iSetTheUpperBoundOfPriceToDollars(String price) {
        find(priceMaxFilter).selectByText(price);
    }
}
