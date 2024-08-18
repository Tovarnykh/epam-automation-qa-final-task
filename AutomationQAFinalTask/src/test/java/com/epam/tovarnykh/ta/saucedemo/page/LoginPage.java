package com.epam.tovarnykh.ta.saucedemo.page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.epam.tovarnykh.ta.saucedemo.page.constants.InventoryPageConstants.INVENTORY_PAGE_LINK;
import static com.epam.tovarnykh.ta.saucedemo.page.constants.LoginPageConstants.*;

public class LoginPage extends AbstractPage {

    private WebDriverWait wait;

    @FindBy(xpath = LOGIN_INPUT_SELECTOR)
    private WebElement loginField;

    @FindBy(xpath = PASSWORD_INPUT_SELECTOR)
    private WebElement passwordField;

    @FindBy(xpath = LOGIN_BUTTON_SELECTOR)
    private WebElement loginButton;

    /**
     * Constructor to initialize the {@code LoginPage} object.
     *
     * @param driver the WebDriver instance used to interact with the browser.
     */
    public LoginPage(WebDriver driver) {
        super(driver);

        this.wait = new WebDriverWait(driver, Duration.ofSeconds(2));

        PageFactory.initElements(driver, this);
    }

    /**
     * Opens the login page by navigating to the login page URL.
     *
     * @return the {@code LoginPage} instance for method chaining.
     */
    @Override
    public LoginPage openPage() {
        driver.get(LOGIN_PAGE_LINK);
        logger.info("Login page is open");
        return this;
    }

    /**
     * Enters the login value in the username input field.
     *
     * @param userName the username to be entered.
     * @return the {@code LoginPage} instance for method chaining.
     */
    public LoginPage enterUserName(String userName) {
        loginField.clear();
        loginField.sendKeys(userName);
        logger.info("Login was entered inside the login input: {}", userName);
        return this;
    }

    /**
     * Clears the login input field.
     *
     * @return the {@code LoginPage} instance for method chaining.
     */
    public LoginPage clearUserName() {
        loginField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        loginField.sendKeys(Keys.BACK_SPACE);
        loginField.sendKeys(Keys.ENTER);
        logger.info("Login input cleared");
        return this;
    }

    /**
     * Enters the password value in the password input field.
     *
     * @param password the password to be entered.
     * @return the {@code LoginPage} instance for method chaining.
     */
    public LoginPage enterPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
        logger.info("Password was entered inside the password input");
        return this;
    }

    /**
     * Clears the password input field.
     *
     * @return the {@code LoginPage} instance for method chaining.
     */
    public LoginPage clearPassword() {
        passwordField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        passwordField.sendKeys(Keys.BACK_SPACE);
        passwordField.sendKeys(Keys.ENTER);
        logger.info("Password input cleared");
        return this;
    }

    /**
     * Clicks the login button on the login page.
     * If the user is successfully logged in, it logs that the inventory page is open.
     *
     * @return the {@code LoginPage} instance for method chaining.
     */
    public LoginPage clickLogin() {
        loginButton.click();
        logger.info("Login button clicked");
        if(driver.getCurrentUrl().equals(INVENTORY_PAGE_LINK)){
            logger.info("Inventory page is open");
        }
        return this;
    }

    /**
     * Retrieves the error message displayed on the login page.
     *
     * @return the error message as a {@code String}.
     */
    public String retrieveErrorMessageDisplayed() throws NoSuchElementException, TimeoutException {
        WebElement errorMessageElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(ERROR_MESSAGE_SELECTOR))
        );
        logger.info("Error text was received: {}", errorMessageElement.getText());
        return errorMessageElement.getText();
    }

}
