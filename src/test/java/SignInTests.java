import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MyAccountPage;
import pages.SignInPage;

public class SignInTests extends BaseTest {

    @Test(description = "Verify successful login with valid email and password")
    public void testValidEmailAndPasswordLogin() {
        SignInPage signInPage = homePage.clickOnSignInButton();
        MyAccountPage myAccountPage = signInPage.loginToMyAccount(email, password);
        Assert.assertTrue(myAccountPage.signOutButtonAppears(), "Sign out button should appear after successful login.");
    }

    @Test(description = "Verify error message appears when invalid password is entered")
    public void testInvalidPassword() {
        SignInPage signInPage = homePage.clickOnSignInButton();
        signInPage.loginWithInvalidData(email, "wrong_password_123");
        Assert.assertTrue(signInPage.isErrorMessageForInvalidLoginVisible(), "Error message should appear for invalid login.");
    }

    @Test(description = "Verify error message appears when invalid email is entered")
    public void testInvalidEmail() {
        SignInPage signInPage = homePage.clickOnSignInButton();
        signInPage.loginWithInvalidData("invalid@example.com", password);
        Assert.assertTrue(signInPage.isErrorMessageForInvalidLoginVisible(), "Error message should appear for invalid login.");
    }

    @Test(description = "Verify error message appears when SQL injection input is used in email field")
    public void testSqlInjectionInput() {
        SignInPage signInPage = homePage.clickOnSignInButton();
        signInPage.loginWithInvalidData("\"' OR '1'='" +
                "1'\"", password);
        String errorMsg = signInPage.getEmailFormatErrorMessage();
        Assert.assertEquals(errorMsg, "Please enter a valid email address (Ex: johndoe@domain.com).", "SQL injection should be blocked.");
    }

    @Test(description = "Verify that password becomes visible when 'Show Password' is clicked")
    public void testShowPasswordFunctionality() {
        SignInPage signInPage = homePage.clickOnSignInButton();
        signInPage.enterPassword(password);
        signInPage.clickShowPassword();
        Assert.assertTrue(signInPage.isPasswordVisible(), "Password should be visible after clicking 'Show Password'.");
    }

    @Test(description = "Verify error appears when trying to sign in with empty fields")
    public void testFieldsRequiredError() {
        SignInPage signInPage = homePage.clickOnSignInButton();
        signInPage.loginWithEmptyFields();
        String errorMsg = signInPage.getRequiredFieldError();
        Assert.assertEquals(errorMsg, "This is a required field.", "Required field error should appear.");
    }
}
