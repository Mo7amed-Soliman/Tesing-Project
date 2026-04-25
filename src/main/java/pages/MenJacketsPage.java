package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MenJacketsPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By sortDropDownMenu=By.id("sorter");
    private By minimumPriceInPage=By.cssSelector("span[data-price-amount=\"45\"]>span");
    private By firstProductByLetter=By.xpath("//*[@id=\"product-item-info_285\"]/div/strong/a");
    private By selectGreenColorForHyperion=By.xpath("//*[@id=\"product-item-info_285\"]/div/div[3]/div/div/div[1]");
    private By selectBlueColorForTaurus=By.xpath("/html/body/div[6]/main/div[3]/div[1]/div[3]/ol/li[1]/div/" +
            "div/div[3]/div[1]/div/div[1]");
    private By selectSizeXsmallForTaurus=By.xpath("/html/body/div[6]/main/div[3]/div[1]/" +
            "div[3]/ol/li[1]/div/div/div[3]/div[2]/div/div[1]");
    private By selectXsmallForHyperion=By.xpath("/html/body/div[6]/main/div[3]/div[1]/div[3]/" +
            "ol/li[2]/div/div/div[3]/div[2]/div/div[1]");
    private By selectFirstProduct=By.id("product-item-info_285");
    private By productSelected=By.cssSelector("div[id=\"product-item-info_285\"]>div>strong>a");
    private By addToWishList=By.xpath("//*[@id=\"product-item-info_285\"]/div/div[4]/div/div[2]/a[1]");
    private By taurusJacket=By.id("product-item-info_349");
    private By addToCmpreTaurus=By.xpath("//*[@id=\"product-item-info_349\"]/div/div[4]/div/div[2]/a[2]");
    private By addToCmpreHyperion=By.xpath("//*[@id=\"product-item-info_285\"]/div/div[4]/div/div[2]/a[2]");
    private By comparisonList=By.linkText("comparison list");
    private By bannerAddToComparisonList=By.xpath("//*[@id=\"maincontent\"]/div[2]/div[2]/div/div/div");
    private By taurusJacketTitle=By.xpath("//*[@id=\"product-item-info_349\"]/div/strong/a");

    public MenJacketsPage(WebDriver driver)
    {
        this.driver=driver;
    }
    public void selectFromSortingDropDownMenu(String sort)
    {
        Select drpDwnMenu=new Select(driver.findElement(sortDropDownMenu));
        drpDwnMenu.selectByVisibleText(sort);
    }
    public String showMinimumPriceInPage()
    {
        String minimumPrice=driver.findElement(minimumPriceInPage).getText();
        return minimumPrice;
    }
    public String showAscendingOrderForProducts()
    {
        String firstProduct=driver.findElement(firstProductByLetter).getText();
        return firstProduct;
    }

    public HyperionMenJacket clickOnFirstProduct()
    {
        driver.findElement(selectFirstProduct).click();
        return new HyperionMenJacket(driver);
    }
    public MyWishList addToWishList()
    {
        WebElement product=driver.findElement(selectFirstProduct);
        Actions actions=new Actions(driver);
        actions.moveToElement(product).perform();
        driver.findElement(selectXsmallForHyperion).click();
        driver.findElement(selectGreenColorForHyperion).click();
        driver.findElement(addToWishList).click();
        return new MyWishList(driver);
    }
}
