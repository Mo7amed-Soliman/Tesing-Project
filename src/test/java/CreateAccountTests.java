import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CreateAccountPage;
import pages.MyAccountPage;

public class CreateAccountTests extends BaseTest {
    //verify when enter confirm password different with password , error appears
    @Test
    public void t1_chkPasswordMatch()
    {
        CreateAccountPage createAccount=homePage.clickOnCreateAccountButton();
        createAccount.accIsNotCreated("Mohamed","Soliman",email,"mo7amed_soliman7",password);
        String errorOfPasswordsUnMatched=createAccount.chkpasswordMatch();
        Assert.assertEquals(errorOfPasswordsUnMatched,"Please enter the same value again.");
    }
    //verify when enter password with length smaller than or equal 7 digits , an error appears
    @Test
    public void t2_chkPasswordLength()
    {
        CreateAccountPage createAccount=homePage.clickOnCreateAccountButton();
        createAccount.accIsNotCreated("p_pet12","p_pet12");
        String errorOfPasswordLength=createAccount.chkpasswordLength();
        Assert.assertEquals(errorOfPasswordLength,"Minimum length of this field must be equal" +
                " or greater than 8 symbols. Leading and trailing spaces will be ignored.");
    }
    //verify when enter valid data , banner appears to cst "thanks for register"
    @Test (priority = 0)
    public void t3_CreateAccountSuccessfully()
    {
        CreateAccountPage createAccount=homePage.clickOnCreateAccountButton();
        MyAccountPage myAccountPage =createAccount.accIsCreated("Mohamed","Soliman","samy@gmail.com",password,password);
        String thanksForRegisterMessage=myAccountPage.bannerAppearsThanksUserForRegister();
        Assert.assertEquals(thanksForRegisterMessage,"Thank you for registering with Main Website Store.","Create an account unsuccessfully");
    }

    //verify when cst enter an email which is already exists , error appears
    @Test
    public void t4_EmailAlreadyExists()
    {
        CreateAccountPage createAccount=homePage.clickOnCreateAccountButton();
        createAccount.accIsNotCreated("Mohamed","Soliman",email,password,password);
        String thanksForRegisterMessage=createAccount.bannerAppearsInformUserEmailAlreadyExist();
        Assert.assertEquals(thanksForRegisterMessage,"There is already an account with this email address. If you are sure that it is your email address, " +
                "click here to get your password and access your account.","Error, create account shouldn't be done");
    }
    //verify when left fields blank , error appears
    @Test
    public void t5_leftFieldsBlank()
    {
        CreateAccountPage createAccount=homePage.clickOnCreateAccountButton();
        createAccount.accIsNotCreated();
        String fnameIsRequired=createAccount.errorAppearsInformUserFieldsAreRequired();
        Assert.assertEquals(fnameIsRequired,"This is a required field.","Error, create account shouldn't be done");
    }
    //verify when enter invalid format of email, error appears
    @Test
    public void t6_enterInvalidEmail()
    {
        CreateAccountPage createAccount=homePage.clickOnCreateAccountButton();
        createAccount.accIsNotCreated("Mohamed","Soliman","mohamed_soliman",password,password);
        String errorEnterValidEmail=createAccount.errorAppearsInformUserToEnterValidEmail();
        Assert.assertEquals(errorEnterValidEmail,"Please enter a valid email address (Ex: johndoe@domain.com)."
                ,"error , create an account shouldn't be done");
    }
    //verify when chk show password , password appears ( defect : security shouldn't get password from the field)
    @Test
    public void t7_chkShowPassword()
    {
        CreateAccountPage createAccount=homePage.clickOnCreateAccountButton();
        createAccount.accIsNotCreated(password,password);
        createAccount.clickShowPasswordButton();
        boolean chkPasswordAppears=createAccount.chkshowPassword();
        Assert.assertTrue(chkPasswordAppears);
    }
}
