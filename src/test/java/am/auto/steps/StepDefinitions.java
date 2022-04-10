package am.auto.steps;

import elements.HomePageElements;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;

import java.util.List;

import static elements.Element.find;
import static elements.Elements.findAll;
import static hooks.SetUp.SOFT;
import static java.util.stream.Collectors.toList;
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

    @Then("I should see only the {string} brand cars on the search result")
    public void iShouldSeeThe(String text) {
        findAll(resultTitles)
                .all()
                .forEach(el -> SOFT.get().assertThat(el.getText()).contains(text));
        SOFT.get().assertAll();
    }

    @When("I set the upper bound of price to {int} dollar")
    public void iSetTheUpperBoundOfPriceToDollars(Integer price) {
        find(priceMaxFilter).selectByText("$" + price);
    }

    @Then("the count of items should appear on the blue button")
    public void theCountOfItemsAppearedOnBlueButton() {
        find(applyButton).containsPositiveInteger();
    }

    @When("I navigate to the search result last page")
    public void iOpenTheLastPage() {
        String text = find(lastPage).text();
        find(lastPage)
                .scrollTo()
                .click();
        find(activePage).waitForText(text);
    }

    @Then("I should see that the number of elements corresponds to the value of the blue button")
    public void iAssumedThatTheNumberOfElementsCorrespondsToTheValueOfTheBlueButton() {
        int itemCount = Integer.parseInt(find(applyButton).text().split(" ")[1]);
        int lastPageItemCount = findAll(priceTag).count();
        int pageCount = Integer.parseInt(find(activePage).text());
        SOFT.get().assertThat(50 * (pageCount - 1) + lastPageItemCount).isEqualTo(itemCount);
        SOFT.get().assertAll();
    }

    @Then("price of all the items in the search results should be less than {int} dollar")
    public void priceOfAllTheProductsInTheSearchResultsShouldBeLessThan(Integer price) {
        List<Integer> tags = findAll(priceTag)
                .all()
                .stream()
                .map(WebElement::getText)
                .takeWhile(el -> el.contains("$"))
                .mapToInt(s -> Integer.parseInt(s.replaceAll("[\\D]", "")))
                .boxed()
                .collect(toList());
        tags.forEach(i -> SOFT.get().assertThat(i).isLessThanOrEqualTo(price));
        SOFT.get().assertAll();
    }
}