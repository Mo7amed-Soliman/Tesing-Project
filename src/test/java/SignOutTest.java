import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.SignInPage;


public class SignOutTest extends BaseTest {

    @BeforeMethod(description = "Login before each test to ensure session is active")
    public void login() {
        SignInPage signInPage = homePage.clickOnSignInButton();
        homePage = signInPage.loginToHomePage(email, password);
    }

    @Test(description = "Verify user is signed out successfully when clicking on logout button")
    public void testSignOutSuccess() {
        homePage.clickOnSignOutButton();
        String message = homePage.getSignOutMessage();
        Assert.assertEquals(message, "You are signed out", "Sign out message did not match expected text.");
        homePage.clickSignOutButton();
    }
}
