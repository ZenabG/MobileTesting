package e2eTests;

import static org.testng.Assert.assertTrue;

import java.net.MalformedURLException;
import java.util.logging.Logger;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import appium.AppiumSetUp;
import pages.AddTaskPage;
import pages.MenuPage;
import pages.StatisticsPage;
import pages.TaskHomePage;

public class TestTaskList extends AppiumSetUp {

	private TaskHomePage taskPage;
	private AddTaskPage addTaskPage;
	private StatisticsPage statisticsPage;
	private MenuPage menuPage; 
	
	private Logger log;

	@BeforeSuite
	public void startAppiumServer() throws MalformedURLException {
		initialiseAppium();
		
		log = Logger.getLogger("global");
	}

	@BeforeTest
	public void beforeTestMethod() {
//		taskPage = new TaskHomePage(driver);
//		addTaskPage = new AddTaskPage(driver);
//		statisticsPage = new StatisticsPage(driver);
//		menuPage = new MenuPage(driver);
	}

	@Test
	public void verifyToDoTaskListFeature() throws MalformedURLException, InterruptedException {

//		// Create two tasks in the TODO list
//		taskPage.clickAddToDoTask(log);
//
//		// Add 2 tasks
//		for (int i = 0; i < 2; i++) {
//			taskPage.clickAddToDoTask(log);
//			addTaskPage.addTaskTitle("task " + i, log);
//			addTaskPage.addTaskDescription("task description " + i, log);
//			addTaskPage.clickSaveTask(log);
//		}
//
//		// Mark one task as completed
//		taskPage.clickTaskCompletedCheckbox(0, log);
//
//		// Verify the statistics for Active Tasks is 50% and Completed Tasks is 50%
//		taskPage.clickHamburgerMenu(log);
//		menuPage.goToStatisticsTab(log);
//		assertTrue(statisticsPage.getActiveTasksStatictics(log).contains("50.0%"));
//		assertTrue(statisticsPage.getCompletedTasksStatistics(log).contains("50.0%"));
//
//		// Clear completed task in TODO list and verify completed task is no longer
//		// listed
//		taskPage.clickHamburgerMenu(log);
//		menuPage.goToTasksListTab(log);
//		assertTrue(taskPage.verifyCompletedTaskIsPresent(0), "Completed tasks are not present");
//		taskPage.clickThreeDotMenu(log);
//		taskPage.clickClearCompletedLink(log);
//		assertTrue(taskPage.verifyCompletedTaskIsNotPresent(0), "Completed tasks are present");
	}

	@AfterSuite
	public void stopAppiumServer() {
		killAppiumServer();
	}
}
