package pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NaptolCartPage extends BaseClass{

	
	@FindBy(xpath = "//ul[@id='cartData']//h2")private WebElement CartProductName;
	@FindBy(xpath = "//div[@id='cartItems']//ul")private List<WebElement> AddedProductToCart;
	@FindBy(xpath = "//span[@class='font-bold-imp']")private WebElement CartItemCount;
	@FindBy(xpath = "//div[@id='cartItems']")private List<WebElement> CartProductNameList;
	@FindBy(xpath = "//p[@class='chintu']//a")private List<WebElement> removeBtm;
	@FindBy(xpath = "//a[@onclick='cart.remove(596086614)']")private WebElement removeBtm1;
	@FindBy(xpath = "(//button[@title='Close'])[2]")private WebElement CloseButton;
	
	
	public NaptolCartPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public String getCartProductname()
	{
		return CartProductName.getText();
	}
	
	public int getCartProductList()
	{
		
		return AddedProductToCart.size();
	}
	
	public int getCartItemCount()
	{
		String [] count =  CartItemCount.getText().split(" ");
		System.out.println(count[0]);
		return Integer.parseInt(removeBracesFromString(count[0]));
				
	}
		
	public String [] getProductNames()
	{
		System.out.println("CartProductNameList");
		String [] ProductNames = new String [CartProductNameList.size()];
		for(int i=0; i<CartProductNameList.size();i++)
		{
			ProductNames[i]=CartProductNameList.get(i).getText();
		}
		return ProductNames;
	}
	
	public String clickOnRemoveBtm(int index)	
	{			
		System.out.println(CartProductNameList.size());
		  String removedProductName = CartProductNameList.get(index).getText();
		  removeBtm.get(index).click();
		  CartProductNameList.remove(index);
		  System.out.println(CartProductNameList.size());
		  
		  System.out.println(removeBtm.get(index).getText());		  
		  return removedProductName;
	}
	
	public void clickOnCloseButton()
	{
		CloseButton.click();
	}
}
