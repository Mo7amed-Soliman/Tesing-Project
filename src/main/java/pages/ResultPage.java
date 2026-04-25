package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class ResultPage {
    WebDriver driver;
    WebDriverWait wait;
    private By noResultsBar=By.xpath("//div[@class=\"message notice\"]/div[1]");
    private By searchResults=By.xpath("//li[@class=\"item product product-item\"]");
    private By didYouMeanMessage=By.cssSelector("dl[class=\"block\"]>dt");
    public ResultPage(WebDriver driver)
    {
        this.driver=driver;
    }
    public List<WebElement> returnResults()
    {
        List<WebElement> results=driver.findElements(searchResults);
        return results;
    }
    public String getErrorNoResultsText()
    {
        wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(noResultsBar));
        return driver.findElement(noResultsBar).getText();
    }
    public String getDidYouMeanMessage()
    {
        return driver.findElement(didYouMeanMessage).getText();
    }
}
