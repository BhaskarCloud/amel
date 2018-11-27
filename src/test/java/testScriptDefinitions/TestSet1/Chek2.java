package testScriptDefinitions.TestSet1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//import ObjectRepository.PgLogin;
//import ObjectRepository.pgHome;

public class Chek2 extends testScriptDefinitions.UserLibrary.UserLibrary{

	public static void main(String[] args) {
		//System.setProperty("webdriver.gecko.driver","BrowserServers\\geckodriver.exe");	
		System.setProperty("webdriver.chrome.driver","BrowserServers\\chromedriver.exe");	
		//WebDriver driver= new FirefoxDriver();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.freecrm.com");
		wait(4);
		driver.findElement(By.name("username")).sendKeys("BhaskarK");  // line 44;//1
	 
		driver.findElement(By.name("password")).sendKeys("123456");  // line 44;//2
		WebElement LoginBtn = driver.findElement(By.xpath("//input[@type='submit']"));
	//	new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(LoginBtn));
		LoginBtn.click();  // line 44
		
		//wait(5);
		System.out.println("------------------");
//driver.findElement(By.linkText("Contacts")).click();
	/*System.out.println(pgHome.LnkCONTACTS.toString());
	System.out.println(PgLogin.btnLogIn.toString());
	System.out.println(pgHome.LnkCONTACTS.xpath("//a[@title='Contacts']").toString());
*/	}

}
