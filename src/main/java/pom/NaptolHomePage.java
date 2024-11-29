package pom;

import java.io.IOException;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.ActionsPro;
import utility.Parameterization;


public class NaptolHomePage  {

	
	@FindBy(xpath = "//input[@id='header_search_text']") private WebElement SearchBox;
	@FindBy(xpath = "(//a[@href='javascript:autoSuggestion.headerSearch()'])[2]")private WebElement SearchButton;
	@FindBy(xpath = "//span[@id='resultCountSpan']") private WebElement SearchResult;
	@FindBy(xpath = "//div[@class='cate_head']")private WebElement ShoppingCategories; 
	@FindBy(xpath = "//div[@id='mainMenuContent']") private WebElement ShoppingCategoriesList;
	@FindBy(xpath = "//div[@class='item_title']") private List <WebElement> ProductNameList;
	@FindBy(xpath = "//a[@class='bt_compare icon chat quickFancyBox']") private List <WebElement> QuckViewBtm;
	
	
	public NaptolHomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);	
	}

	public void enterValidProductNameForSearch() throws EncryptedDocumentException, IOException
	{
		Parameterization pm = new Parameterization();
		 
		SearchBox.sendKeys(Parameterization.getStringData("Sheet1", 0, 0));		
	}
	
	public void enterInvalidProductNameForSearch() throws EncryptedDocumentException, IOException
	{
			 
		SearchBox.sendKeys(Parameterization.getStringData("Sheet1", 1, 0));		
	}
		
	
	public void clickOnSearchButton()
	{
		SearchButton.click();
	}
	
	public String getSearchResultText()
	{
		return SearchResult.getText();
	}
	
	
	public void moveToShoppingCategories(WebDriver driver)
	{			
		ActionsPro.ActionClass(driver,ShoppingCategories);	
	}
	
	public boolean verifyingShoppingcategoriesMenu()
	{
		boolean isShoppingCategoriesListDisplayedOrNot = ShoppingCategoriesList.isDisplayed();
		return isShoppingCategoriesListDisplayedOrNot;
	}
	
	
	public void clickOnQuickView(int index)
	{
		QuckViewBtm.get(index).click();
	}
		
	public String getProductName(int index)
	{
		String productName = ProductNameList.get(index).getText();
		return productName;
	}
	

}
