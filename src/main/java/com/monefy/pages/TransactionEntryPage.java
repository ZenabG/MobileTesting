package com.monefy.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TransactionEntryPage {

    @FindBy(id = "com.monefy.app.lite:id/buttonKeyboard1")
    private WebElement buttonKeyboard1;

    @FindBy(id = "com.monefy.app.lite:id/buttonKeyboard2")
    private WebElement buttonKeyboard2;

    @FindBy(id = "com.monefy.app.lite:id/buttonKeyboard3")
    private WebElement buttonKeyboard3;

    @FindBy(id = "com.monefy.app.lite:id/buttonKeyboard4")
    private WebElement buttonKeyboard4;

    @FindBy(id = "com.monefy.app.lite:id/buttonKeyboard5")
    private WebElement buttonKeyboard5;

    @FindBy(id = "com.monefy.app.lite:id/buttonKeyboard6")
    private WebElement buttonKeyboard6;

    @FindBy(id = "com.monefy.app.lite:id/buttonKeyboard7")
    private WebElement buttonKeyboard7;

    @FindBy(id = "com.monefy.app.lite:id/buttonKeyboard8")
    private WebElement buttonKeyboard8;

    @FindBy(id = "com.monefy.app.lite:id/buttonKeyboard9")
    private WebElement buttonKeyboard9;

    @FindBy(id = "com.monefy.app.lite:id/buttonKeyboard0")
    private WebElement buttonKeyboard0;

    @FindBy(id = "com.monefy.app.lite:id/amount_text")
    private WebElement amountText;

    @FindBy(id = "com.monefy.app.lite:id/keyboard_action_button")
    private WebElement chooseCategoryButton;

    public TransactionEntryPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void addAmount(String amount) {
        for (char c : amount.toCharArray()) {
            switch (c) {
                case '1':
                    buttonKeyboard1.click();
                    break;
                case '2':
                    buttonKeyboard2.click();
                    break;
                case '3':
                    buttonKeyboard3.click();
                    break;
                case '4':
                    buttonKeyboard4.click();
                    break;
                case '5':
                    buttonKeyboard5.click();
                    break;
                case '6':
                    buttonKeyboard6.click();
                    break;
                case '7':
                    buttonKeyboard7.click();
                    break;
                case '8':
                    buttonKeyboard8.click();
                    break;
                case '9':
                    buttonKeyboard9.click();
                    break;
                case '0':
                    buttonKeyboard0.click();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid character: " + c);
            }
        }
    }

        public void clickChooseCategoryButton() {
            chooseCategoryButton.click();
        }

        public String getAmountText() {
            return amountText.getText();
        }
    }


