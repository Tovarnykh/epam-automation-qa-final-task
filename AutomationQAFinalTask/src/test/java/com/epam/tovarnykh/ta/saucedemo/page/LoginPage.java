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

    public LoginPage(WebDriver driver) {
        super(driver);

        this.wait = new WebDriverWait(driver, Duration.ofSeconds(2));

        PageFactory.initElements(driver, this);
    }

    @Override
    public LoginPage openPage() {
        driver.get(LOGIN_PAGE_LINK);
        logger.info("Login page is open");
        return this;
    }

    public LoginPage setLoginValue(String userName) {
        loginField.clear();
        loginField.sendKeys(userName);
        logger.info("Login was entered inside the login input: {}", userName);
        return this;
    }

    public LoginPage clearLoginValue() {
        loginField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        loginField.sendKeys(Keys.BACK_SPACE);
        loginField.sendKeys(Keys.ENTER);
        logger.info("Login input cleared");
        return this;
    }

    public LoginPage setPasswordValue(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
        logger.info("Password was entered inside the password input");
        return this;
    }

    public LoginPage clearPasswordValue() {
        passwordField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        passwordField.sendKeys(Keys.BACK_SPACE);
        passwordField.sendKeys(Keys.ENTER);
        logger.info("Password input cleared");
        return this;
    }

    public LoginPage clickLoginButton() {
        loginButton.click();
        logger.info("Login button clicked");
        if(driver.getCurrentUrl().equals(INVENTORY_PAGE_LINK)){
            logger.info("Inventory page is open");
        }
        return this;
    }

    public String getErrorMessage() {
        WebElement errorMessageElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(ERROR_MESSAGE_SELECTOR))
        );
        logger.info("Error text was received: {}", errorMessageElement.getText());
        return errorMessageElement.getText();
    }

}
