package appium;

import java.io.File;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.github.cdimascio.dotenv.Dotenv;

public class AppiumSetUp {

	protected AndroidDriver driver;  // Use AndroidDriver for Android
	protected WebDriverWait wait;
	private AppiumServiceBuilder builder;
	private AppiumDriverLocalService service;
	private static final Dotenv dotenv = Dotenv.load();

	protected void initialiseAppium() throws MalformedURLException {

		DesiredCapabilities cap = new DesiredCapabilities();

		// Set Appium desired capabilities
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, dotenv.get("PLATFORM_NAME"));
		cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, dotenv.get("PLATFORM_VERSION"));
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, dotenv.get("DEVICE_NAME"));
		cap.setCapability("appPackage", AppiumConstants.APP_PACKAGE);
		cap.setCapability("appActivity", AppiumConstants.APP_ACTIVITY);
		cap.setCapability("noReset", "false");

		// Define AppiumServiceBuilder with Appium IP, port, and paths
		builder = new AppiumServiceBuilder();
		builder.usingDriverExecutable(new File(dotenv.get("NODE_JS_EXE_PATH")));
		builder.withAppiumJS(new File(dotenv.get("APPIUM_JS_PATH")));
		builder.withIPAddress("0.0.0.0");
		builder.usingAnyFreePort();
		builder.withCapabilities(cap);
		builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
		builder.withArgument(GeneralServerFlag.LOG_LEVEL, "debug");
		builder.withLogFile(new File(System.getProperty("user.dir") + "/Appium_Server_Logs/appium_server_logs"));

		// Start the Appium server
		try {
			service = AppiumDriverLocalService.buildService(builder);
			service.start();
			service.clearOutPutStreams();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		System.out.println("Appium server running : " + service.isRunning());
		System.out.println("Appium URL " + service.getUrl().toString());

		// Initialize driver using AndroidDriver
		driver = new AndroidDriver(service.getUrl(), cap);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	public void killAppiumServer() {
		service.stop();
	}
}