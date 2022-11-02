package helper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static helper.CommonMethods.driver;
import static helper.CommonMethods.getLogger;

import static constants.Constants.MAIN_PAGE_BODY_ID;
import static constants.Constants.MAIN_PAGE_LOGO_XPATH;
import static constants.Constants.SEARCH_INPUT_XPATH;
import static constants.Constants.LANGUAGE_LIST_BUTTON_ID;
import static constants.Constants.FOOTER_CLASS_NAME;
import static constants.Constants.SEARCH_LANGUAGE_ID;
import static constants.Constants.SELECTED_LANGUAGE_ID;
import static constants.Constants.SEARCH_KEYWORD;
import static constants.Constants.MAIN_TITLE_ID;
import static constants.Constants.RIGHT_NAVIGATION_XPATH;

/**
 * Methods for homepage verification, search functionality, navigation selection
 */

public class HomepageMethods {

    /**
     * Verifies homepage main content
     * @return true when all homepage elements are displayed on the page
     */
    public static boolean homepageCheck() {

        try {
            if( driver.findElement(By.id(MAIN_PAGE_BODY_ID)).isDisplayed() &&
                driver.findElement(By.xpath(MAIN_PAGE_LOGO_XPATH)).isDisplayed() &&
                driver.findElement(By.xpath(SEARCH_INPUT_XPATH)).isDisplayed() &&
                driver.findElement(By.id(LANGUAGE_LIST_BUTTON_ID)).isDisplayed() &&
                driver.findElement(By.className(FOOTER_CLASS_NAME)).isDisplayed()) {
                return true;
            }
        } catch (Exception e) {
            getLogger().error("homepageCheck", e);
        }
        return false;
    }

    /**
     * Selects search language based od parameter lang
     * @param lang - identifier used for language selection
     * @return true when selected language is displayed inside search box
     */
    public static boolean setSearchLanguage(String lang) {

        try {

            Select selection = new Select(driver.findElement(By.id(SEARCH_LANGUAGE_ID)));
            selection.selectByValue(lang);
            Thread.sleep(2000);

            //Verify selected language
            if(driver.findElement(By.id(SELECTED_LANGUAGE_ID)).getText().toLowerCase().equals(lang)) {
                return true;
            }

        } catch (Exception e) {
            getLogger().error("setSearchLanguage", e);
        }
        return false;
    }

    /**
     * Performs search based on keyword content
     * @param keyword - text written inside search box
     * @return true when page title matches searched keyword
     */
    public static boolean searchBy(String keyword) {

        try {
            driver.findElement(By.xpath(SEARCH_INPUT_XPATH)).sendKeys(keyword);
            Thread.sleep(2000);

            // check for valid suggestion
            driver.findElement(By.partialLinkText(SEARCH_KEYWORD)).click();
            Thread.sleep(2000);

            if(verifyPageTitle(keyword)){
                return true;
            }

        } catch (Exception e) {

            getLogger().error("searchBy", e);

        }
        return false;
    }

    /**
     * Verifies if page title is equal searched content
     * @param keyword - text search string
     * @return true when page title matches searched string
     */
    public static boolean verifyPageTitle(String keyword) {

        try {
            Thread.sleep(2000);
            // check if main title has searched keyword
            if( driver.findElement(By.id(MAIN_TITLE_ID)).getText().equals(keyword)){
                return true;
            }

        } catch (Exception e) {
            getLogger().error("verifyPageTitle", e);
        }
        return false;
    }

    /**
     * Switches user to wanted tab based on tab text
     * @param tab - navigation tab name
     * @return true when tab string is successfully matched and clicked
     */
    public static boolean navigationSelection(String tab) {

        try {
            //Get list of elements on right navigation panel
            List<WebElement> listOfTabs = driver.findElements(By.xpath(RIGHT_NAVIGATION_XPATH));

            for( WebElement li : listOfTabs) {
                    String text = li.getText();
                    if(text.equals(tab)) {
                        li.click();
                        Thread.sleep(2000);
                        return true;
                }
            }

        } catch (Exception e) {
            getLogger().error("navigationSelection", e);
        }
        return false;
    }
}
