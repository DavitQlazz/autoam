package utils;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class BaseUtils {

    public static final String SCROLL_OPTION = "{block: \"center\"}";

    public static By byTitle(String titleName) {
        return By.cssSelector("[title='" + titleName + "']");
    }

    public static void select(String title, String value) {
        closestByTitleOrSelector(title, "input.mat-input-element")
                .first()
                .scrollIntoView(SCROLL_OPTION)
                .shouldBe(interactable)
                .setValue(value);

        $(byTagAndText("button/span", value))
                .shouldBe(interactable)
                .click();
    }

    public static void check(String name) {
        $(byText(name))
                .ancestor("control")
                .find("mat-checkbox")
                .click();
    }

    public static SelenideElement closestByText(String firstText, String secondText) {
        return closestByText(firstText, secondText, true);
    }

    public static SelenideElement closestByText(String firstText, String secondText, boolean isVisible) {
        ElementsCollection originatingCase =
                $$(byText(firstText))
                        .shouldHave(sizeGreaterThan(0))
                        .filter(exist);
        if (isVisible) {
            originatingCase = originatingCase.filter(interactable);
        }
        ElementsCollection innerElement = null;
        for (SelenideElement element : originatingCase) {
            if (isVisible) {
                element.shouldBe(interactable);
            }
            for (int i = 0; i < 5 || element.has(tagName("body")); i++) {
                innerElement = element.$$(byText(secondText));
                if (innerElement.size() > 0) {
                    break;
                }
                element = element.parent();
                if (isVisible) {
                    element.shouldBe(visible);
                }
            }
        }
        assert innerElement != null;
        if (isVisible) {
            innerElement = innerElement.filter(visible);
        }
        return innerElement.first();
    }

    public static ElementsCollection closestByTitleOrSelector(String firstTextOrSelector, String secondSelector) {
        // TODO need optimization
        ElementsCollection originatingCase;
        if (isStartsWithLetter(firstTextOrSelector)) {
            ElementsCollection titles = $$(byTitle(firstTextOrSelector));
            if (!titles.isEmpty()) {
                originatingCase = titles;
            } else {
                originatingCase = $$(byText(firstTextOrSelector))
                        .shouldHave(sizeGreaterThan(0))
                        .filter(enabled);
            }
        } else {
            originatingCase = $$(firstTextOrSelector)
                    .shouldHave(sizeGreaterThan(0))
                    .filter(enabled);
        }

        ElementsCollection innerElement = null;
        for (SelenideElement element : originatingCase) {
            element.shouldBe(visible);
            for (int i = 0; i < 4; i++) {
                innerElement = element
                        .should(exist)
                        .$$(secondSelector)
                        .filter(Condition.empty); // TODO filter testing for input fields
                if (!innerElement.isEmpty() || element.has(tagName("body"))) {
                    return innerElement;
                }
                element = element.parent();
            }
        }
        assert innerElement != null;
        return innerElement;
    }

    public static SelenideElement closestBy(By firstSelector, By secondSelector, boolean isVisible, boolean isEmpty) {
        ElementsCollection titles = $$(firstSelector);
        titles.shouldHave(sizeGreaterThan(0));
        titles
                .first()
                .shouldBe(exist);
        if (isVisible) {
            titles
                    .first()
                    .shouldBe(visible);
        }
        ElementsCollection innerElement = null;
        for (SelenideElement element : titles) {
            for (int i = 0; i < 4; i++) {
                innerElement = element.$$(secondSelector);
                if (isEmpty) {
                    innerElement = innerElement.filter(Condition.empty);
                }
                if (!innerElement.isEmpty() || element.has(tagName("body"))) {
                    return innerElement.first();
                }
                element = element.parent();
            }
        }
        assert innerElement != null;
        throw new NoSuchElementException("No such element: " + firstSelector + " | " + secondSelector);
    }

    public static SelenideElement closestByElement(SelenideElement firstElement, String secondSelector) {
        ElementsCollection innerElement = null;
        firstElement.shouldBe(visible);
        for (int i = 0; i < 4; i++) {
            innerElement = firstElement.$$(secondSelector);
            if (innerElement.size() > 0 || firstElement.has(tagName("body"))) {
                break;
            }
            firstElement = firstElement.parent();
        }

        return innerElement.first();
    }

    private static boolean isStartsWithLetter(String text) {
        return text
                .substring(0, 1)
                .matches("^[a-zA-Z]");
    }
}
