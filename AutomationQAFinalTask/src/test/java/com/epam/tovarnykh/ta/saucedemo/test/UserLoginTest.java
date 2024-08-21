package com.epam.tovarnykh.ta.saucedemo.test;

import com.epam.tovarnykh.ta.saucedemo.model.User;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.opentest4j.AssertionFailedError;

import static com.epam.tovarnykh.ta.saucedemo.page.InventoryPage.DASHBOARD_TITLE_SELECTOR;
import static com.epam.tovarnykh.ta.saucedemo.page.LoginPage.ERROR_MESSAGE_SELECTOR;
import static com.epam.tovarnykh.ta.saucedemo.util.Constants.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class UserLoginTest extends CommonConditions {

    @ParameterizedTest
    @MethodSource("provideUserConfigurations")
    @Tag("uc-1")
    public void shouldShowErrorWhenLoginWithEmptyCredentials(User user) {
        //Given: The login page is open
        setUpBrowserForParametrized(user.getBrowser());
        loginPage.openPage();

        //When: Type any credentials into "Username" & "Password" fields
        loginPage.enterUserName(user.getUserName())
                .enterPassword(user.getPassword());

        //And: Clear the inputs in login and password
        loginPage.clearField(loginPage.getLoginField())
                .clearField(loginPage.getPasswordField());

        //And: Hit on "Login" button
        loginPage.clickLogin();

        //Then: Check the error messages: "Username is required"
        try {
            assertThat("The login error text does not match the expected error text.",
                    loginPage.retrieveErrorMessageDisplayed(),
                    is(equalTo(LOGIN_PAGE_USERNAME_ERROR)));
        } catch (NoSuchElementException | TimeoutException e) {
            logger.error("Unable to locate element:" + ERROR_MESSAGE_SELECTOR);
            if (!driver.getCurrentUrl().equals(LOGIN_PAGE_LINK)) {
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
        setUpBrowserForParametrized(user.getBrowser());
        loginPage.openPage();

        // When: Type any credentials in username and password.
        loginPage.enterUserName(user.getUserName());

        //And: Enter password
        loginPage.enterPassword(user.getPassword());

        //And: Clear the "Password" input.
        loginPage.clearField(loginPage.getPasswordField());

        // And: Hit on "Login" button
        loginPage.clickLogin();

        // Then: An error message "Password is required" should be displayed
        try {
            assertThat("The password error text does not match the expected text.",
                    loginPage.retrieveErrorMessageDisplayed(),
                    is(equalTo(LOGIN_PAGE_PASSWORD_ERROR)));
        } catch (NoSuchElementException e) {
            logger.error("Unable to locate element:" + ERROR_MESSAGE_SELECTOR);
            if (!driver.getCurrentUrl().equals(LOGIN_PAGE_LINK)) {
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
        setUpBrowserForParametrized(user.getBrowser());
        loginPage.openPage();

        // When: Type credentials in username which are under Accepted username are sections.
        loginPage.enterUserName(user.getUserName());

        //And: Enter password as secret sauce.
        loginPage.enterPassword(user.getPassword());

        // Then: Click on Login and validate the title “Swag Labs” in the dashboard.
        try {
            assertThat("The title is not as expected.",
                    loginPage.clickLogin()
                            .retrieveDashboardTitle(),
                    is(equalTo("Swag Labs")));
        } catch (NoSuchElementException e) {
            logger.error("Unable to locate element:" + DASHBOARD_TITLE_SELECTOR);
            if (!driver.getCurrentUrl().equals(INVENTORY_PAGE_LINK)) {
                throw new AssertionError("An attempt to log in into account was unsuccessful.");
            } else {
                throw new AssertionFailedError(e.toString());
            }
        }
    }

}
