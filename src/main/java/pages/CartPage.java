package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public CartPage(WebDriver driver)
    {
        this.driver=driver;
    }
    private By productTitle=By.linkText("Hyperion Elements Jacket");

    private By subTotalOrder=By.xpath("//td[@class=\"col subtotal\"]/span/span/span");

    private By totalPriceOfOrder=By.cssSelector("[data-bind=\"text: getValue()\"]");
    private By quantityField=By.cssSelector("[title=\"Qty\"]");
    private By updateQuantityField=By.name("update_cart_action");
    private By dateShipped=By.id("preparation-time-cart");
    private By removeItem=By.cssSelector("[title=\"Remove item\"]");
    private By youHaveNoItems=By.xpath("//div[@class=\"cart-empty\"]/p[1]");
    private By checkOutButton=By.cssSelector("[data-role=\"proceed-to-checkout\"]");

    public String getProductTitle()
    {
        return driver.findElement(productTitle).getText();
    }
    public String subTotalPrice()
    {
        wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(subTotalOrder));
        return driver.findElement(subTotalOrder).getText();
    }
    public String totalPriceOfOrder()
    {
        wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(totalPriceOfOrder));
        return driver.findElement(totalPriceOfOrder).getText();
    }
    public void setQuantityField(String quantity)
    {
        wait=new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(dateShipped));
        driver.findElement(quantityField).clear();
        driver.findElement(quantityField).sendKeys(quantity);
    }
    public void refreshPage()
    {
        driver.navigate().refresh();
    }
    public void clickOnUpdatequantity()
    {
        wait=new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(quantityField));
        wait=new WebDriverWait(driver,Duration.ofSeconds(10));
        driver.findElement(updateQuantityField).click();
    }
    public void removeItemsFromCart()
    {
        driver.findElement(removeItem).click();
    }
    public String errorYouHaveNoItems()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(youHaveNoItems));
        return messageElement.getText();
    }
    public CheckOutPage proceedTOCheckOut()
    {
        driver.findElement(checkOutButton).click();
        return new CheckOutPage(driver);
    }

}
