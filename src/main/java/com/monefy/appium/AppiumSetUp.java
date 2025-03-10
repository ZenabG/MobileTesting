package com.monefy.appium;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.BASEPATH;
import io.github.cdimascio.dotenv.Dotenv;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.logging.Logger;

public class AppiumSetUp {
	protected static AndroidDriver driver;
	protected Logger log;
	private static AppiumDriverLocalService service;
	private static final Dotenv dotenv = Dotenv.load();

	public static void createAndroidDriver() throws MalformedURLException {
		UiAutomator2Options options = new UiAutomator2Options();
		// Set Appium desired capabilities
		options.setCapability(MobileCapabilityType.PLATFORM_NAME, System.getenv().get("PLATFORM_NAME"));
		options.setCapability(MobileCapabilityType.PLATFORM_VERSION, System.getenv().get("PLATFORM_VERSION"));
		options.setCapability(MobileCapabilityType.DEVICE_NAME, System.getenv().get("DEVICE_NAME"));
		options.setCapability(MobileCapabilityType.UDID, System.getenv().get("UDID"));
		options.setCapability("app", System.getenv().get("APP_PATH"));
		options.setCapability("appPackage", AppiumConstants.APP_PACKAGE);
		options.setCapability("appActivity", AppiumConstants.APP_ACTIVITY);
		options.setCapability("noReset", "false");
		options.setCapability("appium:uiautomator2ServerInstallTimeout", 40000);
		String appiumUrl = getAppiumServerUrl();
		driver = new AndroidDriver(new URL(appiumUrl), options);
		setupDriverTimeouts();
	}

	private static String getAppiumServerUrl() {
		String ciEnv = System.getenv().get("CI");
		// Check if CI is set to true
		if ("true".equalsIgnoreCase(ciEnv)) {
			System.out.println("Using Appium server in Docker (CI/CD mode).");
			return "http://127.0.0.1:4723/"; // Use Docker Appium in CI
		} else {
			System.out.println("Starting local Appium server...");
			startLocalServer();  // Start Appium locally for local dev
			return service.getUrl().toString();
		}
	}

	protected static void startLocalServer() {
		// Start Appium server locally
		AppiumServiceBuilder builder = new AppiumServiceBuilder();
		builder.withIPAddress("127.0.0.1").usingAnyFreePort().withAppiumJS(new File(dotenv.get("APPIUM_JS_PATH"))).usingDriverExecutable(new File(dotenv.get("NODE_JS_EXE_PATH"))).withArgument(BASEPATH, "/wd/hub").withArgument(GeneralServerFlag.SESSION_OVERRIDE).withArgument(GeneralServerFlag.LOG_LEVEL, "debug").withLogFile(new File(System.getProperty("user.dir") + "/Appium_Server_Logs/appium_server_logs"));

		service = AppiumDriverLocalService.buildService(builder);
		service.start();

		System.out.println("Appium server running: " + service.isRunning());
		System.out.println("Appium URL: " + service.getUrl().toString());

	}

	private static void setupDriverTimeouts () {
		driver.manage ()
				.timeouts ()
				.implicitlyWait (Duration.ofSeconds (30));
	}

	protected void killAppiumServer() {
		String ciEnv = System.getenv().get("CI");
		// Check if CI is set to true

		if (driver != null) {
			driver.quit ();
		}

		if ("true".equalsIgnoreCase(ciEnv)) {
			System.out.println("Appium server in Docker (CI/CD mode) will be stopped automatically.");
		} else {
			System.out.println("Stopping local Appium server...");
			service.stop();
		}
	}
}