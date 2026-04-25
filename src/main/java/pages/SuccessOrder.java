package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SuccessOrder {
    private WebDriver driver;
    private WebDriverWait wait;
    public SuccessOrder(WebDriver driver)
    {
        this.driver=driver;
    }
    private By thanksMessage=By.cssSelector("[data-ui-id=\"page-title-wrapper\"]");
    private By orderNumber=By.cssSelector("div[class=\"checkout-success\"]>p:nth-child(1)");

    public String getThanksMessage()
    {
        return driver.findElement(thanksMessage).getText();
    }
    public String getOrderNumber()
    {
        wait=new WebDriverWait(driver, Duration.ofSeconds(1000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(orderNumber));
        return driver.findElement(orderNumber).getText();
    }
}
