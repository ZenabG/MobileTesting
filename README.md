# Monefy mobile automation project

## Overview

This is a maven project created using **Appium Framework with Java**.

The project follows **Page object model** mechanism with **Page Factory**. **TestNG framework** is used for executing the tests.

## Project hierarchy

* **src/main/java** : Consists of 2 packages -

    1. **base :** It has 2 java classes defined below
        * AppiumConstants : Java constants class with app package name, app activity name
        * AppiumSetUp : Appium setup to set desired capabilities, start the appium server programmatically and stop the server when test is completed

    2. **pages :** It consists of 4 java classes defined below

        * AddTaskPage : Mobile locators and page methods for adding a new task page.
        * MenuPage : Mobile locators and page methods for hamburger menu page
        * StatisticsPage : Mobile locators and page methods for statistics of active and completed tasks page.
        * TaskHomePage : Mobile locators and page methods for home page of the TODO app

* **src/test/java** : Consists of 1 package -

    1. **e2eTests :** It consists of TestNG class with the TODO app UI test.

        * TestTaskList : TestNG class with 3 test methods to test the functionality of the TODO app. 
            * testAddTask : Test to add a new task in the TODO app
            * testCompleteTask : Test to complete a task in the TODO app
            * testDeleteTask : Test to delete a task in the TODO app
          
* **Appium_server_logs :** This folder has the appium server logs file generated after each test run

## Test reports 
  **Maven reports :** Path in the project `/target/surefire-reports/emailable-report.html`
    These reports are generated as part of maven test run and provides good details of each test execution. 

## How to run tests locally
### Pre-requisite :
  * Node JS installed and set in *Path* environment variable (Version 23.9.0 was used with this project)
  * Appium installed using npm (`npm install -g appium`) (Version 1.22.1 was used with this project)
  * Android SDK installed and ANDROID_HOME set in environment variable (windows) or bash profile (mac)
  * An IDE (Eclipse or intelliJ)
  * Java 17 installed and JAVA_HOME set in environment variable 
  * Maven 3.9.9 installed and MAVEN_HOME set in environment variable 
  * Git installed 
    
### Steps to download the project in IDE: 
   1. Open command prompt and go to the path where the project is to be downloaded
   2. Run `git clone <url>`. Get the url from the project path https://github.com/ZenabG/ZooplusMobile/tree/master. 
   3. Open IDE and go to File > Open and choose the git cloned project.

### Steps to run the tests using TestNG:
   1. Verify if the project is visible in project panel.
   2. Add TestNG to the IDE. If the project is in eclipse, go to Help > Eclipse Marketplace > enter TestNG in search. 
   3. Go to src/test/java/e2eTests/TestTaskList.java 
   4. Right click and run as TestNG test. (Appium server is started programmatically through the java code)
   5. After test run the reports are generated in folder *test-output*

### Steps to run the project using Maven:
   1. Right click on the project, Run as > maven clean
   2. After step 1 is successful, right click on the project, Run as > maven test (Appium server is started programmatically through the java code)
   3. After test run the reports are generated on the path target > surefire-reports > emailable-report.html 

### Setting up environment variables
To manage appium desired capabilities and environment variables, create a `.env` file in the root directory of the project. You can use the `.env.template` file as a reference. Copy the `.env.template` file to `.env` and fill in the actual values.

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
  1. Download and install Jenkins war file from https://www.jenkins.io/
  2. Open command prompt and go to the path where jenkins is installed on the device
  3. Run the command `java -jar jenkins.war`. This starts jenkins server locally on the machine.
  4. Go to browser and open `localhost:8080`. This will open the jenkins start page where it will ask for admin password. 
  5. In windows open "C:/Users/<username>/.jenkins/secret". Paste the key from this file to admin password tab. Click next.
  6. On next page, set a new user for jenkins or just continue as admin.
  7. Jenkins is started. Create a new maven job to run the project from github and check the build for results. 
    (Added screenshots of the successful build to the folder `Jenkins reports` under the git master branch)
   
