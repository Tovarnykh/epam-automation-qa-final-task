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

    /**
     * Constructor for the InventoryPage class.
     * Initializes the page elements using Selenium's PageFactory.
     *
     * @param driver The WebDriver instance used to interact with the web page.
     */
    public InventoryPage(WebDriver driver) {
        super(driver);

        PageFactory.initElements(driver, this);
    }

    /**
     * Opens the Inventory Page by navigating to its URL.
     * Logs the action of opening the page.
     *
     * @return The current instance of InventoryPage.
     */
    @Override
    public InventoryPage openPage() {
        driver.get(INVENTORY_PAGE_LINK);
        logger.info("Inventory page is open");
        return this;
    }

    /**
     * Retrieves the title text from the dashboard of the Inventory Page.
     * Logs the action of retrieving the title.
     *
     * @return The text of the dashboard title.
     * @throws NoSuchElementException if the title element is not found on the page.
     */
    public String retrieveDashboardTitle() throws NoSuchElementException {
        logger.info("Dashboard title sent");
        return title.getText();
    }

}
