package com.epam.tovarnykh.ta.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverManager extends DriverManager {

    ChromeOptions options;

    public ChromeDriverManager() {
        this.options = new ChromeOptions();

        WebDriverManager.chromedriver().setup();
        options.addArguments("--disable-search-engine-choice-screen");

        driver = new ChromeDriver(options);
    }

}
