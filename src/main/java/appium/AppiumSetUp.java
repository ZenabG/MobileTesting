package appium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import java.net.MalformedURLException;
import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.remote.http.ClientConfig;
import org.openqa.selenium.remote.service.DriverService;
import java.net.URL;
import java.time.Duration;

public class AppiumSetUp {
	protected AppiumDriver driver;
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

		// Start Appium Service
		service = AppiumDriverLocalService.buildService(builder);
		service.start();

		System.out.println("Appium server running: " + service.isRunning());
		System.out.println("Appium URL: " + service.getUrl().toString());

		// Create ClientConfig for Selenium/Appium 9+ compatibility
		ClientConfig clientConfig = ClientConfig.defaultConfig()
				.baseUrl(service.getUrl())
				.readTimeout(Duration.ofSeconds(60));

		// Initialize AndroidDriver with UiAutomator2Options
		driver = new AndroidDriver<>(service.getUrl(), options);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	protected void killAppiumServer() {
		service.stop();
	}

}