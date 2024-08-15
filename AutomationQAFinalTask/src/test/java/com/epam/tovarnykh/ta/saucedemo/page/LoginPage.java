package com.epam.tovarnykh.ta.saucedemo.page;

import org.apache.commons.logging.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.epam.tovarnykh.ta.saucedemo.constants.LoginPageConstants.*;

public class LoginPage extends AbstractPage{

    private WebDriverWait wait;

    @FindBy(xpath = LOGIN_INPUT_SELECTOR)
    private WebElement loginInput;

    @FindBy(xpath = PASSWORD_INPUT_SELECTOR)
    private WebElement passwordInput;

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

    public LoginPage setLoginValue(String userName){
        loginInput.clear();
        loginInput.sendKeys(userName);
        logger.info("Login was entered inside the login input: {}", userName);
        return this;
    }

    public LoginPage clearLoginValue(){
        loginInput.clear();
        logger.info("Login input cleared");
        return this;
    }

    public LoginPage setPasswordValue(String password){
        passwordInput.clear();
        passwordInput.sendKeys(password);
        logger.info("Password was entered inside the password input");
        return this;
    }

    public LoginPage clearPasswordValue(){
        passwordInput.clear();
        logger.info("Password input cleared");
        return this;
    }

    public LoginPage clickLoginButton() throws InterruptedException {
        Thread.sleep(2000);
        loginButton.click();
        logger.info("Login button clicked");
        return this;
    }

    public String getErrorMessage(){
        WebElement errorMessageElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(ERROR_MESSAGE_SELECTOR))
        );
        logger.info("Error text was received: {}", errorMessageElement.getText());
        return errorMessageElement.getText();
    }

}
