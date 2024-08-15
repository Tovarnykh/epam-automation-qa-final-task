package com.epam.tovarnykh.ta.saucedemo.test;

import com.epam.tovarnykh.ta.saucedemo.model.User;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static com.epam.tovarnykh.ta.saucedemo.constants.LoginPageConstants.ERROR_MESSAGE_STARTER;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class UserLoginTest extends CommonConditions {

    @ParameterizedTest
    @MethodSource("provideUserConfigurations")
    @Tag("uc-1")
    public void shouldShowErrorWhenLoginWithEmptyCredentials(User user) {
        //Given: The login page is open
        setUpBrowser(user.getBrowser());
        loginPage.openPage();

        //When: Type any credentials into "Username" & "Password" fields
        loginPage.setLoginValue(user.getUserName())
                .setPasswordValue(user.getPassword());

        //And: Clear the inputs in login and password
        loginPage.clearLoginValue()
                .clearPasswordValue();

        //And: Hit on "Login" button
        loginPage.clickLoginButton();

        //Then: Check the error messages: "Username is required"
        assertThat("The error text does not match the expected error text.",
                loginPage.getErrorMessage(),
                is(equalTo(ERROR_MESSAGE_STARTER + "Username is required")));

    }

    @ParameterizedTest
    @MethodSource("provideUserConfigurations")
    @Tag("uc-2")
    public void shouldShowErrorWhenPasswordIsMissing(User user) {
        // Given: The login page is open
        setUpBrowser(user.getBrowser());
        loginPage.openPage();

        // When: Type any credentials in username and password.
        loginPage.setLoginValue(user.getUserName());

        //And: Enter password
        loginPage.setPasswordValue(user.getPassword());

        //And: Clear the "Password" input.
        loginPage.clearPasswordValue();

        // And: Hit on "Login" button
        loginPage.clickLoginButton();

        // Then: An error message "Password is required" should be displayed
        assertThat("The error text does not match the expected error text.",
                loginPage.getErrorMessage(),
                is(equalTo(ERROR_MESSAGE_STARTER + "Password is required")));
    }

    @ParameterizedTest
    @MethodSource("provideUserConfigurations")
    @Tag("uc-3")
    public void shouldLoginSuccessfullyWithValidCredentials(User user) {
        // Given: The login page is open
        setUpBrowser(user.getBrowser());
        loginPage.openPage();

        // When: Type credentials in username which are under Accepted username are sections.
        loginPage.setLoginValue("standard_user");

        //And: Enter password as secret sauce.
        loginPage.setPasswordValue("secret_sauce");

        // And: Click on Login and validate the title “Swag Labs” in the dashboard.
        loginPage.clickLoginButton();
        inventoryPage.getTitle();

        // Then: The user should be navigated to the dashboard with the title "Swag Labs"
        assertThat("The title is not as expected.",
                inventoryPage.getTitle(),
                is(equalTo("Swag Labs")));
    }

}
