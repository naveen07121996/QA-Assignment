
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

        git clone  https://github.com/naveen07121996/QA-Assignment.git
    
 2. Install Maven dependencies :   

          mvn clean install
## Running Tests

To execute the tests, you can use the following commands:

## Run All tests

     mvn clean test


  This command will compile the project and execute all the scenarios defined in the feature files using the RunnerClass.

## Run Tests
  If you want to run the feature or all scenarios, you can specify the command :

     mvn -Dcucumber.options="src/test/resources/Features/TestFeature.feature" test

## Run specific scenario
   If you'd like to run a specific tag from the feature file, the command would be: 

     mvn -Dcucumber.options="--tags @SearchData" test



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

      target/test-reports/test-reports.html
## Screenshots

![Newyorktimes1](https://github.com/user-attachments/assets/553385db-4317-4d63-8af4-ed2d2b736021)

![Newyorktimes2](https://github.com/user-attachments/assets/e3ebfde5-25e6-464c-a3e1-01e730c1179f)

![Newyorktimes3](https://github.com/user-attachments/assets/9f607818-5df7-4794-bb08-a25d4dde2f48)

![Newyorktimes4](https://github.com/user-attachments/assets/15b7ae83-1be4-459f-9b76-7a275f4203d0)

![Newyorktimes5](https://github.com/user-attachments/assets/930e8aa1-3d7b-4bf5-8de2-ea942d4ee752)

![Newyorktimes6](https://github.com/user-attachments/assets/4a16db0e-ef14-4d7a-85e6-bd3b45ed176c)

![Newyorktimes7](https://github.com/user-attachments/assets/c0ef46d6-2784-4a15-8cb4-82a0ede7b338)

![Newyorktimes8](https://github.com/user-attachments/assets/2c99cc3f-b6f4-46e9-b4d7-fabfed65dda0)

![Newyorktimes9](https://github.com/user-attachments/assets/c795d85a-a4eb-4114-8826-cc1c502c5af2)

![Newyorktimes10](https://github.com/user-attachments/assets/27505313-9e15-4bfd-84a1-156e59fcc449)

