import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

public class PaymentTests extends BaseTest {


    //verify when click on  check money then placeholder , order placed
    @Test
    public void checkSuccessOfOrder()
    {
        homePage.cleanUpCart();
        MenJacketsPage menJacketsPage=homePage.clickOnMenTopsJacketsCategory();
        HyperionMenJacket hyperionMenJacket=menJacketsPage.clickOnFirstProduct();
        hyperionMenJacket.addItemsToCart("5");
        CartPage cartPage=hyperionMenJacket.navigateToCartPage();
        CheckOutPage checkOutPage=cartPage.proceedTOCheckOut();
        checkOutPage.waitUntilElementsLocated();
        checkOutPage.enterValidDataForFieldsInCaseSignUp("dr hossam office"
                ,"building no.35 , 24st form 60st , gharbia , egypt",
                "United States","California","Asafra","29153"
                ,"01157896875");
        checkOutPage.setFreeShipping();
        PaymentPage paymentPage=checkOutPage.clickOnNextButton();
        paymentPage.selectPaymentMethod("Cash On Delivery");
        SuccessOrder successOrder=paymentPage.clickplaceOrder();
        String orderNo=successOrder.getOrderNumber();
        String thanksMessage=successOrder.getThanksMessage();
        Assert.assertTrue(orderNo.contains("Your order "));
        Assert.assertEquals(thanksMessage,"Thank you for your purchase!");
    }

    //verify when enter payment method with payment account , page redirect user to pay on it
    @Test
    public void redirectUserToPay()
    {
        MenJacketsPage menJacketsPage=homePage.clickOnMenTopsJacketsCategory();
        HyperionMenJacket hyperionMenJacket=menJacketsPage.clickOnFirstProduct();
        hyperionMenJacket.addItemsToCart("5");
        CartPage cartPage=hyperionMenJacket.navigateToCartPage();
        CheckOutPage checkOutPage=cartPage.proceedTOCheckOut();
        checkOutPage.waitUntilElementsLocated();
        checkOutPage.setFreeShipping();
        PaymentPage paymentPage=checkOutPage.clickOnNextButton();
        paymentPage.selectPaymentMethod("PayPal");
        PayPalPage payPalPage=paymentPage.directUser();
         String paymentGateWay=payPalPage.gatewayOfPaymet();
        Assert.assertEquals(paymentGateWay,"https://p.monetico-services.com/test/paiement.cgi");
    }
    @BeforeMethod
    public void login()
    {
        SignInPage signInPage=homePage.clickOnSignInButton();
        homePage=signInPage.loginToHomePage(email,password);
    }
}
