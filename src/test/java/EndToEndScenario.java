import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.*;

public class EndToEndScenario extends BaseTest {

    SoftAssert sftassrt=new SoftAssert();
    @Test
    public void endToEndScenario()
    {
        CreateAccountPage createAccount=homePage.clickOnCreateAccountButton();
        MyAccountPage myAccountPage =createAccount.accIsCreated("ahmed","eid","ahmedeid00000500500@gmail.com",password,password);
        String thanksForRegisterMessage=myAccountPage.bannerAppearsThanksUserForRegister();
        sftassrt.assertEquals(thanksForRegisterMessage,"Thank you for registering with Main Website Store.","Create an account unsuccessfully");
        homePage=myAccountPage.goToHomePage();
        MenJacketsPage menJacketsPage=homePage.clickOnMenTopsJacketsCategory();
        HyperionMenJacket hyperionMenJacket=menJacketsPage.clickOnFirstProduct();
        hyperionMenJacket.addItemsToCart("5");
        String titleNameFromProductPage=hyperionMenJacket.getTitleName();
        String totalPriceFromProductPage=hyperionMenJacket.getTotalPrice();
        CartPage cartPage=hyperionMenJacket.navigateToCartPage();
        String titleNameFromCartPage=cartPage.getProductTitle();
        String totalPriceFromCartPage=cartPage.subTotalPrice();
        sftassrt.assertEquals(titleNameFromProductPage,titleNameFromCartPage,"error , items aren't matched");
        sftassrt.assertEquals(totalPriceFromProductPage,totalPriceFromCartPage,"error , prices aren't matched");
        CheckOutPage checkOutPage=cartPage.proceedTOCheckOut();
        checkOutPage.waitUntilElementsLocated();
        checkOutPage.enterValidDataForFieldsInCaseSignUp("dr hossam office","building no.35 , 24st form 60st , gharbia , egypt",
                "United States","California","Asafra","29153","01157896875");
        checkOutPage.setFreeShipping();
        PaymentPage paymentPage=checkOutPage.clickOnNextButton();
        String currentURL=paymentPage.getCurrentUrl();
        sftassrt.assertEquals(currentURL,"https://demo-m2.bird.eu/checkout/#payment","error , redirection failed");
        paymentPage.selectPaymentMethod("Cash On Delivery");
        SuccessOrder successOrder=paymentPage.clickplaceOrder();
        String orderNo=successOrder.getOrderNumber();
        String thanksMessage=successOrder.getThanksMessage();
        sftassrt.assertTrue(orderNo.contains("Your order"));
        sftassrt.assertEquals(thanksMessage,"Thank you for your purchase!");
        sftassrt.assertAll();
    }
}
