package com.monefy.appium;

import com.monefy.PageOperations;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.github.cdimascio.dotenv.Dotenv;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.BASEPATH;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class AppiumSetUp {

	protected static AndroidDriver driver;
	protected static WebDriverWait wait;
	private static AppiumDriverLocalService service;
	private static final Logger log = LoggerFactory.getLogger(AppiumSetUp.class);
	private static final Dotenv dotenv = Dotenv.load();

	public static String startAppium() {
		String ciEnv = dotenv.get("CI");

		if (ciEnv.equalsIgnoreCase("true")) {
			log.info("Using Appium server in Docker (CI/CD mode).");
			return "http://127.0.0.1:4723/";
		} else {
			log.info("Starting local Appium server...");
            return startLocalAppiumServer();
		}
	}

	public static void createAndroidDriver(String appiumUrl) throws MalformedURLException {
		UiAutomator2Options options = setAppiumCapabiliies();
        driver = new AndroidDriver(new URL(appiumUrl), options);
		setupDriverTimeouts(driver);
	}

	protected static String startLocalAppiumServer() {
		// Start Appium server locally
		AppiumServiceBuilder builder = new AppiumServiceBuilder();
		builder.withIPAddress("127.0.0.1").usingAnyFreePort().withAppiumJS(new File(dotenv.get("APPIUM_JS_PATH"))).usingDriverExecutable(new File(dotenv.get("NODE_JS_EXE_PATH"))).withArgument(BASEPATH, "/wd/hub").withArgument(GeneralServerFlag.SESSION_OVERRIDE).withArgument(GeneralServerFlag.LOG_LEVEL, "debug").withArgument(() -> "--use-plugins", "appium-reporter-plugin").withLogFile(new File(System.getProperty("user.dir") + "/Appium_Server_Logs/appium_server_logs"));

		service = AppiumDriverLocalService.buildService(builder);
		service.start();

		log.info("Appium server running: " + service.isRunning());
		log.info("Appium URL: " + service.getUrl().toString());

		return service.getUrl().toString();

	}

	protected void killAppiumServer() {
		String ciEnv = System.getenv().get("CI");

		if (ciEnv.equalsIgnoreCase("true")) {
			log.info("Appium server in Docker (CI/CD mode) will be stopped automatically.");
		} else {
			log.info("Stopping local Appium server...");
			service.stop();
		}
	}

	private static UiAutomator2Options setAppiumCapabiliies() {
		UiAutomator2Options options = new UiAutomator2Options();
		// Set Appium desired capabilities
		options.setCapability(MobileCapabilityType.PLATFORM_NAME, dotenv.get("PLATFORM_NAME"));
		options.setCapability(MobileCapabilityType.PLATFORM_VERSION, dotenv.get("PLATFORM_VERSION"));
		options.setCapability(MobileCapabilityType.DEVICE_NAME, dotenv.get("DEVICE_NAME"));
		options.setCapability(MobileCapabilityType.UDID, dotenv.get("UDID"));
		options.setCapability("app", dotenv.get("APP_PATH"));
		options.setCapability("appPackage", AppiumConstants.MONEFY_APP_PACKAGE);
		options.setCapability("appActivity", AppiumConstants.MONEFY_APP_ACTIVITY);
		options.setCapability("noReset", "false");
		options.setCapability("appium:uiautomator2ServerInstallTimeout", 30000);

		return options;
	}

	private static void setupDriverTimeouts (AndroidDriver driver) {
		driver.manage ()
				.timeouts ()
				.implicitlyWait (Duration.ofSeconds (30));

		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
}