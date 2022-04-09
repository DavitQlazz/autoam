package elements;

import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.BaseUtils;

import java.util.List;

public class Elements extends BaseUtils {
    private final List<WebElement> els;
    private final SoftAssertions soft;

    public Elements(final By by) {
        this.els = waitingAll(by);
        soft = new SoftAssertions();
    }

    public static Elements findAll(By by) {
        return new Elements(by);
    }

    public void allContain(String text) {
        els.forEach(el -> soft.assertThat(el.getText()).contains(text));
        soft.assertAll();
    }

}
