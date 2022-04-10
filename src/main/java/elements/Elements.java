package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.BaseUtils;

import java.util.List;

public class Elements extends BaseUtils {
    private final List<WebElement> els;

    public Elements(final By by) {
        this.els = waitAll(by);
    }

    public static Elements findAll(By by) {
        return new Elements(by);
    }

    public List<WebElement> all() {
        return els;
    }

    public Integer count() {
        return els.size();
    }

}
