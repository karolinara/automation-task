package chrometest;

import base.TestBase;
import helper.HistoryPageCommonMethods;
import helper.HomepageMethods;
import org.testng.annotations.Test;

import static constants.Constants.CRO_LANG_VALUE;
import static constants.Constants.SEARCH_KEYWORD;
import static constants.Constants.HISTORY_NAVIGATION_TAB;
import static constants.Constants.REQUESTED_DATE;
import static constants.Constants.REQUESTED_TIME_STAMP;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CheckWikiPageTest extends TestBase {

    /*
    1. open initial page https://www.wikipedia.org/
    2. inside search option select Cro language and search for Juraj Dobrila
    3. select history page from navigation bar on page https://hr.wikipedia.org/wiki/Juraj_Dobrila
    4. on page https://hr.wikipedia.org/w/index.php?title=Juraj_Dobrila&action=history perform filter
        using following date 01. srpanj 2022. and verify results
    5. assert that first record has timestamp: 21:25, 28.2.2022.
    */

    @Test(priority = 1)
    void openInitialWikiPage() {

        assertTrue(HomepageMethods.homepageCheck());

    }
    @Test(priority = 2)
    void setLanguageAndPerformSearch() {

        assertTrue(HomepageMethods.setSearchLanguage(CRO_LANG_VALUE));
        assertTrue(HomepageMethods.searchBy(SEARCH_KEYWORD));

    }
    @Test(priority = 3)
    void selectHistoryTab() {

        assertTrue(HomepageMethods.navigationSelection(HISTORY_NAVIGATION_TAB));

    }
    @Test(priority = 4)
    void filterByDateCheckResult() {

        assertTrue(HistoryPageCommonMethods.filterByDate(REQUESTED_DATE));

        String displayedDateTime = HistoryPageCommonMethods.getFirstResultTimestamp();
        assertEquals(REQUESTED_TIME_STAMP, displayedDateTime);

    }
}
