package com.monefy.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GetStartedPage {

    private final WebDriverWait wait;

    @FindBy(id = "com.monefy.app.lite:id/buttonContinue")
    private WebElement getStartedButton;

    public GetStartedPage(WebDriver driver, WebDriverWait wait) {
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
            System.out.println("Get Started widget not found");
        }
    }
}