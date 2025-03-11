package com.monefy.e2eTests;

import com.monefy.appium.AppiumSetUp;
import com.monefy.PageOperations;
import com.monefy.testReport.ReporterPluginSetUp;
import io.appium.java_client.android.AndroidDriver;
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

/**
 * TestTransaction class contains end-to-end tests for the Monefy app.
 * It validates core functionalities such as adding income and updating the balance by adding expenses.
 */
public class TestTransaction extends AppiumSetUp {
    private PageOperations pageOperations;
    private ReporterPluginSetUp reporterPluginSetUp;
    private static String appiumURL;

    /**
     * Starts the Appium server before any tests are executed.
     *
     * @throws MalformedURLException if the URL is malformed
     */
    @BeforeSuite
    public void startServer() throws MalformedURLException {
        appiumURL = startAppium();
    }

    /**
     * Initializes the Android driver and sets up the page operations before each test method.
     *
     * @throws MalformedURLException if the URL is malformed
     */
    @BeforeMethod
    public void initialise() throws MalformedURLException {
        createAndroidDriver(appiumURL);
        reporterPluginSetUp = new ReporterPluginSetUp();
        pageOperations = new PageOperations(driver, wait);
    }

    /**
     * Validates that the user can add income to the Monefy app and that the balance is updated correctly.
     *
     * @throws InterruptedException if the thread is interrupted
     */
    @Test
    public void testAddIncome() throws InterruptedException {
        System.out.println("[Test 1] - Starting test to add income");
        String savings = "1000";
        pageOperations.getStartedWidget();
        pageOperations.skipOffers();
        pageOperations.addSavings(savings);
        String balance = pageOperations.getBalance();
        balance = getTrimmedAmount(balance);
        assertEquals(savings, balance);
    }

    /**
     * Validates that the user can add an expense to the Monefy app and that the balance is updated correctly.
     *
     * @throws InterruptedException if the thread is interrupted
     */
    @Test
    public void testUpdateBalanceByAddingExpense() throws InterruptedException {
        System.out.println("[Test 2] - Starting test to update balance by adding expense");
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

    /**
     * Generates a test report after each test method execution.
     *
     * @param result the result of the test method
     * @throws IOException if an I/O error occurs
     */
    @AfterMethod
    public void createTestReport(ITestResult result) throws IOException {
        if (driver != null) {
            if (System.getenv().get("CI").equalsIgnoreCase("true")) {
                String status = result.isSuccess() ? "PASS" : "FAIL";
                String errorMessage = result.getThrowable() != null ? result.getThrowable().toString() : "UnknownError";
                reporterPluginSetUp.setTestInfo(appiumURL, driver.getSessionId().toString(), result.getMethod().getMethodName(), status, errorMessage);
            }
            driver.quit();
        }
    }

    /**
     * Stops the Appium server and generates the final test report after all tests are executed.
     *
     * @throws IOException if an I/O error occurs
     */
    @AfterSuite
    public void tearDown() throws IOException {
        if (System.getenv().get("CI").equalsIgnoreCase("true")) {
            String report = reporterPluginSetUp.getReport(appiumURL);
            reporterPluginSetUp.createReportFile(report, "report");
        }
        killAppiumServer();
    }

    /**
     * Removes currency symbol, commas, and decimal point from the amount and converts it to an integer.
     *
     * @param amount the amount to be trimmed
     * @return the trimmed amount as a string
     */
    private String getTrimmedAmount(String amount) {
        amount = amount.replaceAll("[^\\d]", "");
        int balance = Integer.parseInt(amount) / 100;
        return String.valueOf(balance);
    }
}