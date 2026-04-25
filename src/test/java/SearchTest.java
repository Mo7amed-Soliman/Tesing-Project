import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ResultPage;

import java.util.List;

public class SearchTest extends BaseTest {

    //verify when search for an item , item itself appears
    @Test
    public void chkSearchResults() throws InterruptedException {
        homePage.searchForItem("Jacket");
        ResultPage resultPage=homePage.clickOnSearchButton();
        List<WebElement> results=resultPage.returnResults();
        for (WebElement x:results)
        {
            Assert.assertTrue(x.getText().contains("Jacket"));
        }
    }
    //verify when search for an invalid item , an error appears inform usr that there is no item with this name
    @Test
    public void chkSearchResultsForInvalidProduct()
    {
        homePage.searchForItem("asdknasdijb");
        ResultPage resultPage=homePage.clickOnSearchButton();
        String noResults=resultPage.getErrorNoResultsText();
        Assert.assertEquals(noResults,"Your search returned no results.","error search function is not working effciently");
    }
    //verify when search with mistakes in word , website suggest the most matched category for this word
    @Test
    public void chkSuggestionForInvalidProduct()
    {
        homePage.searchForItem("Jashet");
        ResultPage resultPage=homePage.clickOnSearchButton();
        String matchedSuggestion=resultPage.getDidYouMeanMessage();
        Assert.assertTrue(matchedSuggestion.contains("Did you mean"));
    }
}
