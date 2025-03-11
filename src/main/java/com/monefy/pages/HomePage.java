package com.monefy.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private final WebDriverWait wait;

    @FindBy(id = "com.monefy.app.lite:id/income_button_title")
    private WebElement homeIncomeButton;

    @FindBy(id = "com.monefy.app.lite:id/expense_button")
    private WebElement homeExpenseButton;

    @FindBy(id = "com.monefy.app.lite:id/balance_amount")
    private WebElement homeBalanceButton;

    @FindBy(xpath = " //android.widget.TextView[@resource-id='com.monefy.app.lite:id/income_amount_text']")
    private WebElement homeDisplayWheelIncomeIcon;

    public HomePage(WebDriver driver, WebDriverWait wait) {
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public void clickIncomeButton() {
        try {
            WebElement button = this.wait.until(ExpectedConditions.visibilityOf(homeIncomeButton));
            if (button != null) {
                button.click();
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("Income button not found");
        }
    }

    public void clickExpenseButton() {
        try {
            WebElement button = this.wait.until(ExpectedConditions.visibilityOf(homeExpenseButton));
            if (button != null) {
                button.click();
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("Expense button not found");
        }
    }

    public String getBalance() {
        String balance = null;
        try {
            WebElement button = this.wait.until(ExpectedConditions.visibilityOf(homeBalanceButton));
            if (button != null) {
                balance = button.getText();
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("Balance button not found");
        }
        return balance;
    }
}