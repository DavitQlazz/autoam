package ecase.steps;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.openqa.selenium.By;
import utils.JsonUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static java.util.Arrays.asList;
import static java.util.Collections.reverse;
import static org.openqa.selenium.interactions.WheelInput.ScrollOrigin;
import static utils.BaseUtils.*;

public class WhenSteps {

    private static final List<String> headerButtons = List.of("Save & Close", "Back", "Edit", "Actions");

    SelenideElement progressBar = $(".mat-progress-bar-primary.mat-progress-bar-fill.mat-progress-bar-element");

    @Given("The {string} page")
    public void navigate(String url) {
        open(url);
    }

    @And("I log in using {string} as the username and {string} as the password")
    public void iLoginAs(String username, String password) {
        $(By.id("login")).sendKeys(username);
        $(By.id("password")).sendKeys(password);
        $(By.id("submit")).click();
    }

    @And("I click on {string} button/element/entity")
    public void iClickOnButton(String button) {
        if (headerButtons.contains(button)) {
            unHideHeaderButtons();
            $(byText(button)).click();
        } else {
            $(byText(button))
                    .scrollIntoView("{block: \"center\"}")
                    .click();
        }

    }

    @And("I click on {string} button/text of the {string}( .*)")
    public void iClickOnButtonOfSection(String button, String section) {
        if (headerButtons.contains(button)) {
            unHideHeaderButtons();
            closestByText(section, button, false).click();
        } else {
            closestByText(section, button)
                    .shouldBe(visible, interactable)
                    .hover()
                    .click();
        }
    }

    private void unHideHeaderButtons() {
        Selenide
                .actions()
                .scrollFromOrigin(ScrollOrigin.fromViewport(500, 500), 0, -50)
                .perform();
    }

    @And("I click on {string} icon")
    public void iClickOnIcon(String arg0) {
        String selector = JsonUtils.getIcon(arg0, "elements");
        $(selector)
                .scrollIntoView("{block: \"center\"}")
                .click();
    }

    @And("I wait until the portfolio is opened")
    public void iWaitUntilThePortfolioIsOpened() {
        sleep(2000);
        System.out.println("debug");
    }


    @And("I fill {string} value in {string} field of the {string}")
    public void iFillNewCaseNumberValueInTextboxInCaseDe(String value, String child, String parent) {
        SelenideElement el = closestByText(parent, child);
        closestByElement(el, "input")
                .setValue(value)
                .pressEnter();
        progressBar.shouldBe(appear);
        progressBar.shouldBe(disappear);
    }

    @And("I click on the {string} item of the {string} column in the {string} table")
    public void iClickOnTheColumnElementByIndex(String itemIndex, String columnName, String tableIndex) {
        int iIndex = Integer.parseInt(itemIndex.replaceAll("[^0-9]", "")) - 1;
        int tIndex = Integer.parseInt(tableIndex.replaceAll("[^0-9]", "")) - 1;
        SelenideElement table = $$("mat-table").get(tIndex);

        ElementsCollection columns = table
                .findAll("mat-header-cell")
                .shouldBe(sizeGreaterThan(0));

        int columnIndex = IntStream
                .range(0, columns.size())
                .filter(i -> columns
                        .get(i)
                        .has(text(columnName)))
                .findFirst()
                .orElseThrow();

        SelenideElement cell = $$("mat-row")
                .shouldHave(sizeGreaterThan(0))
                .get(iIndex)
                .findAll("mat-cell")
                .get(columnIndex)
                .shouldNotBe(empty)
                .shouldBe(visible);
        sleep(2000);
        cell
                .hover()
                .click();
    }

    @And("I wait until the overview is opened")
    public void iWaitUntilTheOverviewIsOpened() {
    }

    @And("I wait until the {string} popup window is opened")
    public void iWaitUntilThePopupWindowIsOpenedInDe(String arg0) {
    }

    @And("I select the {string} value in the {string} select")
    public void iSelectTheValueInSearchableComboInDe(String value, String title) {
        SelenideElement input = closestBySelector(title, "input");
        input.click();
        Selenide.sleep(1000);
        input.sendKeys(value);
        $$(".mat-menu-item")
                .find(text(value))
                .shouldBe(interactable)
                .click();
    }

    @And("I select the following values for these fields:")
    public void iSelectBulk(DataTable table) {
        List<List<String>> rows = table.asLists();
        rows.forEach(row -> {
            select(row.get(0), row.get(1));
//            closestBySelector(row.get(0), "input").setValue(row.get(1));
//            $(byTagAndText("button /span", row.get(1)))
//                    .shouldBe(visible)
//                    .hover()
//                    .click(ClickOptions.withOffset(0, 0));
        });
    }

    @And("I pick the following date to these fields:")
    public void pickDate(DataTable table) {
        List<List<String>> rows = table.asLists();
        rows.forEach(row -> {
            String title = row.get(0);
            String[] split = row
                    .get(1)
                    .split(" ");
            closestBySelector(title, ".sis-datepicker__input")
                    .shouldBe(interactable)
                    .click();
            $(".mat-calendar-arrow")
                    .shouldBe(interactable)
                    .click();
            List<String> dates = asList(split);
            reverse(dates);
            dates.forEach(d -> {
                $(byTagAndText("div", d)).click();
            });
        });
    }

    @And("I wait until the {string} popup window is closed")
    public void iWaitUntilThePopupWindowIsClosedInDe(String arg0) {
    }

    @And("I wait until the edit mode is opened")
    public void iWaitUntilTheEditModeIsOpened() {
    }

    @And("I set the {string} date as {string}")
    public void iSetTheDateAsInDe(String arg0, String arg1) {
    }

    @And("I scroll to header and click on {string} button")
    public void iScrollToHeaderAndClickOnButtonInDeForm(String arg0) {
    }

    @And("I wait until the view mode is opened")
    public void iWaitUntilTheViewModeIsOpened() {
        Selenide.sleep(25000);
    }

    @And("I wait {int} seconds")
    public void iWaitSeconds(int sec) {
        Selenide.sleep(sec * 1000);
    }

    @And("The {string} date should be selected as {string}")
    public void theDateShouldBeSelectedAsInDe(String arg0, String arg1) {
    }

    @And("I fill the following fields to these values:")
    public void iFillAllFields(DataTable table) {
        List<List<String>> rows = table.asLists();
        rows.forEach(row -> {
            String type = "input";
            if (row.size() > 2) {
                if (row.get(2) != null) {
                    type = row.get(2);
                }
            }
            closestBySelector(row.get(0), type).setValue(row.get(1));
        });
    }

    @And("I select the {string} value in {string} multi select combo")
    public void iSelectTheValueInMultiSelectComboInDe(String arg0, String arg1) {
    }

    @And("I fill the {string} value in {string} textarea/textbox")
    public void iFillTheValueInTextareaInDe(String arg0, String arg1) {
    }

    @And("I upload the {string} file as a {string}")
    public void iUploadTheFileInInDeForm(String file, String title) {
        ClassLoader classloader = Thread
                .currentThread()
                .getContextClassLoader();
        String path = Objects
                .requireNonNull(classloader
                        .getResource("files/file." + file))
                .getPath();
        SelenideElement element = closestBySelector(title, "[type='file']").shouldBe(enabled);
        element.sendKeys(path);
    }

    @And("The attached file of {string} should contains the {string} file title")
    public void checkAttachedFilename(String section, String fileTitle) {
        closestBy(byText(section), By.cssSelector(".ejustice-single-document a"), true, false)
                .shouldHave(text(fileTitle));
    }

    @And("The following values should be selected in {string} multi select combo")
    public void theFollowingValuesShouldBeSelectedInMultiSelectComboInDe(String arg0) {
    }

    @And("The following value should be appear in {string} textarea")
    public void theFollowingValueShouldBeAppearInTextareaInDe(String arg0) {
    }

    @And("The table should be the following:")
    public void theTableShouldBeTheFollowingInCaseDe(DataTable table) {
        List<List<String>> rows = table.asLists();
        $("table").scrollIntoView(true);
        for (int row = 0; row < rows.size(); row++) {
            for (int column = 0; column < rows
                    .get(row)
                    .size(); column++) {
                $$("tbody tr")
                        .get(row)
                        .findAll("td")
                        .get(column)
                        .shouldHave(text(rows
                                .get(row)
                                .get(column)));
            }
        }
    }

    @And("The {string} value should be selected in {string} searchable combo")
    public void theValueShouldBeSelectedInSearchableComboInDe(String arg0, String arg1) {
    }

    @And("I wait until the {string} element is visible")
    public void iWaitUntilTheElementIsVisibleInDeForm(String arg0) {
    }

    @And("The {string} data should be {string} read only")
    public void theDataShouldBeReadOnlyInDeForm(String arg0, String arg1) {
    }

    @And("I wait until the {string} element is invisible")
    public void iWaitUntilTheElementIsInvisibleInDeForm(String arg0) {
    }

    @And("I wait until the action map is opened")
    public void iWaitUntilTheActionMapIsOpened() {
    }

}
