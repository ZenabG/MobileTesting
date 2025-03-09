package com.monefy.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    @FindBy(id = "com.monefy.app.lite:id/income_button_title")
    private WebElement homeIncomeButton;

    @FindBy(id = "com.monefy.app.lite:id/expense_button")
    private WebElement homeExpenseButton;

    @FindBy(id = "com.monefy.app.lite:id/balance_container")
    private WebElement homeBalanceButton;


    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void clickIncomeButton() {
        homeIncomeButton.click();
    }

    public void clickExpenseButton() {
        homeExpenseButton.click();
    }

    public void clickBalanceButton() {
        homeBalanceButton.click();
    }
}
