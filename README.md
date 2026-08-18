# Marriott-IT

# Marriott UI Automation Framework

## Project Overview

The Marriott UI Automation Framework is an enterprise-grade Selenium automation framework built using Java 17, Maven, Selenium WebDriver, TestNG, and the Page Object Model (POM) design pattern. The primary objective of this framework is to provide a scalable, maintainable, and reusable automation solution for validating the Marriott web application across multiple environments.

The framework has been designed with separation of concerns in mind. Configuration management, browser initialization, page objects, test classes, and execution suites are organized into independent modules, making the framework easy to extend and maintain as the application grows.

One of the key design goals was to support both **local execution** and **CI/CD pipeline execution** without changing the source code. Environment-specific configuration is controlled entirely through Maven parameters and XML settings files.

---

# Framework Features

* Java 17
* Maven Multi-Module Project
* Selenium WebDriver
* TestNG
* Page Object Model (POM)
* PageFactory Design Pattern
* XML-Based Environment Configuration
* Chrome & Firefox Support
* Local and Pipeline Execution
* Cross-Environment Configuration
* Modular Framework Architecture
* Enterprise Folder Structure
* Easily Extendable for API Automation

---

# Framework Architecture

```text
automation
│
├── pom.xml
│
├── drivers
│   └── linux
│       ├── chromedriver
│       └── geckodriver
│
├── ui-tests
│   │
│   ├── settings
│   │   ├── local
│   │   │      marriott-dev-settings.xml
│   │   │
│   │   └── pipeline
│   │          marriott-dev-settings.xml
│   │
│   └── src
│       ├── main
│       │   └── java
│       │       └── com.marriott.ui
│       │           ├── base
│       │           ├── config
│       │           ├── driver
│       │           └── pages
│       │
│       └── test
│           ├── java
│           │   └── com.marriott.ui.tests
│           │       └── regressionTests
│           │
│           └── resources
│               └── marriottUIRegressionTests.xml
│
└── api-tests
```

---

# Framework Execution Flow

The framework executes in the following sequence:

```text
Maven Command

↓

Read Maven Parameters

↓

Load XML Configuration

↓

Initialize DriverFactory

↓

Launch Browser

↓

Navigate to Base URL

↓

Initialize Page Object

↓

Execute Test Method

↓

Perform Assertions

↓

Close Browser
```

---

# Technologies Used

* Java 17
* Selenium WebDriver
* TestNG
* Maven
* PageFactory
* XML Configuration
* ChromeDriver
* GeckoDriver

---

# Configuration

Environment configuration is stored under:

```text
ui-tests/settings
```

Local configuration:

```text
ui-tests/settings/local/marriott-dev-settings.xml
```

Pipeline configuration:

```text
ui-tests/settings/pipeline/marriott-dev-settings.xml
```

The framework dynamically loads the correct configuration using Maven parameters.

Example:

```bash
-Dmode=local
-Denvironment=marriott-dev
```

or

```bash
-Dmode=pipeline
-Denvironment=marriott-dev
```

No Java source code changes are required when switching environments.

---

# Running the Framework

Always execute Maven commands from the project root:

```text
automation
```

Verify location:

```bash
pwd
```

Expected:

```text
/home/amin/projects/Marriott-IT/automation
```

---

# Compile Project

```bash
mvn clean compile
```

---

# Build Project

```bash
mvn clean install
```

---

# Run UI Module Only

```bash
mvn clean install -pl ui-tests
```

---

# Execute a Single Test Class

```bash
mvn clean test -pl ui-tests -Dmode=local -Denvironment=marriott-dev -Dtest=DashboardTest
```

---

# Execute a Single Test Method

```bash
mvn clean test -pl ui-tests -Dmode=local -Denvironment=marriott-dev -Dtest=DashboardTest#verifyDashboardPageTitle
```

or

```bash
mvn clean test -pl ui-tests -Dmode=local -Denvironment=marriott-dev -Dtest=DashboardTest#verifyDashboardHeaderLogoIsDisplayed
```

---

# Execute Regression Suite

Suite Location:

```text
ui-tests/src/test/resources/marriottUIRegressionTests.xml
```

Command:

```bash
mvn clean test -pl ui-tests -Dmode=local -Denvironment=marriott-dev -DsuiteXmlFile=src/test/resources/marriottUIRegressionTests.xml
```

---

# Execute Using Pipeline Configuration

```bash
mvn clean test -pl ui-tests -Dmode=pipeline -Denvironment=marriott-dev -DsuiteXmlFile=src/test/resources/marriottUIRegressionTests.xml
```

---

# Page Object Model Guidelines

* One Java class represents one application page.
* Keep locators private.
* Expose only business methods.
* Never place assertions inside page classes.
* Keep test logic inside test classes.

Example page classes:

* MarriottBonvoyDashboard
* FindAHotelPage
* OffersPage
* SignInPage

---

# Test Class Guidelines

All regression tests should reside under:

```text
com.marriott.ui.tests.regressionTests
```

Each test class extends `BaseTest`, which automatically:

* Launches the browser.
* Navigates to the configured application URL.
* Executes the test.
* Closes the browser after execution.

Recommended TestNG annotation:

```java
@Test(
    priority = 1,
    groups = {"regression"},
    description = "Verify the Marriott Bonvoy Dashboard page title."
)
```

---

# Troubleshooting

### Settings file not found

Verify:

* The XML file exists.
* The file name matches the selected environment.
* Maven is executed from the `automation` directory.

---

### Driver file not found

Verify driver location:

```text
automation/drivers/linux/
```

Grant execute permission:

```bash
chmod +x drivers/linux/chromedriver
chmod +x drivers/linux/geckodriver
```

---

### Browser does not launch

Verify:

```bash
google-chrome --version
```

```bash
firefox --version
```

Ensure the driver version matches the installed browser version.

---

### TestNG annotations not found

Verify the TestNG dependency is available to framework classes that use TestNG annotations.

---

### XML Parsing Errors

Verify all required XML tags exist:

* environment
* browser
* baseUrl
* headless
* maximize
* implicitWait
* explicitWait
* pageLoadTimeout
* connectionTimeout
* readTimeout

---

### Compilation Errors

Compile first:

```bash
mvn clean compile
```

Then execute tests:

```bash
mvn clean test
```

---

# Interview Talking Points

This framework demonstrates several software engineering and test automation principles that are commonly discussed in senior QA Automation and SDET interviews:

* Designed a **multi-module Maven project** separating UI and API automation.
* Implemented the **Page Object Model (POM)** with PageFactory to improve maintainability and reduce code duplication.
* Built a reusable **DriverFactory** to centralize browser initialization and support multiple browsers.
* Externalized configuration into **environment-specific XML files**, allowing the same codebase to run in different environments through Maven parameters.
* Created a reusable **BaseTest** class to standardize browser setup and teardown for all test classes.
* Organized tests using **TestNG groups, priorities, and suite XML files** for flexible execution of regression, smoke, and future test suites.
* Structured the framework for easy integration with CI/CD pipelines by supporting command-line execution without source code changes.
* Kept page objects focused on business actions while maintaining assertions inside test classes, following separation of concerns.
* Designed the project to be extensible for future additions such as reporting, screenshots, retry mechanisms, logging, parallel execution, API automation, and cloud/grid execution.

---

# Future Enhancements

* BasePage utility methods
* Explicit wait utilities
* JavaScript helper methods
* Screenshot utility
* Logging framework
* Reporting (Extent Reports or Allure)
* Retry analyzer
* Parallel execution
* Docker support
* Selenium Grid
* Jenkins and GitHub Actions integration
* API automation integration
* Database validation utilities

---

# Author

**MD Noor A Amin**

Senior QA Automation Engineer

This framework was designed to demonstrate enterprise automation framework design principles while remaining scalable, maintainable, and easy to extend for future UI and API automation initiatives.

