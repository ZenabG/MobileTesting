package appium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

import java.io.File;
import java.net.MalformedURLException;

import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.remote.http.ClientConfig;
import org.openqa.selenium.remote.service.DriverService;
import java.net.URL;
import java.time.Duration;
import java.util.logging.Logger;

import static io.appium.java_client.service.local.flags.GeneralServerFlag.BASEPATH;

public class AppiumSetUp {
	private static final ThreadLocal<AppiumDriver> driver = new ThreadLocal<> ();
//	private static final Logger log = LoggerFactory.getLogger ("DriverManager.class");
	private static AppiumDriverLocalService service;
	private static final Dotenv dotenv = Dotenv.load();

	public static void createAndroidDriver () {
		startServer ();
		UiAutomator2Options options = new UiAutomator2Options();

		// Set Appium desired capabilities
		options.setCapability(MobileCapabilityType.PLATFORM_NAME, dotenv.get("PLATFORM_NAME"));
		options.setCapability(MobileCapabilityType.PLATFORM_VERSION, dotenv.get("PLATFORM_VERSION"));
		options.setCapability(MobileCapabilityType.DEVICE_NAME, dotenv.get("DEVICE_NAME"));
		options.setCapability("appPackage", AppiumConstants.APP_PACKAGE);
		options.setCapability("appActivity", AppiumConstants.APP_ACTIVITY);
		options.setCapability("noReset", "false");

		driver = new AndroidDriver(service.getUrl(), options);
		setupDriverTimeouts();
	}

	protected static void startServer() {
		// Start Appium server locally
		AppiumServiceBuilder builder = new AppiumServiceBuilder();
		builder.withIPAddress("127.0.0.1").usingAnyFreePort().withAppiumJS(new File(dotenv.get("APPIUM_JS_PATH"))).usingDriverExecutable(new File(dotenv.get("NODE_JS_EXE_PATH"))).withArgument(BASEPATH, "/wd/hub").withArgument(GeneralServerFlag.SESSION_OVERRIDE).withArgument(GeneralServerFlag.LOG_LEVEL, "debug").withLogFile(new File(System.getProperty("user.dir") + "/Appium_Server_Logs/appium_server_logs"));

		service = AppiumDriverLocalService.buildService(builder);
		service.start();

		System.out.println("Appium server running: " + service.isRunning());
		System.out.println("Appium URL: " + service.getUrl().toString());

	}

	private static void setupDriverTimeouts () {
		driver.get().manage ()
				.timeouts ()
				.implicitlyWait (Duration.ofSeconds (30));
	}

	protected void killAppiumServer() {
		if (null != driver.get()) {
			log.info ("Closing the driver...");
			driver.get().quit ();
			driver.remove ();
			service.stop();
		}
	}
}