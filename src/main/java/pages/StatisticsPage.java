package pages;

import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StatisticsPage {
	
	@FindBy(id = "com.example.android.architecture.blueprints.master.mock:id/stats_active_text")
	private WebElement activeTasksText;
	
	@FindBy(id = "com.example.android.architecture.blueprints.master.mock:id/stats_completed_text")
	private WebElement completedTasksText;
	
	public StatisticsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public String getActiveTasksStatictics(Logger log) {
		
		log.info(String.format("Active tasks statistics is :", activeTasksText.getText()));
		return activeTasksText.getText();
	}
	
	public String getCompletedTasksStatistics(Logger log) {
		log.info(String.format("Completed tasks statistics is :", completedTasksText.getText()));
		return completedTasksText.getText();
	}

}
