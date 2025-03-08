# Test Plan: Monefy App (Exploratory Testing & Test Automation)

## 1. Introduction
This test plan covers the exploratory testing of the Monefy mobile application (Android platform), followed by automated test development for the top 3 user flows. The tests will focus on functional, usability, and security aspects of the app while using the tech stack provided in the assignment.

## 2. Test Objectives
- **Exploratory Testing**: Identify issues related to core functionality, UI/UX, and security/privacy.
- **Test Automation**: Develop automated tests for the top 3 user flows based on exploratory testing findings using a scalable framework.

## 3. Scope
### In Scope:
- Exploratory testing of Monefy mobile app functionality and UI/UX
- Automated tests for the selected top 3 user flows
- Integration of tech stack including Appium, JUnit, Docker, and other tools used in N26's environment

### Out of Scope:
- Exploratory testing of iOS version (Android version tested)
- Detailed analysis of app features not critical to user flow
- Postman suite for REST API testing (focus on framework and best coding practices)

## 4. Test Strategy

### 4.1 Exploratory Testing
**Exploratory Testing Charters**:
1. **Charter 1**: Test basic functionality (adding, editing, deleting transactions).
2. **Charter 2**: Assess UI and usability (navigation, icons, touch responsiveness).
3. **Charter 3**: Evaluate security and privacy (password protection, data encryption).

**Test Execution Time**: Approximately 2 hours for testing and documentation

### 4.2 Test Automation
**Test Automation Framework**:
- The framework will focus on end-to-end (E2E) testing for the top 3 selected user flows.
- **Tech Stack**:
	- **Appium**: For mobile test automation
	- **JUnit**: For test execution and reporting
	- **Docker**: For containerization
	- **Gradle**: For build and dependency management
	- **Maven**: For dependency management

**Top 3 Selected User Flows for Automation**:
1. Adding a transaction
2. Editing a transaction
3. Deleting a transaction

### 4.3 REST API Automation (Bonus)
Test automation for a RESTful API will involve CRUD operations on selected endpoints (PET, STORE, or USER). The same tech stack principles apply for this task.

## 5. Evaluation Criteria
The testing effort will be evaluated based on:
- **Exploratory Testing**: Coverage of core app functionality, usability, and security issues.
- **Test Automation**: Maintainability, scalability, and effectiveness of the automation framework.
- **Tech Stack Proficiency**: Utilization of tools like Appium, JUnit, Docker, Gradle, Maven, etc.
- **Documentation**: Clarity of the README and test execution reports.

## 6. Test Deliverables
### 6.1 Exploratory Testing Deliverables
- A markdown file with:
	- Exploratory testing charters and goals
	- Findings from each charter
	- Prioritization of findings
	- Risk mitigation strategies

### 6.2 Test Automation Deliverables
- A folder with the automation framework:
	- **Test scripts** for the top 3 user flows (Appium + JUnit)
	- **Dockerized solution** (if applicable)
	- **Execution report** showing test results

### 6.3 README
A README file explaining:
- The setup process for running tests
- How to execute tests
- The approach used for choosing the tech stack

## 7. Test Execution Plan

### 7.1 Exploratory Testing
- Duration: 2 hours
- Method: Ad hoc testing using charters
- Tools: Monefy app on Android, Bug tracker for documenting issues
- Report: Markdown file summarizing findings, prioritization, and risks

### 7.2 Test Automation
- **Duration**: 4-6 hours (assuming Docker is used)
- **Tools**: Appium, JUnit, Gradle/Maven, Docker (optional)
- **Report**: Test execution report (JUnit test results, logs, screenshots)
- **Method**: Automated E2E tests covering the top 3 user flows (Adding, Editing, Deleting Transactions)

## 8. Test Execution

The test execution will be carried out following the exploratory test charters, followed by the automated tests for the selected user flows. The testing will be done on the latest available version of the Monefy Android app.

## 9. Risk Mitigation
- **Exploratory Testing**: Ensure thorough test coverage of all critical features and report bugs that impact the user experience.
- **Automation Testing**: Choose the most stable and critical user flows, ensuring they are scalable and maintainable for future updates.
- **Security Risks**: Implement strong data encryption and password protection as suggested in exploratory testing.

## 10. Conclusion
The test plan aims to comprehensively evaluate the Monefy mobile app, both through manual exploratory testing and automated end-to-end tests for critical user flows. The use of the recommended tech stack will ensure that the solution is maintainable, scalable, and aligned with N26's environment. We aim for thorough test coverage and efficient documentation to support development efforts.