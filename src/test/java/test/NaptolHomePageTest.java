package test;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import junit.framework.Assert;
import pojo.Browser;
import pom.NaptolHomePage;

public class NaptolHomePageTest extends BaseTest {

	@BeforeMethod
	public void launchApplication()
	{
		driver = Browser.openBrowser();				
	}
	
	@Test
	public void searchAValidProduct() throws EncryptedDocumentException, IOException
	{
		NaptolHomePage naptolHomePage = new NaptolHomePage(driver);
		naptolHomePage.enterValidProductNameForSearch();
		naptolHomePage.clickOnSearchButton();
		String SearchResult = naptolHomePage.getSearchResultText();
		
		
	}
	
	
	@Test
	public void searchAInvalidProduct() throws EncryptedDocumentException, IOException
	{
		NaptolHomePage naptolHomePage = new NaptolHomePage(driver);
		naptolHomePage.enterInvalidProductNameForSearch();
		naptolHomePage.clickOnSearchButton();
		String SearchResult = naptolHomePage.getSearchResultText();
		
		Assert.assertEquals("Search Results For : IPhone",SearchResult);
	}
	
	
	
	
}
