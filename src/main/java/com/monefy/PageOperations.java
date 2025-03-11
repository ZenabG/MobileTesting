package com.monefy;

import com.monefy.pages.ChooseCategoryPage;
import com.monefy.pages.HomePage;
import com.monefy.pages.OffersPage;
import com.monefy.pages.EnterAmountPage;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageOperations {

    private AndroidDriver driver;
    private OffersPage offersPage;
    private HomePage homePage;
    private EnterAmountPage enterAmountPage;
    private ChooseCategoryPage chooseCategoryPage;

    public PageOperations(AndroidDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.offersPage = new OffersPage(this.driver, wait);
        this.homePage = new HomePage(this.driver, wait);
        this.enterAmountPage = new EnterAmountPage(this.driver);
        this.chooseCategoryPage = new ChooseCategoryPage(this.driver);
    }

    public void skipOffers() throws InterruptedException {
        System.out.println("Skipping offers...");
        offersPage.clickCloseButton();
        System.out.println("Offers skipped.");
    }

    public void getStartedWidget() throws InterruptedException {
        System.out.println("Navigating 'Get Started' widget ...");
        for (int i = 0; i < 4; i++) {
            System.out.println("Clicking page: " + (i + 1));
            homePage.clickGetStartedButton();
        }
    }

    public void addSalary(String amount) {
        System.out.println("Adding salary: " + amount);
        homePage.clickIncomeButton();
        System.out.println("Clicked income button.");
        enterAmountPage.addAmount(amount);
        System.out.println("Entered amount: " + amount);
        enterAmountPage.clickChooseCategoryButton();
        System.out.println("Clicked choose category button.");
        chooseCategoryPage.clickSalaryCategory();
        System.out.println("Selected salary category.");
    }

    public void addSavings(String amount) {
        System.out.println("Adding savings: " + amount);
        homePage.clickIncomeButton();
        System.out.println("Clicked income button.");
        enterAmountPage.addAmount(amount);
        System.out.println("Entered amount: " + amount);
        enterAmountPage.clickChooseCategoryButton();
        System.out.println("Clicked choose category button.");
        chooseCategoryPage.clickSavingsCategory();
        System.out.println("Selected savings category.");
    }

    public void addCarMortgageExpense(String amount) {
        System.out.println("Adding car mortgage expense: " + amount);
        homePage.clickExpenseButton();
        System.out.println("Clicked expense button.");
        enterAmountPage.addAmount(amount);
        System.out.println("Entered amount: " + amount);
        enterAmountPage.clickChooseCategoryButton();
        System.out.println("Clicked choose category button.");
        chooseCategoryPage.clickCarsCategory();
        System.out.println("Selected cars category.");
    }

    public String getBalance() {
        System.out.println("Retrieving balance...");
        String balance = homePage.getBalance();
        System.out.println("Balance retrieved: " + balance);
        return balance;
    }
}