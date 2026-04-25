package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PayPalPage {
    WebDriver driver;
    WebDriverWait wait;
    public PayPalPage(WebDriver driver)
    {
        this.driver=driver;
    }
    private By eptClosed=By.xpath("//td[@id=\"saisie_cb\"]/div/p[1]");

    public String gatewayOfPaymet()
    {
        wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(eptClosed));
        return driver.getCurrentUrl();
    }


}
