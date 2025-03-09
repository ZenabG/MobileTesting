package e2eTests;

import java.net.MalformedURLException;
import java.util.logging.Logger;

import org.testng.annotations.*;
import com.monefy.appium.AppiumSetUp;
import com.monefy.PageOperations;

public class TestTransaction extends AppiumSetUp {

    private PageOperations pageOperations;

    @BeforeSuite
    public void startAppiumServer() throws MalformedURLException {
        createAndroidDriver();
        log = Logger.getLogger("global");
    }

    @BeforeMethod
    public void initialise() {
        pageOperations = new PageOperations(driver);
    }

    @Test
    public void testAddTransation() throws MalformedURLException, InterruptedException {
        pageOperations.skipOffers();
        pageOperations.addSalaryIncome("2500");
        pageOperations.addCarMortgageExpense("500");
// To add the balance wheel assertion
    }

    @AfterSuite
    public void stopAppiumServer() {
        killAppiumServer();
    }
}
