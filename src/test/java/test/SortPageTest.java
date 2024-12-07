package test;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import junit.framework.Assert;
import pojo.Browser;
import pom.NaptolHomePage;
import pom.SortPage;

@Listeners(test.Listeners.class)
public class SortPageTest extends BaseTest{

	
	@BeforeMethod
	public void launchApplication()
	{
		driver = Browser.openBrowser();				
	}
		
	@Test
	public void VerifySortFeature() throws EncryptedDocumentException, IOException, InterruptedException
	{
		test = reports.createTest("VerifySortFeature");
		NaptolHomePage naptolHomePage = new NaptolHomePage(driver);
		naptolHomePage.enterValidProductNameForSearch();
		naptolHomePage.clickOnSearchButton();
		
		String[] beforeSortProductNameList = naptolHomePage.getProductsNameList();
		
		SortPage sortPage = new SortPage(driver);
		sortPage.clickOnSort();
		sortPage.getSelectedOption(3); //Most Expensive
		Thread.sleep(5000);
		double priceList[] = sortPage.getProducPriceList();
		
		for(int i=0;i<priceList.length;i++)
		{
			if(i<priceList.length-1)
			{
				Assert.assertTrue(priceList[i]>=priceList[i+1]);
			}
		}	
			Thread.sleep(5000);
			sortPage.getSelectedOption(4);	//Cheapest
			
			for(int i=priceList.length-1;i>=0;i--)
			{
				if(i>0)
					Assert.assertTrue(priceList[i]<=priceList[i-1]);
			}
			
			Thread.sleep(5000);
			sortPage.getSelectedOption(1); //New Arrivals
			boolean result =sortPage.isNewFlagDisplayed();
			Assert.assertTrue(result);
			
			sortPage.getSelectedOption(5); //Relevance
			Thread.sleep(3000);
			String newArrivalSortProductNameList[]=sortPage.getProductsNameList();
			for(int i =0; i<newArrivalSortProductNameList.length;i++) {
				if(beforeSortProductNameList[i]!=newArrivalSortProductNameList[i]) {
					Assert.assertTrue(beforeSortProductNameList[i]!=newArrivalSortProductNameList[i]);
				}
			}	
			
			sortPage.getSelectedOption(0); //Most Popular
			Thread.sleep(3000);
			String MostPopularSortProductNameList[]=sortPage.getProductsNameList();
			for(int i =0; i<MostPopularSortProductNameList.length;i++) {
				if(beforeSortProductNameList[i]!=MostPopularSortProductNameList[i]) {
					Assert.assertTrue(beforeSortProductNameList[i]!=MostPopularSortProductNameList[i]);
				}
			}				
		}	
	
	
	@AfterMethod
	public void closeBrowser()
	{
		driver.close();
	}
	
	}
	

