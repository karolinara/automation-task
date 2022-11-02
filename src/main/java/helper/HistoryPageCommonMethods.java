package helper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static helper.CommonMethods.driver;
import static helper.CommonMethods.getLogger;

import static constants.Constants.INPUT_DATE_FIELD_ID;
import static constants.Constants.INPUT_DATE_VALUE_XPATH;
import static constants.Constants.FILTER_FIELD_ID;
import static constants.Constants.FILTER_BUTTON_XPATH;
import static constants.Constants.FILTER_SEARCH_ID;
import static constants.Constants.EXTEND_MONTHS_BUTTON_XPATH;
import static constants.Constants.VALID_DAYS_FOR_SELECTED_MONTH_XPATH;
import static constants.Constants.YEAR_LIST_XPATH;
import static constants.Constants.MONTHS_LIST_XPATH;
import static constants.Constants.RECORD_TIMESTAMP_LIST_CLASS;

/**
 * Contains methods for displayed results, filter and calendar dropdown usage
 */

public class HistoryPageCommonMethods {

    /**
     * Verifies filter dialog content
     * @return true when all elements are displayed inside filter dialog
     */
    public static boolean filterDialogCheck() {

        try {
            Thread.sleep(1000);
            if( driver.findElement(By.id(INPUT_DATE_FIELD_ID)).isDisplayed() &&
                driver.findElement(By.id(FILTER_FIELD_ID)).isDisplayed() &&
                driver.findElement(By.xpath(FILTER_BUTTON_XPATH)).isDisplayed()){
                return true;
            }
        } catch (Exception e) {
            getLogger().error("filterDialogCheck", e);
        }
        return false;
    }

    /**
     * Filtering results by selecting wanted date.
     * Date format is performed for individual day, month and year selection
     * After successful filterDialogCheck, starts year, month and day selection
     * @param date is value that user requested
     * @return true when input date field contains requested date value content
     */
    public static boolean filterByDate(String date) {

        try {
            //Prepare valid data using param date
            String [] dateParts = date.split(" ");
            String dayWithoutLeadingZero = dateParts[0].replaceAll("^0+(?!$)", "");
            String formattedDay = dayWithoutLeadingZero.replaceAll("[^0-9]",  ""); // only numerical data is used for day value
            String month = dateParts[1];    // month
            String year = dateParts[2];     // year

            Thread.sleep(3000);
            driver.findElement(By.id(FILTER_SEARCH_ID)).click();

            // check if filter dialog is displayed
            if(filterDialogCheck()) {
                driver.findElement(By.id(INPUT_DATE_FIELD_ID)).click();
                Thread.sleep(1000);
                driver.findElement(By.xpath(EXTEND_MONTHS_BUTTON_XPATH)).click();
                driver.findElement(By.xpath(EXTEND_MONTHS_BUTTON_XPATH)).click();

                // Select year, month and day
                setDateValue(year, YEAR_LIST_XPATH);
                setDateValue(month, MONTHS_LIST_XPATH);
                setDateValue(formattedDay, VALID_DAYS_FOR_SELECTED_MONTH_XPATH);

                //Verify if submitted date is successfully selected, if yes, perform filter
                String submittedDate = formattedDay+ ". " +month.substring(0,3)+ ". " +year;
                if(driver.findElement(By.xpath(INPUT_DATE_VALUE_XPATH)).getText().contains(submittedDate)) {
                    driver.findElement(By.xpath(FILTER_BUTTON_XPATH)).click();
                    Thread.sleep(2000);
                    return true;
                }
            }
        } catch (Exception e) {
            getLogger().error("filerByDate", e);
        }
        return false;
    }

    /**
     * Selecting date inside calendar dropdown
     * Go through locator xpath list of values and select matched requested date part
     * @param datePart part od the date that user requested for selection
     * @param locator xpath for requested year, month or day used for accessing list values
     */
    public static void setDateValue(String datePart, String locator) {

        try {
            Thread.sleep(2000);
            //Set date value
            List<WebElement> listOfValues = driver.findElements(By.xpath(locator));
            for (WebElement el : listOfValues) {
                String text = el.getText();
                if (text.equals(datePart)) {
                    el.click();
                    break;
                }
            }
        } catch(Exception e){
            getLogger().error("setDateValue", e);
        }
    }

    /**
     * Method is used for getting only first element text of record timestamps on result page
     * @return text content of first element
     */
    public static String getFirstResultTimestamp() {

        //Get the list of timestamps
        List<WebElement> recordTimestampsList = driver.findElements(By.className(RECORD_TIMESTAMP_LIST_CLASS));
        return recordTimestampsList.get(0).getText();

    }
}
