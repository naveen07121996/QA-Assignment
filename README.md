
## NewYork times International Website : Automation Testing Framework
The New York Times International website provides global news, insights, and analysis on a wide range of topics including politics, business, and culture. It delivers high-quality journalism and multimedia content, offering an engaging experience for readers worldwide.


## Features

* Search - Validate the search bar's operation, including handling of date range selections and appropriate responses for invalid input.
* Header Navigation - Ensure smooth navigation across header sections, confirming that each link leads to the correct corresponding page.
* Footer Links - Verify the accuracy of footer links, ensuring they direct users to the intended sections.
* Time Store-Add to Cart -  Test the user flow in the Time Store, covering product selection, category browsing, and adding items to the shopping cart.
## Test Case Design
  The test cases are designed using Cucumber and are structured as feature files written in Gherkin syntax. Each feature file contains multiple scenarios that represent individual test cases. The framework follows the Page Object Model (POM) design pattern, which separates test logic from web element locators and page-specific methods. This approach enhances maintainability and scalability of the test suite.
## Key Components
* Page Objects - Contains classes for each page of the NewYork times International application. Each page object class includes locators and actions that are specific to that page.

* Step Definitions - Maps the Gherkin steps from the feature files to corresponding Java methods. This is where the interaction between the tests and the page objects takes place.

* Runners - Contains the JUnit runner class (e.g., TestRunner.java) used to execute the Cucumber tests.

* Utilities - Includes helper classes such as ExcelReader for reading external data files and ConfigReader for reading configuration properties.

* Feature Files - Cucumber feature files written in Gherkin syntax that define the test scenarios.


## Setup & Installation

Prerequisites:

* Java - Ensure that JDK 17 or higher is installed and properly configured in your system's environment variables (e.g., JAVA_HOME).

* Maven - Ensure Maven is installed and available in your system's PATH. You can verify by running mvn -version in the terminal.

* Chrome WebDriver - The framework is set up to run tests using the Chrome browser. Ensure that the Chrome browser is installed, and the appropriate version of ChromeDriver is available (matching your browser version).

## Installation Steps

1. Clone the repository :

        git clone  https://github.com/naveen07121996/QA-Assignment/tree/master
    
 2. Install Maven dependencies :   

          mvn clean install
## Running Tests

* Open the project in eclipse or intellij idea
* Execute the tests using feature files
* Right click on runner class
* Select Run Junit tests

To execute the tests, you can use the following commands:

## Run All tests

     mvn clean test


  This command will compile the project and execute all the scenarios defined in the feature files using the RunnerClass.

## Run Tests
  If you want to run the feature or all scenarios, you can specify the command :

     mvn -Dcucumber.options="src/test/resources/Features/TestFeature.feature" test

## Running tests on different browsers
* Change the browser type in config.properties file for running on different browsers.
* Chrome(currently set)
* Edge
* Firefox
* Safari

## Run specific scenario
   If you'd like to run a specific tag from the feature file, the command would be: 

     mvn -Dcucumber.options="--tags @SearchData" test

## Technologies Used
* Framework Type - Cucumber & Data driven Framework
* Design pattern - Page Object Model Design pattern, external object repository and inbuilt custom annotations.
* Selenium WebDriver - For Browser Automation
* Cucumber - For organizing and running test cases.
* Maven - For dependency management, Project life cycle and build annotation.
* Java - Primary Programming Language.

## Project Structure
* Eclipse - Eclipse configuration files
* Reports - target folder containing report files
* src - Source code for test scripts, this package consists of below given structure.
* src/main/java - Consists of all packages like Common methods, page objects, utility files.
* src/test/java - Consists of packages like runner, step defintions and Hooks.
* src/test/resources - Consists of feature files,properties file and test data.
* pom.xml - Maven project file for managing dependencies

## Configuration

The test framework is configured through a config.properties file. This file contains key parameters required for the tests, including:

* Browser - Defines which browser the tests should run on. This can be set to any supported browser like Chrome, Firefox, or Edge.

* Base URL - Stores the base URL of the application under test. It is used to access the application throughout the tests.

* API Endpoint URL - Specifies various API or search-related URLs required for specific test cases (e.g., performing search queries).

* Timeouts - Configures different timeouts for test execution, including:

* Environment - Indicates the environment in which the tests are run (e.g., QA, staging, production).

* Error Messages - Stores expected error messages that can be referenced in the tests to validate user-facing errors or application messages.
## Reporting

After running the tests, a Cucumber report will be generated in the target/cucumber-reports/ folder. It includes both HTML and JSON reports that provide a detailed summary of test results.

* To view the report, open the following file in your browser:

          target/test-reports.html


