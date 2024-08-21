package com.epam.tovarnykh.ta.driver;

import org.openqa.selenium.WebDriver;

/**
 * This class is a singleton utility that provides instances of WebDriver
 * based on the specified browser type. It encapsulates the logic of
 * WebDriver creation for different browser types.
 */
public class DriverSingleton {
    // TODO it's not a Singleton, it just a basic switch statement
    // TODO to implement a singleton pattern, you need to ensure that only one instance of WebDriver is created and reused throughout the application

    /**
     * Returns an instance of WebDriver based on the specified browser type.
     * This method uses a switch statement to determine which WebDriver to create.
     *
     * @param type The Browser type (e.g., EDGE, FIREFOX) for which the WebDriver is to be created.
     * @return A WebDriver instance corresponding to the specified browser type.
     * @throws IllegalArgumentException if the browser type is not supported.
     */
    public static WebDriver getDriver(Browser type) {

        switch (type) {
            case EDGE:
                return new EdgeDriverManager().getDriver();
            case FIREFOX:
                return new FirefoxDriverManager().getDriver();
            default:
                break;
        }
        throw new IllegalArgumentException("Unsupported browser type: " + type);
    }

}
