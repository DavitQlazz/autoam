package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SetUp {

    public static final ThreadLocal<WebDriver> DRIVERS =
            ThreadLocal.withInitial(SetUp::createChromeDriver);

    @Before
    public static void before(Scenario scenario) {
        System.out.println("Start " + scenario.getName());
    }

    @After
    public static void tearDown() {
        DRIVERS.get().quit();
        DRIVERS.remove();
    }

    private static WebDriver createChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions().setHeadless(false);
        ChromeDriver chromeDriver = new ChromeDriver(options);
        chromeDriver.manage().window().setSize(new Dimension(1600, 900));
        return chromeDriver;
    }
}
