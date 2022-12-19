package ecase.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static java.time.Duration.ofMillis;
import static utils.BaseUtils.closestBy;

public class ThenSteps {


    @Then("The following values should be displayed accordingly:")
    public void insertedValuesAssertion(DataTable table) {
        List<List<String>> rows = table.asLists();
        rows.forEach(row -> {
            closestBy(By.cssSelector("[title='" + row.get(0) + "']"), By.cssSelector("label"), true, false)
                    .shouldHave(text(row.get(1)), ofMillis(3000));
        });
    }

    @Then("The {string} text should be displayed")
    public void textIsDisplayedAssertion(String text) {
        $(byText(text)).shouldBe(visible);
    }

}
















