package pom;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.Parameterization;

public class NaptolHomePage {

	@FindBy(xpath = "//input[@id='header_search_text']") private WebElement SearchBox;
	@FindBy(xpath = "(//a[@href='javascript:autoSuggestion.headerSearch()'])[2]")private WebElement SearchButton;
	@FindBy(xpath = "//span[@id='resultCountSpan']") private WebElement SearchResult;
	
	
	
	
	public NaptolHomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public void enterValidProductNameForSearch() throws EncryptedDocumentException, IOException
	{
		Parameterization pm = new Parameterization();
		 
		SearchBox.sendKeys(pm.getStringData("Sheet1", 0, 0));		
	}
	
	public void enterInvalidProductNameForSearch() throws EncryptedDocumentException, IOException
	{
		Parameterization pm = new Parameterization();
		 
		SearchBox.sendKeys(pm.getStringData("Sheet1", 1, 0));		
	}
	
	
	
	public void clickOnSearchButton()
	{
		SearchButton.click();
	}
	
	public String getSearchResultText()
	{
		return SearchResult.getText();
	}

}
