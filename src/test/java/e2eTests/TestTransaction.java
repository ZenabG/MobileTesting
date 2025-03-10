package e2eTests;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.testng.annotations.*;
import com.monefy.appium.AppiumSetUp;
import com.monefy.PageOperations;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

import org.openqa.selenium.support.ui.WebDriverWait;
import testReport.ReporterPluginSetUp;
import org.testng.ITestResult;
import org.testng.ITestContext;

public class TestTransaction extends AppiumSetUp {

    private PageOperations pageOperations;
    private WebDriverWait wait;

    private ReporterPluginSetUp reporterPluginSetUp;

    @BeforeSuite
    public void startAppiumServer() throws MalformedURLException {
        createAndroidDriver();
        log = Logger.getLogger("global");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        reporterPluginSetUp = new ReporterPluginSetUp();
    }

    @BeforeMethod
    public void initialise() {
        pageOperations = new PageOperations(driver, wait);
    }

    @Test
    public void testAddTransaction() throws MalformedURLException, InterruptedException {
        pageOperations.skipOffers();
        pageOperations.addSalaryIncome("2500");
        pageOperations.addCarMortgageExpense("500");
        String actualIncomeBalance = pageOperations.getIncomeBalance();

        // Remove currency symbol, commas, and decimal point
        actualIncomeBalance = actualIncomeBalance.replaceAll("[^\\d]", "");

        // Convert to integer and divide by 100 to remove trailing zeroes
        int balance = Integer.parseInt(actualIncomeBalance) / 100;

        assertEquals("2500", String.valueOf(balance));
    }

//    @Test
//    public void testUpdateTransaction() throws MalformedURLException, InterruptedException {
//
//        pageOperations.BalanceButton();
//        pageOperations.addSalaryIncome("2500");
//        pageOperations.updateSalaryIncome("3000");
//        String actualIncomeBalance = pageOperations.getIncomeBalance();
//
//        // Remove currency symbol, commas, and decimal point
//        actualIncomeBalance = actualIncomeBalance.replaceAll("[^\\d]", "");
//
//        assertEquals("300000", actualIncomeBalance);
//    }


    @AfterSuite
    public void stopAppiumServer(ITestContext context) throws IOException {
        String appiumUrl = getAppiumServerUrl();
        System.out.println("Appium URL: " + appiumUrl);

        // Remove /wd/hub/ from the URL if present
        if (appiumUrl.contains("/wd/hub/")) {
            appiumUrl = appiumUrl.replace("/wd/hub", "");
        }

        if (driver != null) {
            for (ITestResult result : context.getPassedTests().getAllResults()) {
                reporterPluginSetUp.setTestInfo(appiumUrl, driver.getSessionId().toString(), result.getMethod().getMethodName(), "PASS", null);
            }
            for (ITestResult result : context.getFailedTests().getAllResults()) {
                reporterPluginSetUp.setTestInfo(appiumUrl, driver.getSessionId().toString(), result.getMethod().getMethodName(), "FAIL", result.getThrowable().getMessage());
            }
            for (ITestResult result : context.getSkippedTests().getAllResults()) {
                reporterPluginSetUp.setTestInfo(appiumUrl, driver.getSessionId().toString(), result.getMethod().getMethodName(), "SKIP", null);
            }
            driver.quit();
        }
        String report = reporterPluginSetUp.getReport(appiumUrl);
        reporterPluginSetUp.createReportFile(report, "report");
        killAppiumServer();
    }
}
