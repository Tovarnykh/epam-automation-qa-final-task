package com.epam.tovarnykh.ta.driver;

import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxDriverManager extends DriverManager {
    private static final String RESOURCES_PATH = "src\\test\\resources\\";

    public FirefoxDriverManager() {
        System.setProperty("webdriver.firefox.driver", RESOURCES_PATH + "geckodriver.exe");
        driver = new FirefoxDriver();
    }

}
