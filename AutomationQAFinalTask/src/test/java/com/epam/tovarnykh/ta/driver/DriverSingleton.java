package com.epam.tovarnykh.ta.driver;

import org.openqa.selenium.WebDriver;

/**
 * This class is a singleton utility that provides instances of WebDriver
 * based on the specified browser type. It encapsulates the logic of
 * WebDriver creation for different browser types.
 */
public class DriverSingleton {

    private static WebDriver driver;

    private DriverSingleton() {
    }

    /**
     * Returns an instance of WebDriver based on the specified browser type.
     * This method uses a switch statement to determine which WebDriver to create.
     *
     * @param type The Browser type (e.g., EDGE, FIREFOX, CHROME) for which the WebDriver is to be created.
     * @return A WebDriver instance corresponding to the specified browser type.
     * @throws IllegalArgumentException if the browser type is not supported.
     */
    public static WebDriver getDriver(Browser type) {

        synchronized (DriverSingleton.class) {
            switch (type) {
                case EDGE:
                    driver = new EdgeDriverManager().getDriver();
                    return driver;
                case FIREFOX:
                    driver = new FirefoxDriverManager().getDriver();
                    return driver;
                case CHROME:
                    driver = new ChromeDriverManager().getDriver();
                    return driver;
                default:
                    break;
            }
            throw new IllegalArgumentException("Unsupported browser type: " + type);
        }
    }

}
