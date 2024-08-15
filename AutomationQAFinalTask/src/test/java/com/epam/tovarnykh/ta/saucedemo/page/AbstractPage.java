package com.epam.tovarnykh.ta.saucedemo.page;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractPage {

    protected static final Logger logger = LoggerFactory.getLogger(LoginPage.class);


    protected WebDriver driver;

    protected abstract AbstractPage openPage();

    protected AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

}
