package com.monefy.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChooseCategoryPage {

    // income categories
    @FindBy(id = "//android.widget.ImageView[@resource-id='com.monefy.app.lite:id/imageView'][1]")
    private WebElement depositCategory;

    @FindBy(xpath = "//android.widget.TextView[@resource-id='com.monefy.app.lite:id/textCategoryName' and @text='Salary']")
    private WebElement salaryCategory;

    @FindBy(xpath = "//android.widget.ImageView[@resource-id='com.monefy.app.lite:id/imageView'][3]")
    private WebElement savingsCategory;

    // expense categories
    @FindBy(xpath = "//android.widget.GridView[@resource-id=\"com.monefy.app.lite:id/gridViewCategories\"]/android.widget.FrameLayout[1]/android.widget.LinearLayout\n")
    private WebElement billsCategory;

    @FindBy(xpath = "//android.widget.GridView[@resource-id=\"com.monefy.app.lite:id/gridViewCategories\"]/android.widget.FrameLayout[2]/android.widget.LinearLayout\n")
    private WebElement carsCategory;

    @FindBy(xpath = "//android.widget.GridView[@resource-id=\"com.monefy.app.lite:id/gridViewCategories\"]/android.widget.FrameLayout[3]/android.widget.LinearLayout\n")
    private WebElement clothesCategory;

    public ChooseCategoryPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void clickDepositCategory() {
        depositCategory.click();
    }

    public void clickSalaryCategory() {
        salaryCategory.click();
    }

    public void clickSavingsCategory() {
        savingsCategory.click();
    }

    public void clickBillsCategory() {
        billsCategory.click();
    }

    public void clickCarsCategory() {
        carsCategory.click();
    }

    public void clickClothesCategory() {
        clothesCategory.click();
    }
}
