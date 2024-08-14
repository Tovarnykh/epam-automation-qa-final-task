package com.epam.tovarnykh.ta.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.edge.EdgeDriver;

class EdgeDriverManager extends DriverManager {

    public EdgeDriverManager() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
    }

}
