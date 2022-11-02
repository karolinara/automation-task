package base;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static helper.CommonMethods.*;
import static org.testng.Assert.assertTrue;
import static constants.Constants.TEST_REPORT_LOCATION;

public class TestBase {

    public int indexTC = 0;

    @BeforeClass
    public void setupApplication()
    {
        prepareTestFile();
        Reporter.log("=====Browser Session Started=====", true);

        assertTrue(openBrowser());
        Reporter.log("=====Application Started=====", true);

    }
    @AfterClass
    public void closeApplication()
    {
        closeBrowser();
        Reporter.log("=====Browser Session End=====", true);

    }
    @AfterMethod
    public void afterMyMethod (ITestResult result) throws IOException{

        indexTC++;
        long durationMillis = result.getEndMillis() - result.getStartMillis();
        String duration = formatMs(durationMillis);
        String formattedStartTime = formatMillisToDate(result.getStartMillis());
        String formattedEndTime = formatMillisToDate(result.getEndMillis());

        if(result.getStatus() == ITestResult.FAILURE){
            updateResult(indexTC, duration, formattedStartTime, formattedEndTime, result.getName(), "Fail" );
        }
        else if(result.getStatus() == ITestResult.SUCCESS){
            updateResult(indexTC, duration, formattedStartTime, formattedEndTime, result.getName(), "Pass");
        }
        else {
            updateResult(indexTC, duration, formattedStartTime, formattedEndTime, result.getName(), "Other");
        }
    }

    public static void updateResult(int indexTC, String duration, String formattedStartTime, String formattedEndTime, String methodName, String response) throws IOException{

        File file = new File(TEST_REPORT_LOCATION);

        BufferedWriter bw1 = new BufferedWriter(new FileWriter(file, true));

        bw1.write("<tr>" + "\n");
        bw1.write("<td bgcolor='#FFFDC' align='Center'><font color='#000000' face='Tahoma' size='2'>" + indexTC + "</font></td>");
        bw1.write("<td bgcolor='#FFFDC' valign='midlle' align='left'<b><font color='#000000' face='Tahoma' size='2'>" + methodName + "</font></td>");
        bw1.write("<td bgcolor='#FFFDC' valign='midlle' align='left'<b><font color='#000000' face='Tahoma' size='2'>" + duration + "</font></td>");
        bw1.write("<td bgcolor='#FFFDC' valign='midlle' align='left'<b><font color='#000000' face='Tahoma' size='2'>" + formattedStartTime+ "</font></td>");
        bw1.write("<td bgcolor='#FFFDC' valign='midlle' align='left'<b><font color='#000000' face='Tahoma' size='2'>" + formattedEndTime+ "</font></td>");
        bw1.write("<td bgcolor='#FFFDC' valign='midlle' align='left'<b><font color='#000000' face='Tahoma' size='2'>" + response + "</font></td>");
        bw1.write("</tr>" + "\n");
        bw1.write("</body>" + "\n");
        bw1.write("</html>" + "\n");
        bw1.close();
    }

    public void prepareTestFile(){

        boolean headlessMode =  Boolean.parseBoolean(readConfigParameter("HEADLESS"));

        String browser = headlessMode ? "Headless" : "Chrome";

        File file = new File(TEST_REPORT_LOCATION);
        file.delete();

        try{
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("<html>" + "\n");
            bw.write("<head><title>" + "Test execution report" + "</title>" + "\n");
            bw.write("</head>" + "\n");
            bw.write("<body>");
            bw.write("<font face='Tahoma'size='1'>" + "\n");
            bw.write("<h1 align='center'>" + "Test execution report" + "</h1>" + "\n");
            bw.write("<h2 align='center'>" + "Browser: " + browser + "</h2" + "\n");

            bw.write("<table align='center' border='0' width='70%' height='10'>");
            bw.write("<tr><td width='70%' </td></tr>");
            bw.write("<table align='center' border='1' width='70%' height='47'>");
            bw.write("<tr>");
            bw.write("</tr>");
            bw.write("</tr>");
            bw.write("<td bgcolor='#CCCCFF' align='center'><b><font color='#000000' face='Tahoma' size='2'>TC No.</font></b></td>");
            bw.write("<td bgcolor='#CCCCFF' align='left'><b><font color='#000000' face='Tahoma' size='2'>Test method</font></b></td>");
            bw.write("<td bgcolor='#CCCCFF' align='left'><b><font color='#000000' face='Tahoma' size='2'>Duration</font></b></td>");
            bw.write("<td bgcolor='#CCCCFF' align='left'><b><font color='#000000' face='Tahoma' size='2'>Started</font></b></td>");
            bw.write("<td bgcolor='#CCCCFF' align='left'><b><font color='#000000' face='Tahoma' size='2'>Ended</font></b></td>");
            bw.write("<td bgcolor='#CCCCFF' align='center'><b><font color='#000000' face='Tahoma' size='2'>Status</font></b></td>");
            bw.write("</tr>");
            bw.flush();
            bw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
