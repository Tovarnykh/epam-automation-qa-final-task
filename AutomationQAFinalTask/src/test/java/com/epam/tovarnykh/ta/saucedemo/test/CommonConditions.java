package com.epam.tovarnykh.ta.saucedemo.test;

import com.epam.tovarnykh.ta.driver.Browser;
import com.epam.tovarnykh.ta.driver.WebDriverFactory;
import com.epam.tovarnykh.ta.saucedemo.model.User;
import com.epam.tovarnykh.ta.saucedemo.model.config.ConfigLoaderUser;
import com.epam.tovarnykh.ta.saucedemo.page.InventoryPage;
import com.epam.tovarnykh.ta.saucedemo.page.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.stream.Stream;

public class CommonConditions {

    protected static final Logger logger = LoggerFactory.getLogger(LoginPage.class);

    protected WebDriver driver;
    protected LoginPage loginPage;
    protected InventoryPage inventoryPage;

    static Stream<User> provideUserConfigurations() throws IOException {
        return Stream.of(
                ConfigLoaderUser.loadConfig("qaUserProperties1.json"),
                ConfigLoaderUser.loadConfig("qaUserProperties2.json")
        );
    }

    @AfterEach
    public void tearDown(){
        driver.quit();
    }

    protected void setUpBrowser(Browser browser){
        driver = WebDriverFactory.getDriver(browser);
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
    }

}
