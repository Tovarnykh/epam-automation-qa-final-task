package com.epam.tovarnykh.ta.saucedemo.page;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.epam.tovarnykh.ta.saucedemo.page.constants.InventoryPageConstants.DASHBOARD_TITLE_SELECTOR;
import static com.epam.tovarnykh.ta.saucedemo.page.constants.InventoryPageConstants.INVENTORY_PAGE_LINK;

public class InventoryPage extends AbstractPage{

    @FindBy(xpath = DASHBOARD_TITLE_SELECTOR)
    private WebElement title;

    public InventoryPage(WebDriver driver) {
        super(driver);

        PageFactory.initElements(driver, this);
    }

    @Override
    public InventoryPage openPage() {
        driver.get(INVENTORY_PAGE_LINK);
        logger.info("Inventory page is open");
        return this;
    }

    public String getTitle() throws NoSuchElementException {
        logger.info("Dashboard title sent");
        return title.getText();
    }

}
