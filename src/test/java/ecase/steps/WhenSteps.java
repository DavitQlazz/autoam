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

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static java.util.Arrays.asList;
import static java.util.Collections.reverse;
import static org.apache.commons.lang3.StringUtils.stripStart;
import static org.openqa.selenium.interactions.WheelInput.ScrollOrigin;
import static utils.BaseUtils.*;

public class WhenSteps {

    private static final List<String> headerButtons = List.of("Save & Close", "Back", "Edit", "Actions");
    SelenideElement progressBar = $(".mat-progress-bar-primary.mat-progress-bar-fill.mat-progress-bar-element");
    SelenideElement popupContainer = $("mat-dialog-container");

    private static String getFilePath(String filename) {
        ClassLoader classloader = Thread
                .currentThread()
                .getContextClassLoader();
        return Objects
                .requireNonNull(classloader.getResource("files/" + filename))
                .getPath();
    }

    @Given("the {string} page")
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
                    .scrollIntoView(SCROLL_OPTION)
                    .click();
        }

    }

    @And("I click on {string} button/text of the {string}( table)")
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
        $x(selector)
                .scrollIntoView(SCROLL_OPTION)
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

    @And("I select the {string} value in the {string} select")
    public void iSelectTheValueInSearchableComboInDe(String value, String title) {
        SelenideElement input = closestByTitleOrSelector(title, "input").first();
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
            closestByTitleOrSelector(title, ".sis-datepicker__input")
                    .first()
                    .scrollIntoView(SCROLL_OPTION)
                    .shouldBe(interactable)
                    .click();
            $(".mat-calendar-arrow")
                    .shouldBe(interactable)
                    .click();
            List<String> dates = asList(split);
            reverse(dates);
            dates.forEach(d -> {
                $(byTagAndText("div", stripStart(d, "0"))).click();
            });
        });
    }

    @And("I wait {int} seconds")
    public void iWaitSeconds(int sec) {
        Selenide.sleep(sec * 1000L);
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
            closestByTitleOrSelector(row.get(0), type)
                    .first()
                    .setValue(row.get(1));
        });
    }

    @And("I fill the following fields of the {string} popup")
    public void iFillAllFieldsOfArea(String popupTitle, DataTable table) {
        popupContainer
                .find(byText(popupTitle))
                .shouldBe(visible);
        List<List<String>> rows = table.asLists();
        rows.forEach(row -> {
            String type = "input";
            if (row.size() > 2) {
                if (row.get(2) != null) {
                    type = row.get(2);
                }
            }
            closestByElement(popupContainer.find(byText(row.get(0))), type)
                    .setValue(row.get(1));
        });
    }

    @And("I upload to {string} the following {int} file(s):")
    public void fileUpload(String title, Integer size, DataTable table) {
        ElementsCollection elements = closestByTitleOrSelector(title, "[type='file']").shouldHave(size(size));
        List<String> files = table.asList();
        for (String file : files) {
            elements
                    .get(0)
                    .sendKeys(getFilePath(file));
        }
    }

    @And("I upload the following files accordingly:")
    public void fileUpload(DataTable table) {
        List<List<String>> rows = table.asLists();
        rows.forEach(row -> {
            closestByTitleOrSelector(row.get(0), "[type='file']")
                    .shouldHave(sizeGreaterThan(0))
                    .first()
                    .sendKeys(getFilePath(row.get(1)));
        });
    }

    @And("the attached file of {string} should contains the {string} file title")
    public void checkAttachedFilename(String section, String fileTitle) {
        closestBy(byText(section), By.cssSelector(".ejustice-single-document a"), true, false).shouldHave(text(fileTitle));
    }

    @And("the {string} table should be the following:")
    public void theTableShouldBeTheFollowingInCaseDe(String tableName, DataTable table) {
        List<List<String>> rows = table.asLists();
        $("table").scrollIntoView(true);
        for (int row = 0; row < rows.size(); row++) {
            for (int column = 0; column < rows
                    .get(row)
                    .size(); column++) {
                closestBy(byText(tableName), By.cssSelector("table"), true, false)
                        .findAll("tbody tr")
                        .get(row)
                        .findAll("td")
                        .get(column)
                        .shouldHave(text(rows
                                .get(row)
                                .get(column)));
            }
        }
    }

    @And("I check the {string} radio button of the {string} table")
    public void iCheckTheRadioButtonOfTheTable(String radio, String table) {
        closestByElement(closestByText(table, radio), "mat-radio-button div").click();
    }

    @And("I am waiting until the {string} pop-up window appears")
    public void iAmWaitingUntilThePopUpWindowAppears(String popupTitle) {
        popupContainer
                .$(byText(popupTitle))
                .shouldBe(visible);
    }
}
