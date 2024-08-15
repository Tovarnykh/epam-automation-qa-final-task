package com.epam.tovarnykh.ta.saucedemo.test;

import com.epam.tovarnykh.ta.driver.Browser;
import com.epam.tovarnykh.ta.driver.WebDriverFactory;
import com.epam.tovarnykh.ta.saucedemo.model.User;
import com.epam.tovarnykh.ta.saucedemo.page.LoginPage;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static com.epam.tovarnykh.ta.saucedemo.constants.LoginPageConstants.ERROR_MESSAGE_STARTER;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserLoginTest extends CommonConditions {

    @ParameterizedTest
    @MethodSource("provideUserConfigurations")
    @Tag("uc-1")
    public void shouldShowErrorWhenLoginWithEmptyCredentials(User user) throws InterruptedException {
        //Given: The login page is open
        setUpBrowser(user.getBrowser());
        loginPage.openPage();

        //When: Type any credentials into "Username" & "Password" fields
        loginPage.setLoginValue(user.getUserName())
                .setPasswordValue(user.getPassword());

        //And: Clear the inputs in login and password
        loginPage.clearLoginValue();
               // .clearPasswordValue();

        //And: Hit on "Login" button
        loginPage.clickLoginButton();
        Thread.sleep(2000);
        //Then: Check the error messages: "Username is required"
        assertEquals(ERROR_MESSAGE_STARTER + "Username is required", loginPage.getErrorMessage(),
                "The error text does not match the expected error text.");

    }

//    @ParameterizedTest
//    @MethodSource("provideUserConfigurations")
//    @Tag("uc-2")
//    public void shouldShowErrorWhenPasswordIsMissing(User user) {
//        // Given: The login page is open
//        setUpBrowser(user.getBrowser());
//        loginPage.openPage();
//
//        // When: Type any credentials in username and password.
//        loginPage.setLoginValue(user.getUserName());
//
//        //And: Enter password
//        loginPage.setPasswordValue(user.getPassword());
//
//        //And: Clear the "Password" input.
//        loginPage.clearPasswordValue();
//
//        // And: Hit on "Login" button
//        loginPage.clickLoginButton();
//
//        // Then: An error message "Password is required" should be displayed
//            assertEquals(ERROR_MESSAGE_STARTER + "Password is required", loginPage.getErrorMessage(),
//                    "The error text does not match the expected error text.");
//    }
//
//    @ParameterizedTest
//    @MethodSource("provideUserConfigurations")
//    @Tag("uc-3")
//    public void shouldLoginSuccessfullyWithValidCredentials(User user) {
//        // Given: The login page is open
//        setUpBrowser(user.getBrowser());
//        loginPage.openPage();
//
//        // When: Type credentials in username which are under Accepted username are sections.
//        loginPage.setLoginValue("standard_user");
//
//        //And: Enter password as secret sauce.
//        loginPage.setPasswordValue("secret_sauce");
//
//        // And: Click on Login and validate the title “Swag Labs” in the dashboard.
//        loginPage.clickLoginButton();
//        inventoryPage.getTitle();
//
//        // Then: The user should be navigated to the dashboard with the title "Swag Labs"
//        assertEquals("Swag Labs", inventoryPage.getTitle(),
//                "The title has not been seen");
//    }

}
