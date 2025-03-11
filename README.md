# Monefy mobile automation project

## Overview

This is a mobile automation project for the Monefy app. The Monefy app is a personal finance management app that helps users to track their expenses and income. The app is available on both Android and iOS platforms.

The project is created using Appium with Java and TestNG frameworks. The project is designed to test the functionality of the Monefy app on Android devices.

## Tools and technologies used

* **Appium** : Open-source test automation tool for mobile applications
* **Java** : Programming language used for writing the test scripts
* **TestNG** : Testing framework used for executing the tests
* **Maven** : Build automation tool used for managing dependencies and building the project
* **Git** : Version control system used for managing the project code
* **Github Actions** : CI/CD tool used for automating the build and test process
* **AppiumReporterPlugin** : Custom plugin used for generating test reports

## Project structure

* **src/main/java/com/monefy** : Consists of 2 packages and 1 class -

    1. **appium :** It has 2 Java classes defined below
        * AppiumConstants : Java constants class with app package name, app activity name
        * AppiumSetUp : Appium setup to set desired capabilities and create android driver, start the Appium server programmatically and stop the server when the test is completed

    2. **pages :** It consists of 4 Java classes defined below
        * HomePage : Page class with locators and methods to interact with the home page of the Monefy app
        * EnterAmountPage : Page class with locators and methods to interact with the enter amount page of the Monefy app
        * ChooseCategoryPage : Page class with locators and methods to interact with the choose category page of the Monefy app
        * OffersPage : Page class with locators and methods to interact with the offers page of the Monefy app
    3. **PageOperations :** Class with methods to perform custom operations on the Monefy app using page class methods

* **src/test/java/com/monefy** : Consists of 2 packages -

    1. **e2eTests :** It consists of TestNG class with the Monefy app UI tests.

        * TestTransaction : TestNG class with methods to test the functionality of the Monefy app.
            * testAddIncome : Test to validate add income in the Monefy app
            * testUpdateBalanceByAddingExpense : Test to validate update balance by adding an expense in the Monefy app

    2. **testReport :** It consists of setup for creating and generating custom test reports.

        * ReporterPluginSetUp : Class to set test information and generate test reports.

* **Appium_server_logs :** This folder has the Appium server logs file generated after each local test run.

## Test reports
* Appium Reporter Plugin is used to generate custom test reports.
  * The reports are generated with the name `report.html`.

## How to run tests locally
### Pre-requisite :
* Node.js installed and set in *Path* environment variable (Version 23.9.0 was used with this project)
* Appium installed using npm (`npm install -g appium`) (Version 1.22.1 was used with this project)
* Android SDK installed and ANDROID_HOME set in environment variable (Windows) or bash profile (macOS)
* An IDE (Eclipse or IntelliJ IDEA)
* Java 17 installed and JAVA_HOME set in environment variable
* Maven 3.9.9 installed and MAVEN_HOME set in environment variable
* Git installed

### Steps to download the project in IDE:
1. Open command prompt and go to the path where the project is to be downloaded
2. Run `git clone <url>`. Get the URL from the project path https://github.com/ZenabG/ZooplusMobile/tree/master.
3. Open IDE and go to File > Open and choose the git cloned project.

### Steps to run the tests using TestNG:
1. Verify if the project is visible in the project panel.
2. Add TestNG to the IDE. If the project is in Eclipse, go to Help > Eclipse Marketplace > enter TestNG in search.
3. Go to `src/test/java/e2eTests/TestTransaction.java`
4. Right click and run as TestNG test. (Appium server is started programmatically through the Java code)
5. After test run the reports are generated in folder *test-output*

### Steps to run the project using Maven:
1. Right click on the project, Run as > Maven clean
2. After step 1 is successful, right click on the project, Run as > Maven test (Appium server is started programmatically through the Java code)
3. After test run the reports are generated on the path `target/surefire-reports/emailable-report.html`

### Setting up environment variables
To manage Appium desired capabilities and environment variables, create a `.env` file in the root directory of the project. You can use the `.env.template` file as a reference. Copy the `.env.template` file to `.env` and fill in the actual values.

#### Example of `.env.template`:
```dotenv
# .env.template

PLATFORM_NAME=your_platform_name
PLATFORM_VERSION=your_platform_version
DEVICE_NAME=your_device_name
NODE_JS_EXE_PATH=your_node_js_exe_path
APPIUM_JS_PATH=your_appium_js_path
```

## How to run tests using CI/CD
Download and install Jenkins war file from https://www.jenkins.io/
Open command prompt and go to the path where Jenkins is installed on the device
Run the command java -jar jenkins.war. This starts Jenkins server locally on the machine.
Go to browser and open localhost:8080. This will open the Jenkins start page where it will ask for admin password.
In Windows open C:/Users/<username>/.jenkins/secret. Paste the key from this file to admin password tab. Click next.
On next page, set a new user for Jenkins or just continue as admin.
Jenkins is started. Create a new Maven job to run the project from GitHub and check the build for results. (Added screenshots of the successful build to the folder Jenkins reports under the git master branch)
