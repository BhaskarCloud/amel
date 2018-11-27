package testScriptDefinitions.UserLibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import FrameworkLibrary.WebLibrary;
import ObjectRepository.PgLogin;
/**
 * @AUTHOR Bhaskar
 */
public class UserLibrary extends WebLibrary
{
	public static boolean login(String UserName,String Password,WebDriver driver)
	{
		boolean stepstatus = false; 
	
		wait(10);
//		System.out.println("---------25-------UL");
		highlight(PgLogin.Username,   driver);  
		setText(PgLogin.Username,  UserName,  driver);  // line 44
		
		highlight(PgLogin.pasward,   driver);  // line 44
		setText(PgLogin.pasward,  Password,  driver);
		
		highlight(PgLogin.btnLogIn, driver);
		clickElement(PgLogin.btnLogIn, driver);
		
		//JavascriptExecutor js = (JavascriptExecutor)driver;
		  // 	js.executeScript("arguments[0].click();", PgLogin.btnLogIn);
		
	/*	RobotKeysSetText(UserName);
		RobotKeysTab();
		RobotKeysSetText(Password);
		RobotKeysEnter();*/
		wait(3);
		try
		{
			String title = driver.getTitle();
			if(title.contains("Allstate Virtual Assistant"))
			{
				stepstatus = true;
			}
		}
		catch(Exception e)
		{
			stepstatus = false;
		}
		return stepstatus;
	}
	
	
}
