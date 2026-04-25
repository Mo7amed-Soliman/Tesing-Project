package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PaymentPage {
    WebDriver driver;
    WebDriverWait wait;
    private By shippingMethod=By.cssSelector("[data-bind=\"text: getShippingMethodTitle()\"]");
    private By placeOrder=By.cssSelector("[title=\"Place Order\"]");
    private By chkMoneyOrder=By.xpath("//*[@id=\"checkmo\"]");
    private By payPalPayment=By.cssSelector("[value=\"monetico_onetime\"]");
    private By placeOrderTwo=By.xpath("//*[@id=\"checkout-payment-method-load\"]/div/div/div[3]/div[2]/div[4]/div/button/span");

    public PaymentPage(WebDriver driver)
    {
        this.driver=driver;
    }
    public String getCurrentUrl()
    {
        wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(shippingMethod));
        return driver.getCurrentUrl();
    }
    public void selectPaymentMethod(String payment)
    {
        wait=new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(chkMoneyOrder));
        if (payment.equalsIgnoreCase("Cash On Delivery"))
        {
            driver.findElement(chkMoneyOrder).click();
        }
        else if (payment.equalsIgnoreCase("PayPal"))
        {
            driver.findElement(payPalPayment).click();
        }
    }
    public SuccessOrder clickplaceOrder()
    {
        wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(placeOrder));
        driver.findElement(placeOrder).click();
        return new SuccessOrder(driver);
    }
    public PayPalPage directUser()
    {
        wait=new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("estimated-delivery-date")));
        driver.findElement(placeOrderTwo).click();
        return new PayPalPage(driver);
    }
}
