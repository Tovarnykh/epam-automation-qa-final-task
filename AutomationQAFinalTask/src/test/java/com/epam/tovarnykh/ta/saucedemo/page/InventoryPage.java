package com.epam.tovarnykh.ta.saucedemo.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.epam.tovarnykh.ta.saucedemo.constants.InventoryPageConstants.DASHBOARD_TITLE_SELECTOR;
import static com.epam.tovarnykh.ta.saucedemo.constants.InventoryPageConstants.INVENTORY_PAGE_LINK;
import static com.epam.tovarnykh.ta.saucedemo.constants.LoginPageConstants.LOGIN_BUTTON_SELECTOR;

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
        return this;
    }

    public String getTitle(){
        return title.getText();
    }

}
