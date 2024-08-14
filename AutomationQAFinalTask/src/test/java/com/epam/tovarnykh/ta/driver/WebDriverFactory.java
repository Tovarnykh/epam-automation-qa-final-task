package com.epam.tovarnykh.ta.driver;

import org.openqa.selenium.WebDriver;

public class WebDriverFactory {

    public static WebDriver getDriver(Browser type) {

        switch (type) {
            case FIREFOX:
                return new FirefoxDriverManager().getDriver();
            case EDGE:
                return new EdgeDriverManager().getDriver();
            default:
                break;
        }
        throw new IllegalArgumentException("Unsupported browser type: " + type);
    }

}
