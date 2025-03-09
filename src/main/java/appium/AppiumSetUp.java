package appium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import io.github.cdimascio.dotenv.Dotenv;

public class BaseTest {
	protected AppiumDriver driver;
	protected WebDriverWait wait;
	protected AppiumDriverLocalService service;

	private static final Dotenv dotenv = Dotenv.load();

	protected void initialiseAppium() throws MalformedURLException {
		// Start Appium server locally
		service = AppiumDriverLocalService.buildDefaultService();
		service.start();

		// Set UiAutomator2 options
		UiAutomator2Options options = new UiAutomator2Options()
				.setPlatformName(dotenv.get("PLATFORM_NAME"))
				.setDeviceName(dotenv.get("DEVICE_NAME"))
				.setAppPackage(AppiumConstants.APP_PACKAGE)
				.setAppActivity(AppiumConstants.APP_ACTIVITY) // Change this
				.setNoReset(false);

		// Use AppiumDriver (no more AndroidDriver in Appium 9+)
		driver = new AppiumDriver(service.getUrl(), options);

		// Set implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	protected void tearDown() {
		if (driver != null) {
			driver.quit();
		}
		if (service != null) {
			service.stop();
		}
	}
}