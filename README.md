# OrangeHRM Selenium Automation Framework

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Selenium](https://img.shields.io/badge/Selenium-4-43B02A?style=for-the-badge&logo=selenium&logoColor=white)
![TestNG](https://img.shields.io/badge/TestNG-Framework-red?style=for-the-badge)
![Maven](https://img.shields.io/badge/Maven-Build-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![GitHub Actions](https://img.shields.io/badge/GitHub_Actions-CI-2088FF?style=for-the-badge&logo=githubactions&logoColor=white)
![Chrome](https://img.shields.io/badge/Chrome-Latest-4285F4?style=for-the-badge&logo=googlechrome&logoColor=white)
![POM](https://img.shields.io/badge/Design-Page_Object_Model-blue?style=for-the-badge)
![Data Driven](https://img.shields.io/badge/Testing-Data_Driven-success?style=for-the-badge)
![License](https://img.shields.io/badge/License-MIT-green?style=for-the-badge)

A Page Object Model (POM) test automation framework for the [OrangeHRM demo site](https://opensource-demo.orangehrmlive.com), built with Selenium WebDriver, Java, and TestNG.

## What it tests

- **Invalid login** — verifies an error message appears for wrong credentials
- **Valid login** — verifies the dashboard loads after successful login
- **Add employee** — adds a new employee through the PIM module
- **Search employee** — confirms the newly added employee appears in the Employee List

## Tech stack

- Java 21
- Selenium WebDriver 4.21
- TestNG 7.9
- Maven

## Framework design

- **Page Object Model** — each screen (Login, Dashboard, PIM, Add Employee, Employee List) has its own class holding locators and actions
- **Explicit waits** (`WebDriverWait` + `ExpectedConditions`) instead of hardcoded sleeps, for reliable synchronization
- **Config-driven** — the base URL and login credentials live in `config.properties`, not hardcoded in test code
- **Automatic failure screenshots** — a custom `ITestListener` captures a screenshot the moment any test fails, saved to `/screenshots`
- **CI-ready** — GitHub Actions runs the full suite headlessly on every push (see `.github/workflows/tests.yml`)

## Running locally

```bash
mvn clean test
```

This runs `testng.xml`, which executes all tests in `tests.TestRun`.

## Two bugs fixed during development (and why)

1. **Click intercepted by a loading overlay** — the Add Employee page briefly shows a loading spinner while it fetches an auto-generated Employee ID. Clicking Save too early hit the invisible overlay instead of the button. Fixed by waiting for the overlay to disappear (`ExpectedConditions.invisibilityOfElementLocated`) before attempting the click.

2. **Exact-match XPath failing on multi-class elements** — `@class='oxd-alert-content-text'` only matches if that's the *entire* class attribute. Since the actual element had multiple classes, the exact match never found it. Fixed using `contains(@class, '...')` instead.

## Project structure

```
src/main/java/
├── base/BaseClass.java       → WebDriver setup/teardown, headless mode for CI
├── pages/                    → Page Object classes
└── utils/ConfigReader.java   → Reads config.properties

src/test/java/
├── tests/TestRun.java        → Test cases
└── listeners/ScreenshotListener.java → Auto-captures screenshots on failure

src/main/resources/config.properties → URL + login credentials
.github/workflows/tests.yml          → CI pipeline (GitHub Actions)
```
