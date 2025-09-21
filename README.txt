## Project Name:
SportingBull Search Automation

## Description:
This project automates the search functionality of the SportingBull website
 using Selenium WebDriver and Cucumber (BDD).
It validates different search scenarios, including:

Partial keyword matches

Unicode / special character handling

Case insensitivity

Long input handling


## Technologies Used:

Java

Selenium WebDriver

Cucumber (BDD)

Maven

TestNG

Log4j (logging)

ExtentReports (reporting)

## Project Structure:

src/test/java: Step definitions, page objects, and utilities.

src/test/resources: Feature files and configuration files.

reports: ExtentReports test execution reports.

logs: Log files generated during test execution.

screenshots: Screenshots captured during test failures.

## How to Run:

Clone the repository:

git clone https://github.com/vyshnavipapineni/sportingbull-search-automation.git
cd sportingbull-search-automation


Configure the config.properties file with the required browser and URL.

Run the tests using Maven:

mvn test


After execution:

Open the HTML report in the reports folder.

Check logs and screenshots for debugging details.

Author:
Vyshnavi Papineni