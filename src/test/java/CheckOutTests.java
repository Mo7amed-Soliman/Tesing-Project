import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

public class CheckOutTests extends BaseTest {

    //verify when enter valid information , proceed to shipping page.
    @Test
    public void t3_validData()
    {
        homePage.cleanUpCart();
        MenJacketsPage menJacketsPage=homePage.clickOnMenTopsJacketsCategory();
        HyperionMenJacket hyperionMenJacket=menJacketsPage.clickOnFirstProduct();
        hyperionMenJacket.addItemsToCart("5");
        CartPage cartPage=hyperionMenJacket.navigateToCartPage();
        CheckOutPage checkOutPage=cartPage.proceedTOCheckOut();
        checkOutPage.waitUntilElementsLocated();
        checkOutPage.enterValidDataForFieldsInCaseSignUp("dr hossam office","building no.35 , 24st form 60st , gharbia , egypt",
                "United States","California","Asafra","29153","01157896875");
        checkOutPage.setFreeShipping();
        PaymentPage paymentPage=checkOutPage.clickOnNextButton();
        String currentURL=paymentPage.getCurrentUrl();
        Assert.assertEquals(currentURL,"https://demo-m2.bird.eu/checkout/#payment","error , redirection failed");
    }

    //verify when total price of products less than 100 , freeshipping is not allowed
    @Test
    public void t1_checkNotValidityOfFreeShipping()
    {
        MenJacketsPage menJacketsPage=homePage.clickOnMenTopsJacketsCategory();
        HyperionMenJacket hyperionMenJacket=menJacketsPage.clickOnFirstProduct();
        hyperionMenJacket.addItemsToCart("1");
        CartPage cartPage=hyperionMenJacket.navigateToCartPage();
        CheckOutPage checkOutPage=cartPage.proceedTOCheckOut();
        checkOutPage.waitUntilElementsLocated();
        String shippingMethod=checkOutPage.getShippingMethod();
        Assert.assertNotEquals(shippingMethod,"Free Shipping");
    }
    /*
    //verify when left fields blank , error is appeared
    @Test
    public void t2_fieldRequired()
    {
        homePage.cleanUpCart();
        MenJacketsPage menJacketsPage=homePage.clickOnMenTopsJacketsCategory();
        HyperionMenJacket hyperionMenJacket=menJacketsPage.clickOnFirstProduct();
        hyperionMenJacket.addItemsToCart("1");
        CartPage cartPage=hyperionMenJacket.navigateToCartPage();
        CheckOutPage checkOutPage=cartPage.proceedTOCheckOut();
        checkOutPage.waitUntilElementsLocated();
        checkOutPage.setFreeShipping();
        //PaymentPage shippingPage=checkOutPage.clickOnNextButton();
        String error=checkOutPage.getFieldsRequired();
        Assert.assertTrue(error.contains("required"));
    }

     */

    //verify when enter zipcode with invalid format , an error appears
    @Test
    public void t4_zipCodeFormatValidity()
    {
        homePage.cleanUpCart();
        MenJacketsPage menJacketsPage=homePage.clickOnMenTopsJacketsCategory();
        HyperionMenJacket hyperionMenJacket=menJacketsPage.clickOnFirstProduct();
        hyperionMenJacket.addItemsToCart("5");
        CartPage cartPage=hyperionMenJacket.navigateToCartPage();
        CheckOutPage checkOutPage=cartPage.proceedTOCheckOut();
        checkOutPage.waitUntilElementsLocated();
        checkOutPage.enterValidDataForFields("291553");
        String error=checkOutPage.errorInvalidZipCode();
        Assert.assertTrue(error.contains("Provided Zip/Postal Code seems to be invalid"));
    }
    @BeforeMethod
    public void login()
    {
        SignInPage signInPage=homePage.clickOnSignInButton();
        homePage=signInPage.loginToHomePage(email,password);
    }
}