package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AmpkartPage;
import base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.List;

public class AmpkartTest extends BaseClass {

    @Test
    public void verifyNavigationMenu() {
        AmpkartPage page = new AmpkartPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        String homeUrl = driver.getCurrentUrl();

        // ✅ Normal menu items
        int optionCount = page.getAllOptions().size();
        for (int i = 0; i < optionCount; i++) {
            // Re-fetch fresh list each time to avoid stale references
            List<WebElement> options = page.getAllOptions();
            WebElement option = options.get(i);

            String optionText = option.getText().trim();
            System.out.println("Checking menu option: " + optionText);

            Assert.assertTrue(option.isDisplayed(), optionText + " is not visible");
            Assert.assertTrue(option.isEnabled(), optionText + " is not enabled");

            option.click();
            wait.until(ExpectedConditions.urlContains("ampkart.com"));

            String currentUrl = driver.getCurrentUrl();

            if (optionText.equalsIgnoreCase("Home")) {
                Assert.assertEquals(currentUrl, homeUrl,
                        "Home menu should not redirect, but URL changed!");
                System.out.println("✅ Home menu verified: no redirection.");
            } else {
                Assert.assertNotEquals(currentUrl, homeUrl,
                        optionText + " menu did not redirect!");
                System.out.println("✅ " + optionText + " redirected successfully.");
                driver.navigate().back();
                wait.until(ExpectedConditions.urlToBe(homeUrl));
            }
        }

        // ✅ Dropdown: Price Lists → click first option
        page.getPriceListDropdown().click();
        WebElement firstPriceList = wait.until(ExpectedConditions.visibilityOf(page.getFirstPriceListOption()));
        String firstPriceText = firstPriceList.getText().trim();
        firstPriceList.click();
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(homeUrl)));
        System.out.println("✅ Price Lists → clicked first option: " + firstPriceText);
        driver.navigate().back();
        wait.until(ExpectedConditions.urlToBe(homeUrl));

        // ✅ Dropdown: Brands → click first option
        page.getBrandsDropdown().click();
        WebElement firstBrand = wait.until(ExpectedConditions.visibilityOf(page.getFirstBrandOption()));
        String firstBrandText = firstBrand.getText().trim();
        firstBrand.click();
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(homeUrl)));
        System.out.println("✅ Brands → clicked first option: " + firstBrandText);
        driver.navigate().back();
        wait.until(ExpectedConditions.urlToBe(homeUrl));
    }
}
