import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ForgotPasswordPage;
import pages.SignInPage;

public class ForgotPasswordTest extends BaseTest {


    //verify when left fields blank , an error appears
    @Test
    public void resetPasswordEmailSentSuccessfully(){
        SignInPage signInPage=homePage.clickOnSignInButton();
        ForgotPasswordPage forgotPasswordPage=signInPage.goToForgotPasswordPage();
        forgotPasswordPage.clickOnResetPasswordButton();
        String fieldsAreRequired=forgotPasswordPage.getErrorMessageForRequiredFields();
        Assert.assertTrue(fieldsAreRequired.matches("This is a required field."));
    }
}
