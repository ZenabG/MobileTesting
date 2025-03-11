package com.monefy.pages;

import com.monefy.PageOperations;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OffersPage {
    private final WebDriverWait wait;
    private static final Logger log = LoggerFactory.getLogger(OffersPage.class);
    @FindBy(id = "com.monefy.app.lite:id/buttonClose")
    private WebElement closeButton;

    public OffersPage(WebDriver driver, WebDriverWait wait) {
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public void clickCloseButton() {
        try {
            WebElement button = this.wait.until(ExpectedConditions.visibilityOf(closeButton));
            if (button != null) {
                button.click();
            }
        } catch (RuntimeException e) {
            log.info("Skip Offers widget not found");
        }
    }
}
