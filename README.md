# Marriott-IT
Marriott Bonvoy test automation framework using Java, Selenium, TestNG, Maven, and REST API automation.

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




# Marriott Git Dual-Remote Cheat Sheet

This cheat sheet is for the setup where:

```text
YOUR MAIN REPOSITORY
origin → https://github.com/bikrampur1988/Marriott-IT.git

SABIHA'S REPOSITORY
sabiha → https://github.com/Sabihaakther/Marriott-IT.git
```

Your main local working directory:

```bash
~/projects/Automation-Projects/Marriott-IT
```

Your main branch:

```text
develop
```

Sabiha's main branch:

```text
main
```

---

## 1. Always Check Where You Are

Before doing Git work:

```bash
pwd
```

You should normally be inside:

```text
/home/amin/projects/Automation-Projects/Marriott-IT
```

Check your current branch:

```bash
git branch --show-current
```

Check your remotes:

```bash
git remote -v
```

Expected:

```text
origin  https://github.com/bikrampur1988/Marriott-IT.git
sabiha  https://github.com/Sabihaakther/Marriott-IT.git
```

---

## 2. Normal Work Directly on `develop`

Make your code changes.

Then:

```bash
git status
```

Stage everything:

```bash
git add .
```

Commit:

```bash
git commit -m "Add Look Up Member Number automation"
```

Push to your repository:

```bash
git push origin develop
```

Your code goes to:

```text
bikrampur1988/Marriott-IT
        ↓
develop
```

---

## 3. Push the Same Work to Sabiha

Before pushing to Sabiha, get her latest repository information:

```bash
git fetch sabiha
```

Then, when you are ready to send your local `develop` work to her `main` branch:

```bash
git push sabiha develop:main
```

Meaning:

```text
git push  sabiha   develop : main
          │          │        │
          │          │        └── Sabiha's destination branch
          │          └────────── Your local branch
          └───────────────────── Sabiha's remote
```

So:

```bash
git push origin develop
```

means:

```text
local develop
      ↓
your develop
```

while:

```bash
git push sabiha develop:main
```

means:

```text
local develop
      ↓
Sabiha main
```

---

# 4. Recommended Feature Branch Workflow

For real development work, create a feature branch.

Example:

```bash
git checkout develop
```

Make sure your `develop` is current:

```bash
git pull origin develop
```

Create the feature branch:

```bash
git checkout -b feature/look-up-member-number
```

Confirm:

```bash
git branch --show-current
```

Expected:

```text
feature/look-up-member-number
```

Now make all your Selenium changes.

---

## 5. Save Feature Branch Work

Check changes:

```bash
git status
```

Stage:

```bash
git add .
```

Commit:

```bash
git commit -m "Add Look Up Member Number automation"
```

Push the feature branch to your GitHub:

```bash
git push -u origin feature/look-up-member-number
```

Flow:

```text
Local feature branch
        ↓
origin/feature/look-up-member-number
```

---

# 6. Merge Feature Branch Into Your `develop`

After testing the feature successfully:

```bash
git checkout develop
```

Update local `develop`:

```bash
git pull origin develop
```

Merge:

```bash
git merge feature/look-up-member-number
```

Push:

```bash
git push origin develop
```

Flow:

```text
feature/look-up-member-number
           ↓
        develop
           ↓
      origin/develop
```

---

# 7. Then Send Completed Work to Sabiha

Fetch Sabiha's latest code:

```bash
git fetch sabiha
```

Then, if the histories are compatible and you're intentionally updating her `main`:

```bash
git push sabiha develop:main
```

Final flow:

```text
feature branch
      ↓
your develop
      ↓
origin/develop
      ↓
Sabiha main
```

---

# 8. Very Important Safety Commands

Before pushing anywhere:

```bash
git status
```

Check current branch:

```bash
git branch --show-current
```

Check destinations:

```bash
git remote -v
```

Fetch latest changes:

```bash
git fetch origin
git fetch sabiha
```

These commands are safe because `fetch` downloads information without changing your working files.

---

# 9. Check What Branches Exist

```bash
git branch
```

Remote branches:

```bash
git branch -r
```

All branches:

```bash
git branch -a
```

Example:

```text
* develop
  feature/look-up-member-number

  remotes/origin/develop
  remotes/origin/feature/look-up-member-number
  remotes/sabiha/main
```

The `*` shows your current branch.

---

# 10. Check Recent Commits

```bash
git log --oneline -10
```

More visual:

```bash
git log --oneline --graph --decorate --all -20
```

This is very useful before merging or pushing.

---

# 11. Compare Your Branch With Sabiha's Main

First:

```bash
git fetch sabiha
```

Then:

```bash
git log --oneline --left-right --graph develop...sabiha/main
```

This helps you see whether:

```text
your develop
```

and:

```text
sabiha/main
```

have different commits.

---

# 12. If You Made Changes But Are Not Ready to Commit

Check:

```bash
git status
```

Do not push.

Your work remains only on your machine until you commit and push.

---

# 13. If You Want to Undo an Unstaged File Change

For one file:

```bash
git restore path/to/file
```

Example:

```bash
git restore automation/ui-tests/src/main/java/com/marriott/ui/pages/SignInPage.java
```

Be careful: this removes your uncommitted modifications to that file.

---

# 14. If You Accidentally Staged a File

Example:

```bash
git add .
```

but you did not want one file staged.

Unstage it:

```bash
git restore --staged path/to/file
```

Your actual file changes remain.

---

# 15. Delete a Finished Local Feature Branch

After successfully merging:

```bash
git checkout develop
```

Then:

```bash
git branch -d feature/look-up-member-number
```

Delete the remote feature branch if no longer needed:

```bash
git push origin --delete feature/look-up-member-number
```

---

# 16. Your Most Common Daily Workflow

```bash
cd ~/projects/Automation-Projects/Marriott-IT

git checkout develop

git pull origin develop

git checkout -b feature/my-new-feature
```

Work on the code.

Then:

```bash
git status

git add .

git commit -m "Implement my new feature"

git push -u origin feature/my-new-feature
```

After testing:

```bash
git checkout develop

git pull origin develop

git merge feature/my-new-feature

git push origin develop
```

Then, when ready for Sabiha:

```bash
git fetch sabiha

git push sabiha develop:main
```

---

# 17. Easy Memory Rule

Think:

```text
origin = MINE

sabiha = SABIHA
```

And:

```text
MY BRANCH = develop

SABIHA BRANCH = main
```

So:

```bash
git push origin develop
```

means:

```text
Mine → develop
```

and:

```bash
git push sabiha develop:main
```

means:

```text
My local develop → Sabiha main
```

---

# 18. Golden Rule

Before every important push, run:

```bash
pwd
git branch --show-current
git remote -v
git status
```

Then ask yourself:

```text
Where am I?
Which branch am I on?
Which remote am I pushing to?
What files am I committing?
```

If those four answers look correct, then push. ✅


