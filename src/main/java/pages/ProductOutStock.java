package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductOutStock {
    WebDriver driver;
    WebDriverWait wait;
    public ProductOutStock(WebDriver driver)
    {
        this.driver=driver;
    }
    private By notifyUser=By.className("primary");
    private By usrEmail=By.id("email-stock-alert");
    private By notifyButton=By.cssSelector("[data-role=\"action\"]");
    private By errorYouAlreadySubscribe=By.id("email_address-error");
    private By subscribeSuccess=By.id("cisa-succes");

    public void clickOnNotifyButton()
    {
        driver.findElement(notifyUser).click();
    }

    public void setusrEmail(String email)
    {
        wait=new WebDriverWait(driver, Duration.ofSeconds(1000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(usrEmail));
        driver.findElement(usrEmail).clear();
        driver.findElement(usrEmail).sendKeys(email);
    }
    public void clickOnNotifyMe()
    {
        wait=new WebDriverWait(driver, Duration.ofSeconds(1000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(notifyButton));
        driver.findElement(notifyButton).click();
    }
    public String getSubscribeSuccess()
    {
        wait=new WebDriverWait(driver,Duration.ofSeconds(1000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(subscribeSuccess));
        return driver.findElement(subscribeSuccess).getText();
    }
    public String getAlertAlreadySubscribed()
    {
        wait=new WebDriverWait(driver,Duration.ofSeconds(1000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorYouAlreadySubscribe));
        return driver.findElement(errorYouAlreadySubscribe).getText();
    }
}
