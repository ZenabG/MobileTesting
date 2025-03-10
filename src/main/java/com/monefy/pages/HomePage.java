package com.monefy.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private final WebDriverWait wait;
    @FindBy(id = "com.monefy.app.lite:id/buttonContinue")
    private WebElement getStartedButton;

    @FindBy(id = "com.monefy.app.lite:id/income_button_title")
    private WebElement homeIncomeButton;

    @FindBy(id = "com.monefy.app.lite:id/expense_button")
    private WebElement homeExpenseButton;

    @FindBy(id = "com.monefy.app.lite:id/balance_container")
    private WebElement homeBalanceButton;

    @FindBy(xpath = " //android.widget.TextView[@resource-id=\"com.monefy.app.lite:id/income_amount_text\"]")
    private WebElement homeDisplayWheelIncomeIcon;

    public HomePage(WebDriver driver, WebDriverWait wait) {
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public void clickGetStartedButton() {
        try {
            WebElement button = this.wait.until(ExpectedConditions.visibilityOf(getStartedButton));
            if (button != null) {
                button.click();
            }
        } catch (RuntimeException e) {
            // Handle the case where the button is not present
            System.out.println("Get Started button is not present.");
        }
    }

    public void clickIncomeButton() {
        homeIncomeButton.click();
    }

    public void clickExpenseButton() {
        homeExpenseButton.click();
    }

    public void clickBalanceButton() {homeBalanceButton.click();
    }

    public String getDisplayWheelIncomeIconText() {
        return homeDisplayWheelIncomeIcon.getText();
    }
}
