package pages;

import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddTaskPage {
	
	@FindBy(id = "com.example.android.architecture.blueprints.master.mock:id/add_task_title_edit_text")
	private WebElement addTaskTitle;
	
	@FindBy(id = "com.example.android.architecture.blueprints.master.mock:id/add_task_description_edit_text")
	private WebElement addTaskDescription;
	
	@FindBy(id = "com.example.android.architecture.blueprints.master.mock:id/save_task_fab")
	private WebElement saveTaskButton;
	
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
