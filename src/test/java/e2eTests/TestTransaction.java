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
        pageOperations = new PageOperations(driver, wait);
    }

    @Test
    public void testAddIncome() throws InterruptedException {
        System.out.println("Adding income...");
        pageOperations.skipOffers();
        pageOperations.addSalaryIncome("2500");
//        Thread.sleep(4000);
        String actualIncomeBalance = pageOperations.getBalanceText();
//        System.out.println("Actual Income Balance: " + actualIncomeBalance);

        actualIncomeBalance = getTrimmedAmount(actualIncomeBalance);

        assertEquals("2500", actualIncomeBalance);
    }

    @Test
    public void testUpdateBalanceByAddingExpense() throws MalformedURLException, InterruptedException {
        System.out.println("Adding expense...");
        pageOperations.skipOffers();
        pageOperations.addCarMortgageExpense("500");
//        Thread.sleep(4000);
        String actualIncomeBalance = pageOperations.getBalanceText();
//        System.out.println("Actual Income Balance: " + actualIncomeBalance);

        actualIncomeBalance = getTrimmedAmount(actualIncomeBalance);

        assertEquals("2000", actualIncomeBalance);
    }


    @AfterSuite
    public void stopAppiumServer(ITestContext context) throws IOException {
        if (driver != null) {
            for (ITestResult result : context.getPassedTests().getAllResults()) {
                reporterPluginSetUp.setTestInfo(getAppiumServerUrl(), driver.getSessionId().toString(), result.getMethod().getMethodName(), "PASS", null);
            }
            for (ITestResult result : context.getFailedTests().getAllResults()) {
                reporterPluginSetUp.setTestInfo(getAppiumServerUrl(), driver.getSessionId().toString(), result.getMethod().getMethodName(), "FAIL", result.getThrowable().getMessage());
            }
            for (ITestResult result : context.getSkippedTests().getAllResults()) {
                reporterPluginSetUp.setTestInfo(getAppiumServerUrl(), driver.getSessionId().toString(), result.getMethod().getMethodName(), "SKIP", null);
            }
            driver.quit();
        }
        String report = reporterPluginSetUp.getReport(getAppiumServerUrl());
        reporterPluginSetUp.createReportFile(report, "report");
        killAppiumServer();
    }

    private String getTrimmedAmount(String amount) {
        // Remove currency symbol, commas, and decimal point
        amount = amount.replaceAll("[^\\d]", "");

        // Convert to integer and divide by 100 to remove trailing zeroes
        int balance = Integer.parseInt(amount) / 100;

        return String.valueOf(balance);
    }
}
