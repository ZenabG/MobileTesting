# Monefy mobile automation project

## Overview

This is a mobile automation project for the Monefy app. The Monefy app is a personal finance management app that helps users to track their expenses and income. The app is available on both Android and iOS platforms.

The project is created using Appium with Java and TestNG frameworks. The project is designed to test the functionality of the Monefy app on Android devices.

The tests are containerized using \*Docker\* in GitHub Actions to ensure a consistent and isolated environment for test execution.

## Tools and technologies used

* **Appium** : Open-source test automation tool for mobile applications
* **Java** : Programming language used for writing the test scripts
* **Docker** : Used within GitHub Actions to run tests in a consistent and isolated environment
* **AppiumReporterPlugin** : Custom plugin used for generating test reports
* **Github Actions** : CI/CD tool used for automating the build and test process
* **TestNG** : Testing framework used for executing the tests
* **Maven** : Build automation tool used for managing dependencies and building the project
* **Git** : Version control system used for managing the project code

## Project structure

* **src/main/java/com/monefy** : Consists of 2 packages and 1 class -

    1. **appium :** It has 2 Java classes defined below
        * **AppiumConstants** : Java constants class with app package name, app activity name
        * **AppiumSetUp** : Appium setup to set desired capabilities and create android driver, start the Appium server programmatically and stop the server when the test is completed

    2. **pages :** It consists of 4 Java classes defined below
        * **HomePage** : Page class with locators and methods to interact with the home page of the Monefy app
        * **EnterAmountPage** : Page class with locators and methods to interact with the enter amount page of the Monefy app
        * **ChooseCategoryPage** : Page class with locators and methods to interact with the choose category page of the Monefy app
        * **OffersPage** : Page class with locators and methods to interact with the offers page of the Monefy app
    3. **PageOperations** : Class with methods to perform custom operations on the Monefy app using page class methods

* **src/test/java/com/monefy** : Consists of 2 packages -

    1. **e2eTests :** It consists of TestNG class with the Monefy app UI tests.

        * **TestTransaction** : TestNG class with methods to test the functionality of the Monefy app.
            * **testAddIncome** : Test to validate add income in the Monefy app
            * **testUpdateBalanceByAddingExpense** : Test to validate update balance by adding an expense in the Monefy app

    2. **testReport :** It consists of setup for creating and generating custom test reports.

        * **ReporterPluginSetUp** : Class to set test information and generate test reports.

* **Appium_server_logs** : This folder has the Appium server logs file generated after each local test run.


## Functional Tests
* The functional tests for the Monefy app are located in the com.monefy.e2eTests package. These tests are designed to validate the core functionalities of the Monefy app, such as adding income and updating the balance by adding expenses.
    * Test Classes
        * **TestTransaction:** This class contains the following test methods:
            * **testAddIncome:** Validates that the user can add income to the Monefy app and that the balance is updated correctly.
            * **testUpdateBalanceByAddingExpense:** Validates that the user can add an expense to the Monefy app and that the balance is updated correctly.

## How to run tests locally
### Pre-requisite :
* Node.js installed and set in \*Path\* environment variable (Version 23.9.0 was used with this project)
* Appium installed using npm (\`npm install -g appium\`) (Version 2.16.2 was used with this project)
* Android SDK installed. ANDROID_HOME and ANDROID_SDK_ROOT set in environment variable
* An IDE (Eclipse or IntelliJ IDEA)
* Java 17 installed and JAVA_HOME set in environment variable
* Maven installed and MAVEN_HOME set in environment variable
* Git installed

### Steps to download the project in IDE:
1. Open command prompt and go to the path where the project is to be downloaded
2. Run \`git clone <url>\`. Get the URL from the project path
3. Open IDE and go to File > Open and choose the git cloned project.

### Steps to run the tests locally using Maven:
1. Copy \`.env.template\` file from root directory and copy it to bash_profile or zshrc file and update the values. These are env variables required to run the tests.
2. Run the command \`mvn clean test\` in the root directory of the project to run the tests.
3. The test results are displayed in the console and the custom test reports are generated in the root directory with name \`report.html\`.

## How to run tests using CI/CD
* The project is integrated with **Github Actions** for CI/CD.
* The workflow file is present in the \`.github/workflows\` directory and is named \`e2eTests.yml\`.
* The workflow file is configured to run the tests on every push and pull request to the \`master\` branch.
* The workflow file is configured to generate the custom test reports and \**upload them as artifacts\**.

### Steps in the workflow
1. **name**: The name of the workflow.
2. **on**: Specifies the events that trigger the workflow. In this case, it is triggered on push and pull request events to the \`master\` branch.
3. **jobs**: Defines the jobs that will run as part of the workflow.
4. **build**: The name of the job.
5. **runs-on**: Specifies the type of runner to use. In this case, it uses \`ubuntu-latest\`.
6. **steps**: The steps to be executed in the job.
    - **Checkout repository**: Uses the \`actions/checkout@v3\` action to check out the repository code.
    - **Start Android 11 Emulator in Docker**: Runs a Docker container with an Android 11 emulator.
    - **Verify Emulator is Fully Booted**: Checks if the emulator is fully booted.
    - **Check Emulator Status**: Verifies that the emulator is running using \`adb devices\`.
    - **Get Emulator Device ID**: Retrieves the device ID of the emulator.
    - **Create Dockerfile for Appium Image with Reporter Plugin**: Creates a Dockerfile for a custom Appium image with the reporter plugin.
    - **Build Custom Appium Docker Image**: Builds the custom Appium Docker image.
    - **Start Appium Container with Reporter Plugin**: Starts the Appium container with the reporter plugin.
    - **Print Appium Server Logs**: Prints the Appium server logs.
    - **Push monefy apk file into appium container**: Copies the Monefy APK file from project path into the Appium container.
    - **Run Tests in a Docker Container**: Runs the tests in a Docker container.
    - **Upload Test Report**: Uploads the test report as an artifact.