package constants;

public class Constants {

    //Testing data
    public static final String SEARCH_KEYWORD = "Juraj Dobrila";
    public static final String HISTORY_NAVIGATION_TAB = "Vidi povijest";
    public static final String CRO_LANG_VALUE = "hr";
    public static final String REQUESTED_DATE = "01. srpanj 2022";
    public static final String REQUESTED_TIME_STAMP = "20:25, 28. veljače 2022.";

    //Test report
    public static final String TEST_REPORT_LOCATION = "src/test/resources/TestReport.html";

    //Browser config
    public static final String ChromeDriverPath = "src/tools/chromedriver.exe";
    public static final String BASE_URL = "https://www.wikipedia.org/";

    //Homepage
    public static final String MAIN_PAGE_BODY_ID = "www-wikipedia-org";
    public static final String MAIN_PAGE_LOGO_XPATH = "//*[@alt='Wikipedia']";
    public static final String SEARCH_INPUT_XPATH = "//*[@name='search']";
    public static final String LANGUAGE_LIST_BUTTON_ID = "js-lang-list-button";
    public static final String SELECTED_LANGUAGE_ID = "jsLangLabel";

    public static final String SEARCH_LANGUAGE_ID = "searchLanguage";
    public static final String FOOTER_CLASS_NAME = "footer";

    //Search results
    public static final String MAIN_TITLE_ID = "firstHeading";
    public static final String RIGHT_NAVIGATION_XPATH = "//*[@id='p-views']/div/ul/li/a";

    //History page
    //Filter
    public static final String FILTER_SEARCH_ID = "mw-history-search";
    public static final String INPUT_DATE_FIELD_ID = "mw-input-date-range-to";
    public static final String INPUT_DATE_VALUE_XPATH = "//*[@class='mw-widget-dateInputWidget-handle']";
    public static final String FILTER_FIELD_ID = "tagfilter";
    public static final String FILTER_BUTTON_XPATH = "//button[@type='submit']";

    //Calendar dropdown
    public static final String EXTEND_MONTHS_BUTTON_XPATH = "//*[(@class='mw-widget-calendarWidget-header')]";
    public static final String YEAR_LIST_XPATH = "//*[contains(@class,'mw-widget-calendarWidget-item mw-widget-calendarWidget-year')]";
    public static final String MONTHS_LIST_XPATH = "//*[contains(@class,'mw-widget-calendarWidget-month')]";
    public static final String VALID_DAYS_FOR_SELECTED_MONTH_XPATH = "//*[@class='mw-widget-calendarWidget-item mw-widget-calendarWidget-day']";

    //History results
    public static final String RECORD_TIMESTAMP_LIST_CLASS = "mw-changeslist-date";

}
