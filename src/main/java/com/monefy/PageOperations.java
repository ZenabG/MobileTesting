package com.monefy;

import com.monefy.pages.ChooseCategoryPage;
import com.monefy.pages.HomePage;
import com.monefy.pages.OffersPage;
import com.monefy.pages.TransactionEntryPage;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageOperations {

    private AndroidDriver driver;
    private OffersPage offersPage;
    private HomePage homePage;
    private TransactionEntryPage transactionEntryPage;
    private ChooseCategoryPage chooseCategoryPage;

    private final WebDriverWait wait;

    public PageOperations(AndroidDriver driver) {
        this.driver = driver;
        this.offersPage = new OffersPage(this.driver);
        this.homePage = new HomePage(this.driver);
        this.transactionEntryPage = new TransactionEntryPage(this.driver);
        this.chooseCategoryPage = new ChooseCategoryPage(this.driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void skipOffers() throws InterruptedException {
        for (int i = 0; i < 4; i++) {
            this.wait.until(ExpectedConditions.elementToBeClickable(homePage.clickGetStartedButton())).click();
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
