package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CreateAccountPage {
    private WebDriver driver;
    private WebDriverWait wait;
    public CreateAccountPage(WebDriver driver)
    {
        this.driver=driver;
    }
    private By firstName = By.id("firstname");
    private By lastName = By.id("lastname");
    private By signUpSubscribtion = By.name("is_subscribed");
    private By allowRemoteShoppingAssistance = By.name("assistance_allowed_checkbox");
    private By emailField = By.id("email_address");
    private By passwordField = By.id("password");
    private By confirmPassword = By.id("password-confirmation");
    private By showPassword = By.id("show-password");
    private By createAccountButton = By.cssSelector("[title=\"Create an Account\"]");
    private By fnameIsRequired = By.id("firstname-error");
    private By bannerEmailExists=By.cssSelector("[data-bind=\"html: $parent.prepareMessageForHtml(message.text)\"]");
    private By bannerEnterValidEmail=By.id("email_address-error");
    private By errorEnterTheSamePassword=By.id("password-confirmation-error");
    private By errorPasswordLength=By.id("password-error");
    public void chkSignUpSubscribtion()
    {
        driver.findElement(signUpSubscribtion).click();
    }
    public void chkAllowRemoteShoppingAssistance()
    {
        driver.findElement(allowRemoteShoppingAssistance).click();
    }

    public void clickShowPasswordButton()
    {
        driver.findElement(showPassword).click();
    }
    public String bannerAppearsInformUserEmailAlreadyExist()
    {
        wait=new WebDriverWait(driver, Duration.ofSeconds(1000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(bannerEmailExists));
        String bannerEmailExistsMssg=driver.findElement(bannerEmailExists).getText();
        return bannerEmailExistsMssg;
    }
    public String errorAppearsInformUserFieldsAreRequired()
    {
        wait=new WebDriverWait(driver, Duration.ofSeconds(1000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(fnameIsRequired));
        String errormssgFnameIsRequired=driver.findElement(fnameIsRequired).getText();
        return errormssgFnameIsRequired;
    }
    public String errorAppearsInformUserToEnterValidEmail()
    {
        wait=new WebDriverWait(driver, Duration.ofSeconds(1000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(bannerEnterValidEmail));
        String errormssgEnterValidEmail=driver.findElement(bannerEnterValidEmail).getText();
        return errormssgEnterValidEmail;
    }
    public boolean chkshowPassword()
    {
        wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        boolean passwordAppear=driver.findElement(passwordField).isDisplayed();
        return passwordAppear;
    }
    public String chkpasswordMatch()
    {
        wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorEnterTheSamePassword));
        String validityOfPasswordMatching=driver.findElement(errorEnterTheSamePassword).getText();
        return validityOfPasswordMatching;

    }
    public String chkpasswordLength()
    {
        wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorPasswordLength));
        String validityOfPasswordLength=driver.findElement(errorPasswordLength).getText();
        return validityOfPasswordLength;

    }
    public MyAccountPage accIsCreated(String fname,String lname,String email,String password,String cpassword)
    {
        driver.findElement(firstName).sendKeys(fname);
        driver.findElement(lastName).sendKeys(lname);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(confirmPassword).sendKeys(cpassword);
        driver.findElement(createAccountButton).click();
        return new MyAccountPage(driver);
    }
    public void accIsNotCreated(String fname,String lname,String email,String password,String cpassword)
    {
        driver.findElement(firstName).sendKeys(fname);
        driver.findElement(lastName).sendKeys(lname);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(confirmPassword).sendKeys(cpassword);
        driver.findElement(createAccountButton).click();
    }
    public void accIsNotCreated()
    {
        driver.findElement(createAccountButton).click();
    }
    public void accIsNotCreated(String password,String cpassword)
    {
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(confirmPassword).sendKeys(cpassword);
        driver.findElement(showPassword).click();
    }
}

