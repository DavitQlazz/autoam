package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utils.BaseUtils;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class Element extends BaseUtils {
    private final WebElement el;

    public Element(final By by) {
        this.el = waitElement(by);
    }

    public static Element find(By by) {
        return new Element(by);
    }

    public void click() {
        el.click();
    }

    public Element scrollTo() {
        scrollTo(el);
        return this;
    }

    public void waitForText(String text) {
        waitText(el, text);
    }

    public String text() {
        return el.getText();
    }

    public void selectByText(String price) {
        new Select(el).selectByVisibleText(price);
    }

    public void containsPositiveInteger() {
        assertThat(Integer.parseInt(el.getText().split(" ")[1]))
                .isPositive();
    }
}
