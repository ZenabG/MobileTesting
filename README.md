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
  * The reports are generated at the root with name `report.html`.

## How to run tests locally
### Pre-requisite :
* Node.js installed and set in *Path* environment variable (Version 23.9.0 was used with this project)
* Appium installed using npm (`npm install -g appium`) (Version 1.22.1 was used with this project)
* Appium Reporter Plugin installed using `appium plugin install --source=npm appium-reporter-plugin`
* Android SDK installed. ANDROID_HOME and ANDROID_SDK_ROOT set in environment variable 
* An IDE (Eclipse or IntelliJ IDEA)
* Java 17 installed and JAVA_HOME set in environment variable
* Maven 3.9.9 installed and MAVEN_HOME set in environment variable
* Git installed

### Steps to download the project in IDE:
1. Open command prompt and go to the path where the project is to be downloaded
2. Run `git clone <url>`. Get the URL from the project path 
3. Open IDE and go to File > Open and choose the git cloned project.

### Steps to run the tests locally using Maven:
1. Make a copy of `.env.template` file and rename it to `.env` in the root directory of the project. Fill in the actual values for the environment variables.
2. Run the command `mvn clean test` in the root directory of the project to run the tests.
3. The test results are displayed in the console and the custom test reports are generated in the root directory with name `report.html`.

## How to run tests using CI/CD
* The project is integrated with Github Actions for CI/CD.
* The workflow file is present in the `.github/workflows` directory.
* The workflow file is configured to run the tests on every push and pull request to the `master` branch.
* The workflow file is configured to run the tests on an Android emulator 