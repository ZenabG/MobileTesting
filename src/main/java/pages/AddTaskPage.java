package pages;

import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddTaskPage {
	// Home page
	@FindBy(id = "com.monefy.app.lite:id/buttonClose")
	private WebElement buttonClose;
	
	@FindBy(id = "com.monefy.app.lite:id/search_view")
	private WebElement searchView;

	//Keypad
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

	@FindBy(id = "com.monefy.app.lite:id/buttonKeyboard0")
	private WebElement buttonKeyboard0;

	//buttons
	@FindBy(id = "com.monefy.app.lite:id/buttonKeyboardPlus")
	private WebElement buttonKeyboardPlus;

	@FindBy(id = "com.monefy.app.lite:id/amount_text")
	private WebElement amountText;

	@FindBy(id = "com.monefy.app.lite:id/keyboard_action_button")
	private WebElement chooseCategoryButton;




	//	income page

	@FindBy(id = "com.monefy.app.lite:id/income_button_title")
	private WebElement incomeTitle;

	@FindBy(xpath = "//android.widget.ImageView[@resource-id='com.monefy.app.lite:id/imageView'][1]")
	private WebElement depositCategory;

	@FindBy(xpath = "//android.widget.ImageView[@resource-id='com.monefy.app.lite:id/imageView'][2]")
	private WebElement salaryCategory;

	@FindBy(xpath = "//android.widget.ImageView[@resource-id='com.monefy.app.lite:id/imageView'][3]")
	private WebElement savingsCategory;


	//	expense page
	@FindBy(id = "com.monefy.app.lite:id/expense_button")
	private WebElement expenseButton;

	@FindBy(xpath = "//android.widget.GridView[@resource-id=\"com.monefy.app.lite:id/gridViewCategories\"]/android.widget.FrameLayout[1]/android.widget.LinearLayout\n")
	private WebElement billsCategory;

	@FindBy(xpath = "//android.widget.GridView[@resource-id=\"com.monefy.app.lite:id/gridViewCategories\"]/android.widget.FrameLayout[2]/android.widget.LinearLayout\n")
	private WebElement carsCategory;

	@FindBy(xpath = "//android.widget.GridView[@resource-id=\"com.monefy.app.lite:id/gridViewCategories\"]/android.widget.FrameLayout[3]/android.widget.LinearLayout\n")
	private WebElement clothesCategory;


	//delete button

	@FindBy(id = "com.monefy.app.lite:id/balance_container")
	private WebElement balancebutton;

	@FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"com.monefy.app.lite:id/piegraph\"]/android.widget.TextView[7]")
	private WebElement dropdowntoggle;

	@FindBy(xpath = "(//android.widget.TextView[@resource-id='com.monefy.app.lite:id/textViewTransactionAmount'])[2]")
	private WebElement selectTransaction;

	@FindBy(id = "com.monefy.app.lite:id/delete")
	private WebElement deleteButton;

	// navigate button
	@FindBy(xpath = "//android.widget.ImageButton[@content-desc=\"Navigate up\"]")
	private WebElement navigateUp;



	public AddTaskPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void addTaskTitle(String title, Logger log) {
		addTaskTitle.sendKeys(title);
		
		log.info(String.format("Added task title as %s", title));
	}
	
	public void addTaskDescription(String description, Logger log) {
		addTaskDescription.sendKeys(description);
		
		log.info(String.format("Added task description as %s", description));
	}
	
	public void clickSaveTask(Logger log) {
		saveTaskButton.click();
		
		log.info("Task is saved");
	}

}
