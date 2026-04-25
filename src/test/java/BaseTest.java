import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;

public class BaseTest {
    private WebDriver driver;
    protected HomePage homePage;
    public String email="ms3196@fayoum.edu.eg";
    public String password="P@ssword123";

    @BeforeMethod
    public void setUpEnv()
    {
        driver=new ChromeDriver();
        driver.get("https://demo-m2.bird.eu/home");
        driver.manage().window().maximize();
        homePage = new HomePage(driver);   //Declare new object from Home class
    }
    @AfterMethod
    public void tearDown()
    {
     driver.quit();
    }
}
