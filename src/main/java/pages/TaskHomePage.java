package pages;

import java.util.List;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class TaskHomePage {

	@FindBy(id = "com.example.android.architecture.blueprints.master.mock:id/add_task_fab")
	private WebElement addTaskButton;

	@FindBy(id = "com.example.android.architecture.blueprints.master.mock:id/complete_checkbox")
	private List<WebElement> checkBoxCompleteTask;

	@FindBy(xpath = "//android.widget.ImageButton[@content-desc='Open navigation drawer']")
	private WebElement hamburgerMenuButton;

	@FindBy(xpath = "//android.widget.ImageView[@content-desc='More options']")
	private WebElement threeDotMenu;

	@FindBy(id = "com.example.android.architecture.blueprints.master.mock:id/title")
	private WebElement clearCompletedLink;

	public TaskHomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void clickAddToDoTask(Logger log) {
		addTaskButton.click();
		
		log.info("Clicked add task button");
	}

	public void clickTaskCompletedCheckbox(int taskNumber, Logger log) {
		checkBoxCompleteTask.get(taskNumber).click();
		
		log.info(String.format("Task %s is marked as complete", taskNumber));
	}

	public void clickHamburgerMenu(Logger log) {
		hamburgerMenuButton.click();
		
		log.info("Hamburger menu clicked");
	}

	public void clickThreeDotMenu(Logger log) {
		threeDotMenu.click();
		
		log.info("Clicked 3 dot menu");
	}

	public void clickClearCompletedLink(Logger log) {
		clearCompletedLink.click();
		
		log.info("Clicked clear completed tasks link");
	}

	public boolean verifyCompletedTaskIsPresent(int taskCompletedNumber) {
		return checkBoxCompleteTask.get(taskCompletedNumber).getAttribute("checked").equals("true");
	}

	public boolean verifyCompletedTaskIsNotPresent(int taskNotCompletedNumber) {
		return checkBoxCompleteTask.get(taskNotCompletedNumber).getAttribute("checked").equals("false");
	}
}
