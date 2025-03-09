package com.monefy.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OffersPage {

    @FindBy(id = "com.monefy.app.lite:id/buttonClose")
    private WebElement closeButton;

    public OffersPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void clickCloseButton() {
        closeButton.click();
    }
}
