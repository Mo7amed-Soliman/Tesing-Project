import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;


public class AddToWishListFunction extends BaseTest{

    @Test
    public void addToWishListSuccessfully()
    {
        MenJacketsPage menJacketsPage=homePage.clickOnMenTopsJacketsCategory();
        MyWishList myWishList=menJacketsPage.addToWishList();
        String productNameFromWishList=myWishList.getProductName();
        Assert.assertTrue(productNameFromWishList.contains("Hyperion Elements"),"error, items aren't matched");
    }
    @BeforeMethod
    public void login()
    {
        SignInPage signInPage=homePage.clickOnSignInButton();
        homePage=signInPage.loginToHomePage(email,password);
    }
}
