package pageObjects;

import org.openqa.selenium.*;
import java.util.List;

public class AmpkartPage {
    WebDriver driver;

    // ✅ Normal menu links
    By menuOptions = By.xpath("//ul[@class='shop-nav-list mr-auto']/li/a");

    // ✅ Dropdown buttons
    By priceListDropdown = By.xpath("//button[@aria-controls='shop-nav-submenu-4']");
    By brandsDropdown = By.xpath("//button[@aria-controls='shop-nav-submenu-6']");

    // ✅ First submenu link inside dropdowns
    By firstPriceListOption = By.xpath("//div[@id='shop-nav-submenu-4']//a");
    By firstBrandOption = By.xpath("//div[@id='shop-nav-submenu-6']//a");

    public AmpkartPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getAllOptions() {
        return driver.findElements(menuOptions);
    }

    public WebElement getPriceListDropdown() {
        return driver.findElement(priceListDropdown);
    }

    public WebElement getBrandsDropdown() {
        return driver.findElement(brandsDropdown);
    }

    public WebElement getFirstPriceListOption() {
        return driver.findElement(firstPriceListOption);
    }

    public WebElement getFirstBrandOption() {
        return driver.findElement(firstBrandOption);
    }
}
