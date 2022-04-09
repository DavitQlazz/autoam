package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utils.BaseUtils;


public class Element extends BaseUtils {
    private final WebElement el;

    public Element(final By by) {
        this.el = waitingElement(by);
    }

    public static Element find(By by) {
        return new Element(by);
    }

    public void click() {
        el.click();
    }

    public void type(String str) {
        el.sendKeys(str);
    }

    public Element scrollTo() {
        scrollTo(el);
        return this;
    }

    public String text() {
        return el.getText();
    }

    public void selectByText(String price) {
        new Select(el).selectByVisibleText(price);
    }

}
