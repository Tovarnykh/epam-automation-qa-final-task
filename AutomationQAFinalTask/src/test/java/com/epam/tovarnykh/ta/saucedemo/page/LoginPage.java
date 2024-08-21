package com.epam.tovarnykh.ta.saucedemo.page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.epam.tovarnykh.ta.saucedemo.util.Constants.INVENTORY_PAGE_LINK;
import static com.epam.tovarnykh.ta.saucedemo.util.Constants.LOGIN_PAGE_LINK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isEmptyOrNullString;

public class LoginPage extends AbstractPage {

    public static final String LOGIN_INPUT_SELECTOR = "//input[@id='user-name']";
    public static final String PASSWORD_INPUT_SELECTOR = "//input[@id='password']";
    public static final String LOGIN_BUTTON_SELECTOR = "//input[@id='login-button']";
    public static final String ERROR_MESSAGE_SELECTOR = "//h3[@data-test='error']";

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

        this.wait = new WebDriverWait(driver, Duration.ofSeconds(SECONDS_WAIT_AMOUNT_PARAMETER));

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
     * Clears the selected field by its instance provided in method argument.
     *
     * @return the {@code LoginPage} instance for method chaining.
     */
    public LoginPage clearField(WebElement fieldElement) {
        //Actual clearing
        fieldElement.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        fieldElement.sendKeys(Keys.BACK_SPACE);
        fieldElement.sendKeys(Keys.ENTER);

        //Asserting that field is cleared
        String fieldValue = fieldElement.getAttribute("value");
        assertThat("The field is not empty after clearing.", fieldValue, isEmptyOrNullString());

        //Logging
        logger.info(fieldElement.getAccessibleName() + " field is cleared");
        return this;
    }

    /**
     * Clicks the login button on the login page.
     * If the user is successfully logged in, it logs that the inventory page is open.
     *
     * @return the {@code LoginPage} instance for method chaining.
     */
    public InventoryPage clickLogin() {
        loginButton.click();
        logger.info("Login button clicked");
        if (driver.getCurrentUrl().equals(INVENTORY_PAGE_LINK)) {
            logger.info("Inventory page is open");
        }
        return new InventoryPage(driver);
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

    /**
     * Gives the WebElement instance of login field on the login page.
     *
     * @return the login field as a {@code WebElement}.
     */
    public WebElement getLoginField() {
        return loginField;
    }

    /**
     * Gives the WebElement instance of password field on the login page.
     *
     * @return the password field as a {@code WebElement}.
     */
    public WebElement getPasswordField() {
        return passwordField;
    }
}
