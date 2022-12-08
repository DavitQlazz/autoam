package hooks;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Browsers.*;

public class SetUp {

    public static final ThreadLocal<SoftAssertions> SOFT =
            ThreadLocal.withInitial(SoftAssertions::new);

    @Before
    public static void before(Scenario scenario) {
        System.out.println("Start " + scenario.getName());
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setVersion("105.0");
        caps.setCapability("enableVNC", true);
        caps.setCapability("enableVideo", false);

        Configuration.browser = CHROME;
        Configuration.timeout = 40000;
        Configuration.baseUrl = "https://zimiecms.synisys.com/zwe-dev/login";
        Configuration.browserSize = "1920x1080";

//        Configuration.browserVersion = "105";
//        Configuration.driverManagerEnabled = false;
//        Configuration.remote = "http://selenium:123456@172.17.17.114:4444/wd/hub";
//        Configuration.browserCapabilities = caps;
    }

    @After
    public static void tearDown() {
        Selenide.closeWebDriver();
    }
}
