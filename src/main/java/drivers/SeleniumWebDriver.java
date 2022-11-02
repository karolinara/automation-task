package drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static constants.Constants.ChromeDriverPath;
import static helper.CommonMethods.readConfigParameter;

public class SeleniumWebDriver {

    /**
     * Creates instance of chrome driver
     * depending on variable Constants.HEADLESS_MODE, browser has ability to run in headless mode
     * @return created driver
     * */
    public static WebDriver createChromeDriver() {

        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        options.setHeadless(Boolean.parseBoolean(readConfigParameter("HEADLESS")));

        System.setProperty("webdriver.chrome.driver", ChromeDriverPath);
        WebDriver driver = new ChromeDriver(options);

        return driver;
    }
}