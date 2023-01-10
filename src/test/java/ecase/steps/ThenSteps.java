package ecase.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static utils.BaseUtils.byTitle;
import static utils.BaseUtils.closestBy;

public class ThenSteps {


    @Then("the following values should be displayed accordingly:")
    public void insertedValuesAssertion(DataTable table) {
        List<List<String>> rows = table.asLists();
        rows.forEach(row -> {
            // Find label or span
            closestBy(byTitle(row.get(0)), By.xpath(".//label | .//span"), true, false)
                    .shouldBe(exist)
                    .shouldHave(text(row.get(1)));
        });
    }

    @Then("the {string} text/message should be displayed")
    public void textIsDisplayedAssertion(String text) {
        $(byText(text)).shouldBe(visible);
    }

}
















