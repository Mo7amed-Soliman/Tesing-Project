package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators
    private final By createAccountLink = By.linkText("Create an Account");
    private final By signInLink = By.linkText("Sign In");
    private final By menMenu = By.id("ui-id-13");
    private final By topsMenu = By.id("ui-id-14");
    private final By jacketsMenu = By.id("ui-id-15");
    private final By searchBar = By.id("search");
    private final By searchButton = By.cssSelector("[title=\"Search\"]");
    private final By outOfStockProduct = By.cssSelector("[alt=\"Josie Yoga Jacket\"]");
    private final By accountDropdown = By.cssSelector("[data-action=\"customer-menu-toggle\"]");
    private final By signOutLink = By.linkText("Sign Out");
    private final By cartIcon = By.className("showcart");
    private final By removeItemButton = By.cssSelector("[title=\"Remove item\"]");
    private final By confirmRemoveButton = By.xpath("//span[text()=\"OK\"]");
    private final By cartItemCount = By.xpath("//*[@id=\"html-body\"]/div[6]/header/div[2]/div[1]/a/span[2]");
    private final By signOutMessage = By.cssSelector("[data-ui-id=\"page-title-wrapper\"]");
    private final By homeButton = By.id("ui-id-3");



    // Navigation methods
    public SignInPage clickOnSignInButton() {
        driver.findElement(signInLink).click();
        return new SignInPage(driver);
    }

    public CreateAccountPage clickOnCreateAccountButton() {
        driver.findElement(createAccountLink).click();
        return new CreateAccountPage(driver);
    }

    public MenJacketsPage clickOnMenTopsJacketsCategory() {
        Actions actions = new Actions(driver);

        WebElement menElement = wait.until(ExpectedConditions.visibilityOfElementLocated(menMenu));
        actions.moveToElement(menElement).perform();

        WebElement topsElement = wait.until(ExpectedConditions.visibilityOfElementLocated(topsMenu));
        actions.moveToElement(topsElement).perform();

        WebElement jacketsElement = wait.until(ExpectedConditions.elementToBeClickable(jacketsMenu));
        actions.moveToElement(jacketsElement).click().perform();

        return new MenJacketsPage(driver);
    }

    public void searchForItem(String item) {
        driver.findElement(searchBar).sendKeys(item);
    }

    public ResultPage clickOnSearchButton() {
        driver.findElement(searchButton).click();
        return new ResultPage(driver);
    }

    public ProductOutStock moveToProductOutOfStock() {
        driver.findElement(outOfStockProduct).click();
        return new ProductOutStock(driver);
    }

    public void clickOnSignOutButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(accountDropdown));
        driver.findElement(accountDropdown).click();
        driver.findElement(signOutLink).click();

    }

    public void cleanUpCart() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartItemCount));
        driver.findElement(cartIcon).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(removeItemButton));
        driver.findElement(removeItemButton).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmRemoveButton));
        driver.findElement(confirmRemoveButton).click();
    }

    public void clickSignOutButton() {
        driver.findElement(homeButton).click();
    }

    public String getSignOutMessage() {
        return driver.findElement(signOutMessage).getText();
    }
}
