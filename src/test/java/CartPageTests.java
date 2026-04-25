import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

public class CartPageTests extends BaseTest {

    //verify when Add products to the cart page , already added
    @Test
    public void t1_chkProductAddedSuccessfully()
    {
        MenJacketsPage menJacketsPage=homePage.clickOnMenTopsJacketsCategory();
        HyperionMenJacket hyperionMenJacket=menJacketsPage.clickOnFirstProduct();
        hyperionMenJacket.addItemsToCart("5");
        String titleNameFromProductPage=hyperionMenJacket.getTitleName();
        hyperionMenJacket.getTotalPrice();
        CartPage cartPage=hyperionMenJacket.navigateToCartPage();
        String titleNameFromCartPage=cartPage.getProductTitle();
        Assert.assertEquals(titleNameFromProductPage,titleNameFromCartPage,"error , items aren't matched");
    }

    //verify when enter quantity greater than or equal 4 , discount is applied
    @Test
    public void t2_chkApplyDiscount()
    {
        homePage.cleanUpCart();
        MenJacketsPage menJacketsPage=homePage.clickOnMenTopsJacketsCategory();
        HyperionMenJacket hyperionMenJacket=menJacketsPage.clickOnFirstProduct();
        hyperionMenJacket.addItemsToCart("4");
        CartPage cartPage=hyperionMenJacket.navigateToCartPage();
        cartPage.refreshPage();
        String subTotalPrice=cartPage.subTotalPrice();
        String totalPrice=cartPage.totalPriceOfOrder();
        Assert.assertNotEquals(subTotalPrice,totalPrice);
    }
//    //verify when enter quantity less than or equal 3 , discount isn't applied
    @Test
    public void t3_chknotApplyDiscount()
    {
        homePage.cleanUpCart();
        MenJacketsPage menJacketsPage=homePage.clickOnMenTopsJacketsCategory();
        HyperionMenJacket hyperionMenJacket=menJacketsPage.clickOnFirstProduct();
        hyperionMenJacket.addItemsToCart("3");
        CartPage cartPage=hyperionMenJacket.navigateToCartPage();
        cartPage.refreshPage();
        String subTotalPrice=cartPage.subTotalPrice();
        String totalPrice=cartPage.totalPriceOfOrder();
        Assert.assertEquals(subTotalPrice,totalPrice);
    }
    //verify when click on delete items , items deleted from cat
    @Test
    public void t4_deleteItemsFromCart()
    {
        MenJacketsPage menJacketsPage=homePage.clickOnMenTopsJacketsCategory();
        HyperionMenJacket hyperionMenJacket=menJacketsPage.clickOnFirstProduct();
        hyperionMenJacket.addItemsToCart("3");
        CartPage cartPage=hyperionMenJacket.navigateToCartPage();
        cartPage.removeItemsFromCart();
        cartPage.refreshPage();
        cartPage.refreshPage();
        cartPage.refreshPage();
        String noItemsFound=cartPage.errorYouHaveNoItems();
        Assert.assertEquals(noItemsFound,"You have no items in your shopping cart.","error, delete function isn't work");
    }



    @BeforeMethod
    public void login()
    {
        SignInPage signInPage=homePage.clickOnSignInButton();
        homePage=signInPage.loginToHomePage(email,password);
    }
}
