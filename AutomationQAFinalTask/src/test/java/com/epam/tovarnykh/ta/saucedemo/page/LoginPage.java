package com.epam.tovarnykh.ta.saucedemo.page;

import org.apache.commons.logging.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.epam.tovarnykh.ta.saucedemo.constants.LoginPageConstants.*;

public class LoginPage extends AbstractPage{

    @FindBy(xpath = LOGIN_INPUT_SELECTOR)
    private WebElement loginInput;

    @FindBy(xpath = PASSWORD_INPUT_SELECTOR)
    private WebElement passwordInput;

    @FindBy(xpath = LOGIN_BUTTON_SELECTOR)
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);

        PageFactory.initElements(driver, this);
    }

    @Override
    public LoginPage openPage() {
        driver.get(LOGIN_PAGE_LINK);
        return this;
    }

    public LoginPage setLoginValue(String userName){
        loginInput.clear();
        loginInput.sendKeys(userName);
        return this;
    }

    public LoginPage clearLoginValue(){
        loginInput.clear();
        return this;
    }

    public LoginPage setPasswordValue(String password){
        passwordInput.clear();
        passwordInput.sendKeys(password);
        return this;
    }

    public LoginPage clearPasswordValue(){
        passwordInput.clear();
        return this;
    }

    public LoginPage clickLoginButton(){
        loginButton.click();
        return this;
    }

    public String getErrorMessage(){
        return driver.findElement(By.xpath(ERROR_MESSAGE_SELECTOR)).getText();
    }

}
