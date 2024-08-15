package com.epam.tovarnykh.ta.saucedemo.test;

import com.epam.tovarnykh.ta.driver.Browser;
import com.epam.tovarnykh.ta.driver.DriverSingleton;
import com.epam.tovarnykh.ta.saucedemo.model.User;
import com.epam.tovarnykh.ta.saucedemo.model.config.UserAdapter;
import com.epam.tovarnykh.ta.saucedemo.page.InventoryPage;
import com.epam.tovarnykh.ta.saucedemo.page.LoginPage;
import org.junit.jupiter.api.AfterEach;
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
                UserAdapter.loadConfig("qaUserProperties1.json"),
                UserAdapter.loadConfig("qaUserProperties2.json")
        );
    }

    @AfterEach
    public void tearDown(){
        driver.quit();
    }

    protected void setUpBrowser(Browser browser){
        driver = DriverSingleton.getDriver(browser);
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
    }

}
