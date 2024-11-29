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
		String searchResult = naptolHomePage.getSearchResultText();
		double searchCount = Double.parseDouble(searchResult)	;
		Assert.assertTrue(searchCount>0);				
	}
	
	
	@Test
	public void searchAInvalidProduct() throws EncryptedDocumentException, IOException
	{
		NaptolHomePage naptolHomePage = new NaptolHomePage(driver);
		naptolHomePage.enterInvalidProductNameForSearch();
		naptolHomePage.clickOnSearchButton();
		String searchResult = naptolHomePage.getSearchResultText();
		double searchCount = Double.parseDouble(searchResult);									
		Assert.assertTrue(searchCount==0);								
	}
	
	@Test
	public void verifyOnClickingShoppingCategories()
	{
		NaptolHomePage naptolHomePage = new NaptolHomePage(driver);
		naptolHomePage.moveToShoppingCategories(driver);
		 Assert.assertTrue(naptolHomePage.verifyingShoppingcategoriesMenu());
	}
	
	@Test
	public void VerifyIfProdutDetailsAreCorrcetIfViewInQuickView() throws EncryptedDocumentException, IOException
	{
		NaptolHomePage naptolHomePage = new NaptolHomePage(driver);
		naptolHomePage.enterValidProductNameForSearch();
		naptolHomePage.clickOnSearchButton();		
		naptolHomePage.clickOnQuickView(0);
	}
	
	
}
