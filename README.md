# SauceDemo Login Tests

This project contains automated tests for the login functionality on the SauceDemo website.

The project uses Selenium WebDriver, Maven, and JUnit. 
The tests are written in BDD style, assertyion library - hamcrest.

Made by Tovarnykh Viktor.

## Task Description

- **UC-1**: Test Login form with empty credentials:

	Type any credentials into "Username" and "Password" fields.

	Clear the inputs.

	Hit the "Login" button.

	Check the error messages: "Username is required".

- **UC-2**: Test Login form with credentials by passing Username:

	Type any credentials in username.

	Enter password.

	Clear the "Password" input.

	Hit the "Login" button.

	Check the error messages: "Password is required".

- **UC-3**: Test Login form with credentials by passing Username & Password:

	Type credentials in username which are under Accepted username are sections.

	Enter password as secret sauce.

	Click on Login and validate the title “Swag Labs” in the dashboard.

## Additional requirements

	Provide parallel execution, add logging for tests and use Data Provider to parametrize tests. 
	Make sure that all tasks are supported by these 3 conditions: UC-1; UC-2; UC-3.

## How to Run

1. Clone the repository.
2. Navigate to the project directory.
3. Run the tests using Maven:

```bash
mvn test
```
Or run test inside IDE.