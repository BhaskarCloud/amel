package testScriptDefinitions.TestSet1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

//import ObjectRepository.PgLogin;
//import ObjectRepository.pgHome;

public class Chek1 extends testScriptDefinitions.UserLibrary.UserLibrary{

	public static void main(String[] args) {
		System.setProperty("webdriver.gecko.driver","BrowserServers\\geckodriver.exe");	
		
		WebDriver driver= new FirefoxDriver();
		
		driver.get("https://www.freecrm.com");
		wait(5);
		driver.findElement(By.name("username")).sendKeys("BhaskarK");  // line 44;//1
	 
		driver.findElement(By.name("password")).sendKeys("123456");  // line 44;//2
		driver.findElement(By.xpath("//input[@type='submit']")).click();  // line 44
		
		wait(5);
		
//driver.findElement(By.linkText("Contacts")).click();
	/*System.out.println(pgHome.LnkCONTACTS.toString());
	System.out.println(PgLogin.btnLogIn.toString());
	System.out.println(pgHome.LnkCONTACTS.xpath("//a[@title='Contacts']").toString());
*/	}

}
