package com.monefy;

import com.monefy.pages.ChooseCategoryPage;
import com.monefy.pages.HomePage;
import com.monefy.pages.OffersPage;
import com.monefy.pages.TransactionEntryPage;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageOperations {

    private AndroidDriver driver;
    private OffersPage offersPage;
    private HomePage homePage;
    private TransactionEntryPage transactionEntryPage;
    private ChooseCategoryPage chooseCategoryPage;

    public PageOperations(AndroidDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.offersPage = new OffersPage(this.driver);
        this.homePage = new HomePage(this.driver, wait);
        this.transactionEntryPage = new TransactionEntryPage(this.driver);
        this.chooseCategoryPage = new ChooseCategoryPage(this.driver);
    }

    public void BalanceButton() {
        homePage.clickBalanceButton();
    }

    public void skipOffers() throws InterruptedException {
        for (int i = 0; i < 4; i++) {
            homePage.clickGetStartedButton();
        }

        offersPage.clickCloseButton();
    }

    public void addSalaryIncome(String amount) {
        homePage.clickIncomeButton();
        transactionEntryPage.addAmount(amount);
        transactionEntryPage.clickChooseCategoryButton();
        chooseCategoryPage.clickSalaryCategory();
    }

    public void addCarMortgageExpense(String amount) throws InterruptedException {
        Thread.sleep(4000);
        homePage.clickExpenseButton();
        Thread.sleep(2000);
        transactionEntryPage.addAmount(amount);
        transactionEntryPage.clickChooseCategoryButton();
        chooseCategoryPage.clickCarsCategory();
    }

    public String getIncomeBalance() {
        return homePage.getDisplayWheelIncomeIconText();
    }

}
