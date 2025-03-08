package base;

public class AppiumConstants {
	
	//Appium capabilities of an android device
	public static final String PLATFORM_NAME = "android"; 
	public static final String PLATFORM_VERSION = "10.0.1";
	public static final String DEVICE_NAME = "OnePlus";
	
	//Appium capabilities appPackage and appActivity for ToDo app from https://github.com/android/architecture-samples
	public static final String APP_PACKAGE = "com.example.android.architecture.blueprints.master.mock";
	public static final String APP_ACTIVITY = "com.example.android.architecture.blueprints.todoapp.tasks.TasksActivity";
	
	//NodeJs executable path and AppiumJS path. This path is for windows machine, please update it if you are using mac.
	public static final String NODE_JS_EXE_PATH = "/usr/local/bin/node";
	public static final String APPIUM_JS_PATH = System.getProperty("user.home")+"/AppData/Roaming/npm/node_modules/appium/build/lib/main.js";
	
	
	

}
