package com.epam.tovarnykh.ta.saucedemo.test;

import com.epam.tovarnykh.ta.driver.Browser;
import com.epam.tovarnykh.ta.driver.WebDriverFactory;
import com.epam.tovarnykh.ta.saucedemo.model.User;
import com.epam.tovarnykh.ta.saucedemo.model.config.ConfigLoaderUser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.stream.Stream;

public class CommonConditions {

    protected WebDriver driver;

    static Stream<User> provideUserConfigurations() throws IOException {
        return Stream.of(
                ConfigLoaderUser.loadConfig("qaUserProperties1.json"),
                ConfigLoaderUser.loadConfig("qaUserProperties2.json")
        );
    }

    @BeforeEach
    public void setUp(){
        driver = WebDriverFactory.getDriver(Browser.EDGE);
    }

    @AfterEach
    public void tearDown(){
        driver.quit();
    }

}
