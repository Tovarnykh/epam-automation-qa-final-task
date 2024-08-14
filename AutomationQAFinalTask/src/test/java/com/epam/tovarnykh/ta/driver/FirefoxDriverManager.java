package com.epam.tovarnykh.ta.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.edge.EdgeDriver;

public class FirefoxDriverManager extends DriverManager {

    public FirefoxDriverManager() {
        WebDriverManager.firefoxdriver().setup();
        driver = new EdgeDriver();
    }

}
