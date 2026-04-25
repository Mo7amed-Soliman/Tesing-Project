package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ComparisonList {
    WebDriver driver;
    WebDriverWait wait;
    public ComparisonList(WebDriver driver)
    {
        this.driver=driver;
    }
    private By hyperionJacket=By.xpath("//*[@id=\"product-comparison\"]/tbody[1]/tr/td[1]/strong/a");
    private By taurusJacket=By.xpath("//*[@id=\"product-comparison\"]/tbody[1]/tr/td[2]/strong/a");
    public String getHyperionJacketTitle()
    {
        return driver.findElement(hyperionJacket).getText();
    }
    public String getTaurusJacketTitle()
    {
        return driver.findElement(taurusJacket).getText();
    }
    public int numbersOfHyperionJacket()
    {
        List<WebElement> hyperionJackets=driver.findElements(hyperionJacket);
        return hyperionJackets.size();
    }
}
