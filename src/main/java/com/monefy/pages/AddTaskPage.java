package com.monefy.pages;

import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddTaskPage {

	//delete button

	@FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"com.monefy.app.lite:id/piegraph\"]/android.widget.TextView[7]")
	private WebElement dropdowntoggle;

	@FindBy(xpath = "(//android.widget.TextView[@resource-id='com.monefy.app.lite:id/textViewTransactionAmount'])[2]")
	private WebElement selectTransaction;

	@FindBy(id = "com.monefy.app.lite:id/delete")
	private WebElement deleteButton;

	// navigate button
	@FindBy(xpath = "//android.widget.ImageButton[@content-desc=\"Navigate up\"]")
	private WebElement navigateUp;

}
