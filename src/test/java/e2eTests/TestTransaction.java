package e2eTests;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.logging.Logger;

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

    private static String appiumURL;

    @BeforeSuite
    public void startServer() throws MalformedURLException {
        appiumURL = startAppiumServer();
        log = Logger.getLogger("global");
    }

    @BeforeMethod
    public void initialise() throws MalformedURLException {
        createAndroidDriver(appiumURL);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        reporterPluginSetUp = new ReporterPluginSetUp();
        pageOperations = new PageOperations(driver, wait);
    }

    @Test
    public void testAddIncome() throws InterruptedException {
        System.out.println("Adding income...");
        pageOperations.skipOffers();
        pageOperations.addSalaryIncome("2500");
        String balance = pageOperations.getBalanceText();
        balance = getTrimmedAmount(balance);

        assertEquals("2500", balance);
    }

    @Test
    public void testUpdateBalanceByAddingExpense() throws InterruptedException {
        System.out.println("Adding expense...");
        pageOperations.skipOffers();
        pageOperations.addSalaryIncome("2500");

        String balance = pageOperations.getBalanceText();
        balance = getTrimmedAmount(balance);
        assertEquals("2500", balance);

        pageOperations.addCarMortgageExpense("500");
        String updatedBalance = pageOperations.getBalanceText();
        updatedBalance = getTrimmedAmount(updatedBalance);

        assertEquals("2000", updatedBalance);
    }


    @AfterMethod
    public void setTestReport(ITestResult result) throws IOException {
        if (driver != null) {
            String status = result.isSuccess() ? "PASS" : "FAIL";
            String errorMessage = result.getThrowable() != null ? result.getThrowable().toString() : "UnknownError";
            reporterPluginSetUp.setTestInfo(appiumURL, driver.getSessionId().toString(), result.getMethod().getMethodName(), status, errorMessage);
            driver.quit();
        }
    }

    @AfterSuite
    public void tearDown() throws IOException {
        String report = reporterPluginSetUp.getReport(appiumURL);
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
