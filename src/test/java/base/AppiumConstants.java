package base;

public class AppiumConstants {
	
	//Appium capabilities of an android device
	public static final String PLATFORM_NAME = "android"; 
	public static final String PLATFORM_VERSION = "14";
	public static final String DEVICE_NAME = "OnePlus";

	
	//Appium capabilities appPackage and appActivity for ToDo app from https://github.com/android/architecture-samples
	public static final String APP_PACKAGE = "com.monefy.app.lite";
	public static final String APP_ACTIVITY = "com.monefy.activities.main.MainActivity_t4575";
	
	//NodeJs executable path and AppiumJS path. This path is for windows machine, please update it if you are using mac.
	public static final String NODE_JS_EXE_PATH = "/usr/local/bin/node";
	public static final String APPIUM_JS_PATH = "/usr/local/lib/node_modules/appium/build/lib/main.js";
	
	
	

}
