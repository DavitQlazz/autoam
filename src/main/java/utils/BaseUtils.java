package utils;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class BaseUtils {

    public static void select(String title, String value) {
        $("[title='" + title + "']")
                .ancestor("control")
                .find(".mat-empty.mat-form-field-empty")
                .ancestor("div")
                .find("input")
                .setValue(value);
        $$(".sis-select__item.test-selenium__item")
                .filter(text(value))
                .first()
                .click();
    }

    public static void fill(String title, String value) {
        $("[title='" + title + "']")
                .ancestor("control")
                .find("input")
                .setValue(value);
    }

    public static void check(String name) {
        $(byText(name))
                .ancestor("control")
                .find("mat-checkbox")
                .click();
    }

    public static SelenideElement closest(String firstText, String secondText) {
        ElementsCollection originatingCase =
                $$(byText(firstText))
                        .shouldHave(sizeGreaterThan(0))
                        .filter(visible);
        ElementsCollection innerElement = null;
        for (SelenideElement element : originatingCase) {
            element.shouldBe(interactable);
            for (int i = 0; i < 5; i++) {
                innerElement = element.$$(byText(secondText));
                if (innerElement.size() > 0 || element.has(tagName("body"))) {
                    break;
                }
                element = element.parent();
                element.shouldBe(visible);
            }
        }
        assert innerElement != null;
        return innerElement.first();
    }

    public static SelenideElement closestBySelector(String first, String selector) {
        ElementsCollection originatingCase =
                $$(byText(first))
                        .shouldHave(sizeGreaterThan(0))
                        .filter(enabled);
        ElementsCollection innerElement = null;
        for (SelenideElement element : originatingCase) {
            element.shouldBe(visible);
            for (int i = 0; i < 4; i++) {
                innerElement = element.$$(selector);
                if (!innerElement.isEmpty() || element.has(tagName("body"))) {
                    return innerElement.first();
                }
                element = element.parent();
            }
        }
        assert innerElement != null;
        return innerElement.first();
    }

    public static SelenideElement closest(SelenideElement first, String secondSelector) {
        ElementsCollection innerElement = null;
        first.shouldBe(visible);
        for (int i = 0; i < 4; i++) {
            innerElement = first.$$(secondSelector);
            if (innerElement.size() > 0 || first.has(tagName("body"))) {
                break;
            }
            first = first.parent();
        }

        return innerElement.first();
    }
}
