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
//        actualIncomeBalance = actualIncomeBalance.replaceAll("[^\\d]", "");

        assertEquals("250000", actualIncomeBalance );
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
    public void stopAppiumServer(ITestResult result) throws IOException {
        reporterPluginSetUp.setTestInfo(getAppiumServerUrl(), driver.getSessionId().toString(), result.getMethod().getMethodName(), result.getStatus() == ITestResult.SUCCESS ? "PASS" : "FAIL", result.getThrowable() != null ? result.getThrowable().getMessage() : null);
        driver.quit();
        String report = reporterPluginSetUp.getReport();
        reporterPluginSetUp.createReportFile(report, "report");
        killAppiumServer();
    }
}
