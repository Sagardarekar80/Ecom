package test;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import junit.framework.Assert;
import pojo.Browser;
import pom.NaptolCartPage;
import pom.NaptolHomePage;
import pom.NaptolQuickViewPage;

public class NaptolCartPageTest extends BaseTest{

	
	@BeforeMethod
	public void launchApplication()
	{
		driver = Browser.openBrowser();				
	}
	
	@Test	
	public void verifyAddingMultipleProductToCart() throws EncryptedDocumentException, IOException
	{
		NaptolHomePage naptolHomePage = new NaptolHomePage(driver);
		naptolHomePage.enterValidProductNameForSearch();
		naptolHomePage.clickOnSearchButton();
		naptolHomePage.moveToProduct(driver,1);		
		boolean result = naptolHomePage.clickOnQuickView(1);		
						
		NaptolQuickViewPage naptolQuickViewPage = new NaptolQuickViewPage(driver);
		
		if(naptolQuickViewPage.getProductColorList()>0)
		{
			naptolQuickViewPage.SelectProductColor(1);
						
			naptolQuickViewPage.clickOnClickHereToBuyButton();			
		}
		else
		{
			naptolQuickViewPage.clickOnClickHereToBuyButton();
		}		
		NaptolCartPage naptolCartPage = new NaptolCartPage(driver);
		
		
		int d = naptolCartPage.getCartItemCount();
		System.out.println(d);
		int itemCount = naptolCartPage.getCartProductList();
		
		Assert.assertEquals(itemCount, d);
	}
	
	
	
	@Test
	public void VerifyRemovingProductFromCart() throws EncryptedDocumentException, IOException
	{
		NaptolHomePage naptolHomePage = new NaptolHomePage(driver);
		naptolHomePage.enterValidProductNameForSearch();
		naptolHomePage.clickOnSearchButton();
		naptolHomePage.moveToProduct(driver,1);		
		boolean result = naptolHomePage.clickOnQuickView(1);		
						
		NaptolQuickViewPage naptolQuickViewPage = new NaptolQuickViewPage(driver);
		
		if(naptolQuickViewPage.getProductColorList()>0)
		{
			naptolQuickViewPage.SelectProductColor(1);
						
			naptolQuickViewPage.clickOnClickHereToBuyButton();			
		}
		else
		{
			naptolQuickViewPage.clickOnClickHereToBuyButton();
		}		
		NaptolCartPage naptolCartPage = new NaptolCartPage(driver);
		
	//	naptolCartPage.clickOnRemoveBtm(0);
		
		
		  String RemovedProductname = naptolCartPage.clickOnRemoveBtm(0);
		 
		  
		  String [] AllCartProductNames = naptolCartPage.getProductNames();
		  
		  for(int i=0;i<AllCartProductNames.length;i++) 
		  {
			  if(AllCartProductNames[i]!=RemovedProductname) 
			  {
				  System.out.println(AllCartProductNames[i]+ "For loop");
		  
			  } 
		  
		  System.out.println(RemovedProductname + "Remove product name");
		  Assert.assertEquals(AllCartProductNames[i], RemovedProductname); 
		  
		  System.out.println("Code Completed");
		  
		 }
		 
		
		
		
		
	}
	
}
