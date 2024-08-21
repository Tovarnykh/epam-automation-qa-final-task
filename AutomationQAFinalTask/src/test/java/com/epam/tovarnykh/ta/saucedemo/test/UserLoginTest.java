package com.epam.tovarnykh.ta.saucedemo.test;

import com.epam.tovarnykh.ta.saucedemo.model.User;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.opentest4j.AssertionFailedError;

import static com.epam.tovarnykh.ta.saucedemo.page.constants.InventoryPageConstants.DASHBOARD_TITLE_SELECTOR;
import static com.epam.tovarnykh.ta.saucedemo.page.constants.InventoryPageConstants.INVENTORY_PAGE_LINK;
import static com.epam.tovarnykh.ta.saucedemo.page.constants.LoginPageConstants.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class UserLoginTest extends CommonConditions {

    @ParameterizedTest
    @MethodSource("provideUserConfigurations")
    @Tag("uc-1")
    public void shouldShowErrorWhenLoginWithEmptyCredentials(User user) {
        //Given: The login page is open
        setUpBrowser(user.getBrowser()); // TODO @BeforeEach/@BeforeAll
        loginPage.openPage();

        //When: Type any credentials into "Username" & "Password" fields
        loginPage.enterUserName(user.getUserName())
                .enterPassword(user.getPassword());

        //And: Clear the inputs in login and password
        loginPage.clearUserName()
                .clearPassword();

        //And: Hit on "Login" button
        loginPage.clickLogin();

        //Then: Check the error messages: "Username is required"
        try {
            assertThat("The login error text does not match the expected error text.",
                    loginPage.retrieveErrorMessageDisplayed(),
                    is(equalTo(ERROR_MESSAGE_STARTER + "Username is required")));// TODO create separate constant
        } catch (NoSuchElementException | TimeoutException e) {
            logger.error("Unable to locate element:" + ERROR_MESSAGE_SELECTOR);
            if (driver.getCurrentUrl() != LOGIN_PAGE_LINK) { // TODO refactor to equals
                throw new AssertionError("Login was successful, the program did not work as planned.");
            } else {
                throw new AssertionFailedError("The error message did not appear on the page.\n" + e);
            }
        }
    }

    @ParameterizedTest
    @MethodSource("provideUserConfigurations")
    @Tag("uc-2")
    public void shouldShowErrorWhenPasswordIsMissing(User user) {
        // Given: The login page is open
        setUpBrowser(user.getBrowser()); // TODO @BeforeEach/@BeforeAll
        loginPage.openPage();

        // When: Type any credentials in username and password.
        loginPage.enterUserName(user.getUserName());

        //And: Enter password
        loginPage.enterPassword(user.getPassword());

        //And: Clear the "Password" input.
        loginPage.clearPassword();

        // And: Hit on "Login" button
        loginPage.clickLogin();

        // Then: An error message "Password is required" should be displayed
        try {
            assertThat("The password error text does not match the expected text.",
                    loginPage.retrieveErrorMessageDisplayed(),
                    is(equalTo(ERROR_MESSAGE_STARTER + "Password is required"))); // TODO create separate constants
        } catch (NoSuchElementException e) {
            logger.error("Unable to locate element:" + ERROR_MESSAGE_SELECTOR);
            if (driver.getCurrentUrl() != LOGIN_PAGE_LINK) { // TODO refactor to equals
                throw new AssertionError("Login was successful, the program did not work as planned.");
            } else {
                throw new AssertionFailedError("The error message did not appear on the page.\n" + e);
            }
        }
    }

    @ParameterizedTest
    @MethodSource("provideUserConfigurations")
    @Tag("uc-3")
    public void shouldLoginSuccessfullyWithValidCredentials(User user) {
        // Given: The login page is open
        setUpBrowser(user.getBrowser()); // TODO @BeforeEach/@BeforeAll
        loginPage.openPage();

        // When: Type credentials in username which are under Accepted username are sections.
        loginPage.enterUserName("standard_user"); // TODO hardcoded

        //And: Enter password as secret sauce.
        loginPage.enterPassword("secret_sauce"); // TODO hardcoded

        // Then: Click on Login and validate the title “Swag Labs” in the dashboard.
        loginPage.clickLogin();
        try {
            assertThat("The title is not as expected.",
                    inventoryPage.retrieveDashboardTitle(),
                    is(equalTo("Swag Labs")));
        } catch (NoSuchElementException e) {
            logger.error("Unable to locate element:" + DASHBOARD_TITLE_SELECTOR);
            if (driver.getCurrentUrl() != INVENTORY_PAGE_LINK) { // TODO refactor to equals
                throw new AssertionError("An attempt to log in into account was unsuccessful.");
            } else {
                throw new AssertionFailedError(e.toString());
            }
        }
    }


    // TODO actually no need to try catch them for noSuchElementException, we have assertions, it should fail if element is not found
    // TODO but all right let it be
}
