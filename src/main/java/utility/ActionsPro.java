package utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


public class ActionsPro {
	
	public static  void ActionClass(WebDriver driver, WebElement ele)
	{
	
		Actions act = new Actions(driver);
		act.moveToElement(ele);
		act.perform();
	}
}
