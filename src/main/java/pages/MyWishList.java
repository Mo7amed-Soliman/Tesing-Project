package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MyWishList {
    WebDriver driver;
    WebDriverWait wait;
    private By productAddedSuccessfully=By.cssSelector("div[data-bind=\"html: $parent.prepareMessageForHtml(message.text)\"]");
    public MyWishList(WebDriver driver)
    {
        this.driver=driver;
    }
    public String getProductName()
    {
        wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(productAddedSuccessfully));
        return driver.findElement(productAddedSuccessfully).getText();
    }
}
