package test;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import junit.framework.Assert;
import pojo.Browser;
import pom.NaptolHomePage;
import pom.NaptolProductDetailsPage;
import pom.NaptolQuickViewPage;


public class NaptolHomePageTest extends BaseTest  {

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
	
	
	//29-11-2024 changed
	@Test
	public void VerifyIfProdutDetailsAreCorrcetIfViewInQuickView() throws EncryptedDocumentException, IOException 
	{
		NaptolHomePage naptolHomePage = new NaptolHomePage(driver);
		naptolHomePage.enterValidProductNameForSearch();
		naptolHomePage.clickOnSearchButton();
		
		System.out.println(naptolHomePage.getProductName(1));
		String ProductName =  naptolHomePage.getProductName(1);
		
		naptolHomePage.moveToProduct(driver,1);		
		boolean result = naptolHomePage.clickOnQuickView(1);	
		System.out.println(result);
		Assert.assertTrue(result);
		NaptolQuickViewPage naptolQuickViewPage = new NaptolQuickViewPage(driver);
		String QuickViewProName = naptolQuickViewPage.getProductName();	
		System.out.println(QuickViewProName);
		Assert.assertEquals(ProductName, QuickViewProName);		
		Double ProductPrice = naptolHomePage.getProductPrice(1);
		System.out.println(ProductPrice);
		Double QuickProductPrice = naptolQuickViewPage.getProductPrice(1);
		System.out.println(QuickProductPrice);
		Assert.assertEquals(ProductPrice, QuickProductPrice);				
	}
	
	@Test
	public void VerifyProductDetailsAreCorrectIfViewInNewTab() throws EncryptedDocumentException, IOException
	{
		NaptolHomePage naptolHomePage = new NaptolHomePage(driver);
		naptolHomePage.enterValidProductNameForSearch();
		naptolHomePage.clickOnSearchButton();
		String ProductName =  naptolHomePage.getProductName(1);
		
		String title = naptolHomePage.clickOnProduct(driver);
		Double ProductPrice = naptolHomePage.getProductPrice(1);
		
		NaptolProductDetailsPage naptolProductDetailsPage = new NaptolProductDetailsPage(driver);
		String ChildBrowserProductName = naptolProductDetailsPage.getProductName();
		double ChildBrowserProductPrice = naptolProductDetailsPage.getProductPrice(1);		
		Assert.assertEquals(ProductName, ChildBrowserProductName);
		Assert.assertEquals(ProductPrice, ChildBrowserProductPrice);
	}
}
