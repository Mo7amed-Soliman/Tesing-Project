package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ForgotPasswordPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators
    private final By emailField = By.name("email");
    private final By captchaImage = By.cssSelector("[alt=\"Please type the letters and numbers below\"]");
    private final By captchaField = By.id("captcha_user_forgotpassword");
    private final By resetPasswordButton = By.cssSelector("form.forget button#send2");
    private final By errorFieldRequired = By.id("email_address-error");

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // Initialize WebDriverWait once
    }

    // Actions
    public void setEmailField(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void setCaptchaField(String captcha) {
        waitUntilCaptchaImageIsVisible();  // Ensure captcha is visible before interacting
        driver.findElement(captchaField).sendKeys(captcha);
    }

    public void clickOnResetPasswordButton() {
        driver.findElement(resetPasswordButton).click();
    }

    public String getErrorMessageForRequiredFields() {
        return driver.findElement(errorFieldRequired).getText();
    }

    // Helper methods
    private void waitUntilCaptchaImageIsVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(captchaImage));
    }
}
