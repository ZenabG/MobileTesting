package com.monefy.e2eTests;

import com.monefy.appium.AppiumSetUp;
import com.monefy.PageOperations;
import com.monefy.testReport.ReporterPluginSetUp;
import io.appium.java_client.android.AndroidDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.ITestResult;
import org.testng.ITestContext;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;

public class TestTransaction extends AppiumSetUp {
    private AndroidDriver driver;
    private PageOperations pageOperations;
    private ReporterPluginSetUp reporterPluginSetUp;
    private static String appiumURL;
    private static final Logger log = LoggerFactory.getLogger(TestTransaction.class);

    @BeforeSuite
    public void startServer() throws MalformedURLException {
        appiumURL = startAppium();
    }

    @BeforeMethod
    public void initialise() throws MalformedURLException {
        driver = createAndroidDriver(appiumURL);
        reporterPluginSetUp = new ReporterPluginSetUp();
        pageOperations = new PageOperations(driver, wait);
    }

    @Test
    public void testAddIncome() throws InterruptedException {
        log.info("Starting test to add income");
        String savings = "1000";
        pageOperations.getStartedWidget();
        pageOperations.skipOffers();
        pageOperations.addSavings(savings);
        String balance = pageOperations.getBalance();
        balance = getTrimmedAmount(balance);
        assertEquals(savings, balance);
    }

    @Test
    public void testUpdateBalanceByAddingExpense() throws InterruptedException {
        log.info("Starting test to update balance by adding expense");
        String salary = "2500";
        String carExpense = "500";

        pageOperations.getStartedWidget();

        pageOperations.skipOffers();
        pageOperations.addSalary(salary);

        String balance = pageOperations.getBalance();
        balance = getTrimmedAmount(balance);

        pageOperations.addCarMortgageExpense(carExpense);
        String updatedBalance = pageOperations.getBalance();
        updatedBalance = getTrimmedAmount(updatedBalance);

        assertTrue(Integer.parseInt(updatedBalance) < Integer.parseInt(balance));
    }


    @AfterMethod
    public void createTestReport(ITestResult result) throws IOException {
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
