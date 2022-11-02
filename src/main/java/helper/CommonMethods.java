package helper;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static constants.Constants.BASE_URL;
import static constants.Constants.MAIN_PAGE_BODY_ID;
import static drivers.SeleniumWebDriver.createChromeDriver;

/**
 * Common methods used across the project
 */

public class CommonMethods {

    public static WebDriver driver;
    private static WebDriverWait wait;
    private static Logger logger = Logger.getLogger(CommonMethods.class);

    // get wait
    public static WebDriverWait getWait() {
        return wait;
    }

    // get logger
    public static Logger getLogger() {
        return logger;
    }

    /**
     * Includes creation of chrome driver instance, getting to the base url and maximising browser window
     * @return true when main page body id identifier is displayed
     */
    public static boolean openBrowser() {

        try {
            driver = createChromeDriver();
            driver.get(BASE_URL);
            driver.manage().window().maximize();
            Thread.sleep(10);

            if (driver.findElement(By.id(MAIN_PAGE_BODY_ID)).isDisplayed()){
                return true;
            }

        } catch (Exception e) {
            getLogger().error("openBrowser", e);
        }
        return false;
    }

    /**
     * Stopping driver instance
     */
    public static void closeBrowser() {

        driver.close();

    }

    /**
     * Converts long value of milliseconds into HH:mm:ss:sss format
     * Milliseconds are also displayed because some tests last under one second
     * @param millis long value used for conversion to time
     * @return HH:mm:ss:sss time format
     */
    public static String formatMs(long millis) {

       return String.format("%02d:%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                TimeUnit.MILLISECONDS.toSeconds(millis) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)),
                TimeUnit.MILLISECONDS.toMillis(millis) -                                      // Added extra conversion for millis display
                         TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(millis)));
    }

    /**
     * Converts long value of milliseconds into date time format
     * @param millis long value used for conversion to date time
     * @return yyyy-MM-dd HH:mm:ss value
     */
    public static String formatMillisToDate(long millis) {

        String date = LocalDateTime.ofInstant(Instant.ofEpochMilli(millis),
                ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        return date;
    }
    /**
     * Reads configuration file
     * @param paramName getting value of wanted parameter name variable
     * @return value of paramName
     */
    public static String readConfigParameter(String paramName) {

        Properties prop = new Properties();
        try (FileInputStream fis = new FileInputStream("src/main/resources/parameters.config")) {

            prop.load(fis);

        } catch (Exception e) {
            getLogger().error("readConfigParameter", e);
        }
        return (prop.getProperty(paramName));
    }
}
