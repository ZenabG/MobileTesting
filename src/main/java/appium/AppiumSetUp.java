package appium;

import io.appium.java_client.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import java.net.MalformedURLException;
import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.remote.http.ClientConfig;
import org.openqa.selenium.remote.service.DriverService;
import java.net.URL;
import java.time.Duration;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.BASEPATH;

public class AppiumSetUp {
	private static final ThreadLocal<AppiumDriver> driver = new ThreadLocal<> ();
	private static final Logger log = LogManager.getLogger ("DriverManager.class");
	private static AppiumDriverLocalService service;
	private static final Dotenv dotenv = Dotenv.load();

	public static void createAndroidDriver () {
		startServer ();
		driver.set (new AndroidDriver (service.getUrl (), setOptions ()));
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

private void UiAutomator2Options setOptions () {
		// Set UiAutomator2 options
		return new UiAutomator2Options()
				.setPlatformName(dotenv.get("PLATFORM_NAME"))
				.setDeviceName(dotenv.get("DEVICE_NAME"))
				.setAppPackage(AppiumConstants.APP_PACKAGE)
				.setAppActivity(AppiumConstants.APP_ACTIVITY) // Change this
				.setNoReset(false);

	}

	public static <D extends AppiumDriver> D getDriver () {
		return (D) DriverManager.DRIVER.get ();
	}

	private static <D extends AppiumDriver> void setDriver (final D driver) {
		DriverManager.DRIVER.set (driver);
	}

	private static void setupDriverTimeouts () {
		getDriver ().manage ()
				.timeouts ()
				.implicitlyWait (Duration.ofSeconds (30));
	}

	protected void killAppiumServer() {
		if (null != driver.get ()) {
			log.info ("Closing the driver...");
			getDriver().quit ();
			driver.remove ();
			service.stop();
		}
	}

}