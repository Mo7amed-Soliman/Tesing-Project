package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignInPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public SignInPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators
    private final By emailField = By.id("email");
    private final By passwordField = By.id("password");
    private final By showPassword = By.id("show-password");
    private final By signInButton = By.cssSelector("button.login.primary");
    private final By forgotPasswordLink = By.linkText("Forgot Your Password?");
    private final By emailError = By.id("email-error");
    private final By commonMessage = By.cssSelector("[data-bind=\"html: $parent.prepareMessageForHtml(message.text)\"]");

    // Actions
    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickShowPassword() {
        driver.findElement(showPassword).click();
    }

    public void clickSignIn() {
        driver.findElement(signInButton).click();
    }

    public ForgotPasswordPage goToForgotPasswordPage() {
        driver.findElement(forgotPasswordLink).click();
        return new ForgotPasswordPage(driver);
    }

    public boolean isErrorMessageForInvalidLoginVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(commonMessage));
        String message = driver.findElement(commonMessage).getText();
        return message.contains("The account sign-in was incorrect") || message.contains("Incorrect CAPTCHA");
    }

    public String getEmailFormatErrorMessage() {
        return driver.findElement(emailError).getText();
    }

    public boolean isPasswordVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        return driver.findElement(passwordField).isDisplayed();
    }

    public String getRequiredFieldError() {
        return driver.findElement(emailError).getText();
    }

    public MyAccountPage loginToMyAccount(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickSignIn();
        return new MyAccountPage(driver);
    }

    public HomePage loginToHomePage(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickSignIn();
        return new HomePage(driver);
    }

    public void loginWithInvalidData(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickSignIn();
    }

    public void loginWithEmptyFields() {
        clickSignIn();
    }
}
