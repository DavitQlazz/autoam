package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static hooks.SetUp.DRIVERS;

public class BaseUtils {

    private static final Duration SHORT_TIMEOUT = Duration.ofMillis(5000);
    private static final Duration LONG_TIMEOUT = Duration.ofMillis(10000);

    public static WebElement waitingElement(final By by) {
        WebDriverWait wait = new WebDriverWait(DRIVERS.get(), LONG_TIMEOUT);
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static List<WebElement> waitingAll(final By by) {
        WebDriverWait wait = new WebDriverWait(DRIVERS.get(), LONG_TIMEOUT);
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    public static void open(String url) {
        DRIVERS.get().get(url);
    }

    public static void scrollTo(WebElement el) {
        JavascriptExecutor je = (JavascriptExecutor) DRIVERS.get();
        je.executeScript("arguments[0].scrollIntoView(true);", el);
    }
}
