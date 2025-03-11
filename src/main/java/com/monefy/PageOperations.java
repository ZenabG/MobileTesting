package com.monefy;

import com.monefy.pages.ChooseCategoryPage;
import com.monefy.pages.HomePage;
import com.monefy.pages.OffersPage;
import com.monefy.pages.EnterAmountPage;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PageOperations {

    private AndroidDriver driver;
    private OffersPage offersPage;
    private HomePage homePage;
    private EnterAmountPage enterAmountPage;
    private ChooseCategoryPage chooseCategoryPage;

    private static final Logger log = LoggerFactory.getLogger(PageOperations.class);

    public PageOperations(AndroidDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.offersPage = new OffersPage(this.driver, wait);
        this.homePage = new HomePage(this.driver, wait);
        this.enterAmountPage = new EnterAmountPage(this.driver);
        this.chooseCategoryPage = new ChooseCategoryPage(this.driver);
    }

    public void skipOffers() throws InterruptedException {
        offersPage.clickCloseButton();
    }

    public void getStartedWidget() {
        // Widget has to be clicked 4 times to get started
        for (int i = 0; i < 4; i++) {
            homePage.clickGetStartedButton();
        }
    }

    public void addSalary(String amount) {
        homePage.clickIncomeButton();
        enterAmountPage.addAmount(amount);
        enterAmountPage.clickChooseCategoryButton();
        chooseCategoryPage.clickSalaryCategory();
    }

    public void addSavings(String amount) {
        homePage.clickIncomeButton();
        enterAmountPage.addAmount(amount);
        enterAmountPage.clickChooseCategoryButton();
        chooseCategoryPage.clickSavingsCategory();
    }

    public void addCarMortgageExpense(String amount) {
        homePage.clickExpenseButton();
        enterAmountPage.addAmount(amount);
        enterAmountPage.clickChooseCategoryButton();
        chooseCategoryPage.clickCarsCategory();
    }

    public String getBalance() {
        return homePage.getBalance();
    }
}
