package com.epam.tovarnykh.ta.driver;

import org.openqa.selenium.WebDriver;

public abstract class DriverManager {

    protected WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

}
