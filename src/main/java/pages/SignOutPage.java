package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignOutPage {

    private final WebDriver driver;
    public SignOutPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    private final By signOutMessage = By.cssSelector("[data-ui-id=\"page-title-wrapper\"]");
    private final By homeButton = By.id("ui-id-3");



    // Actions
    public String getSignOutMessage() {
        return driver.findElement(signOutMessage).getText();
    }

    public HomePage clickHomeButton() {
        driver.findElement(homeButton).click();
        return new HomePage(driver);
    }
}
