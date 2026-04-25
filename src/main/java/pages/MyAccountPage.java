package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class MyAccountPage {
    private WebDriver driver;
    private WebDriverWait wait;
    public MyAccountPage(WebDriver driver)
    {
        this.driver=driver;
    }
    private By bannerRegisterSuccess=By.cssSelector("[data-bind=\"html: $parent.prepareMessageForHtml(message.text)\"]");
    private By drpDownMenu=By.cssSelector("[data-action=\"customer-menu-toggle\"]");
    private By signOutButton=By.linkText("Sign Out");
    private By homePage=By.cssSelector("a[class=\"level0 first level-top ui-menu-item-wrapper\"]>span");


    public String bannerAppearsThanksUserForRegister()
    {
        wait=new WebDriverWait(driver, Duration.ofSeconds(1000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(bannerRegisterSuccess));
        String bannerThanksForRegisterMssg=driver.findElement(bannerRegisterSuccess).getText();
        return bannerThanksForRegisterMssg;
    }
    public Boolean signOutButtonAppears()
    {
        wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(drpDownMenu));
        driver.findElement(drpDownMenu).click();
        boolean signOutAppears=driver.findElement(signOutButton).getText().matches("Sign Out");
        return signOutAppears;
    }
    public SignOutPage clickOnSignOutButton()
    {
        wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(drpDownMenu));
        driver.findElement(drpDownMenu).click();
        driver.findElement(signOutButton).click();
        return new SignOutPage(driver);
    }
    public HomePage goToHomePage()
    {
        driver.findElement(homePage).click();
        return new HomePage(driver);
    }

}
