package FrameworkLibrary;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent; // keyEvent is here,, can not see
import java.io.File;
import java.nio.channels.GatheringByteChannel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys; // keys is here,, can not see
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import com.relevantcodes.extentreports.LogStatus;

public class WebLibrary extends ExcelLibrary // line 33
{

	/**
	 * Used to enter text into a edit field
	 * 
	 * @param by       -- Element Reference
	 * @param strValue -- TestData
	 * @param driver   -- driver Reference
	 * @return -- True/False
	 * @author Bhaskar
	 */
	protected static boolean setText(By by, String strValue, WebDriver driver) // line 44
	{
		boolean stepstatus;
		try {
			driver.findElement(by).click();
			driver.findElement(by).clear();
			driver.findElement(by).sendKeys(strValue);
			stepstatus = true;
		} catch (Exception e) {
			stepstatus = false;
		}
		return stepstatus;
	}

	/**
	 * Used to click on a webelement
	 * 
	 * @param by     -- WebElement Reference
	 * @param driver -- driver Reference
	 * @return -- True/False
	 * @author Bhaskar
	 */
	protected static boolean clickElement(By by, WebDriver driver) // line 68
	{
		boolean stepstatus;
		try {
			driver.findElement(by).click();
			stepstatus = true;
		} catch (Exception e) {
			stepstatus = false;
		}
		return stepstatus;
	}

	/**
	 * Used to select option from dropdown
	 * 
	 * @param by       -- Select Box Reference
	 * @param strValue -- Option Value
	 * @param driver   -- driver Reference
	 * @return -- True/False
	 * @author Bhaskar
	 */
	protected static boolean selectOptionByText(By by, String strValue, WebDriver driver) // line 91
	{
		boolean stepstatus;
		try {
			wait(1);
			Select s1 = new Select(driver.findElement(by));
			s1.selectByVisibleText(strValue);
			stepstatus = true;
		} catch (Exception e) {
			stepstatus = false;
		}
		return stepstatus;
	}

	/**
	 * Description : Used Launch Browser Method Name : launchBrowser Input
	 * parameters : 1) Browser Name to launch Created By : bhaskar Created On :
	 * 8-May-2017 LastUpdated On : 9-Sep-2018 Last Updated By : Email id : Comments
	 * : @return -- WebDriver
	 */

	protected static WebDriver launchBrowser(String BrowserName) // line 120
	{
		WebDriver Tempdriver = null;
		switch (BrowserName.toLowerCase()) {
		case "firefox": {
			System.setProperty("webdriver.gecko.driver", "BrowserServers\\geckodriver.exe");

			Tempdriver = new FirefoxDriver();
			break;
		}
		case "ie":
		case "internetexplorer": {
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
			System.setProperty("webdriver.ie.driver", "BrowserServers\\IEDriverServer.exe");
			Tempdriver = new InternetExplorerDriver(capabilities);
			break;
		}
		case "chrome": {
			System.setProperty("webdriver.chrome.driver", "BrowserServers\\chromedriver.exe");
			Tempdriver = new ChromeDriver();
			break;
		}
		case "htmlunit": {

			Tempdriver = new HtmlUnitDriver();
			break;
		}
		default: {
			System.out.println("please Select the correct browser");
		}
		}
		return Tempdriver;
	}

	/**
	 * Description : to open the URL in the driver instance Method Name : OpenUrl
	 * Input parameters : 1) URL 2) driver instance Created By : bhaskar Last
	 * Updated By : bhaskar
	 */
	protected static boolean OpenUrl(WebDriver driver)// (String ENV, WebDriver driver) //line 168
	{
		boolean stepStatus = true;
		try {
			String URL = "https://allstate-amelia-uat-v3.ipsoft.com/Amelia/";
			driver.get(URL);
			driver.manage().window().maximize();
		} catch (Exception e) {
			stepStatus = true;
		}
		return stepStatus;
	}

	/**
	 * Description : to close all window instances Method Name : quitDriver Input
	 * parameters : 1) driver instance Created By : bhaskar Created On : 8-May-2017
	 * LastUpdated On : 9-May-2017 Last Updated By : Email id : Comments :
	 */

	public static boolean quitDriver(WebDriver driver) // line 201
	{
		boolean stepStatus;
		try {
			driver.quit();
			stepStatus = true;
		} catch (Exception e) {
			stepStatus = false;
		}
		return stepStatus;
	} // line 214

	/**
	 * Description : to varify the existance of a webelement Method Name : exists
	 * Input parameters : 1) driver instance Created By : bhaskar Created On :
	 * 8-May-2017 LastUpdated On : 9-May-2017 Last Updated By : Email id : Comments
	 * :
	 */
	protected static boolean exists(By by, WebDriver driver) // line 227
	{
		List<WebElement> allitems = driver.findElements(by);
		boolean found = false;
		if (allitems.size() > 0) {
			found = true;
		} else {
			found = false;
		}
		return found;
	}

	/**
	 * Description : to highlight a webelement Method Name : highlight Input
	 * parameters : 1) driver instance Created By : bhaskar Created On : 8-May-2017
	 * LastUpdated On : 9-May-2017 Last Updated By : Email id : Comments :
	 */
	protected static void highlight(By by, WebDriver driver) // line 252
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			WebElement ele = driver.findElement(by);
			for (int i = 1; i <= 2; i++) {
				Thread.sleep(200);
				js.executeScript("arguments[0].style.border='solid 4px black'", ele);
				Thread.sleep(200);
				js.executeScript("arguments[0].style.border=''", ele);
			}
		} catch (Exception e) {

		}
	} // line 270

	/**
	 * Description : set Text in elemwnt And press Escape Method Name :
	 * setTextAndEscape Input parameters : 1) driver instance Created By : bhaskar
	 * Created On : 8-May-2017 LastUpdated On : 9-May-2017 Last Updated By : bhaskar
	 * Email id : Comments :
	 */

	protected static boolean setTextAndEscape(By by, String Value, WebDriver driver) // line 283
	{
		boolean status = false;
		try {
			highlight(by, driver);
			driver.findElement(by).click();
			driver.findElement(by).clear();
			driver.findElement(by).sendKeys(Value);
			String webValue = driver.findElement(by).getAttribute("value");
			driver.findElement(by).sendKeys(Keys.ESCAPE);
			Thread.sleep(500);
			if (webValue.equalsIgnoreCase(Value)) {
				status = true;
			}
		} catch (Exception e) {
			status = false;
		}
		return status;
	}

	/**
	 * Description : To perform mouse hover to a element Method Name : mouseHover
	 * Input parameters : 1) WebElement xpath 2) driver instance Created By :
	 * bhaskar Created On : 8-May-2017 LastUpdated On : 9-May-2017 Last Updated By :
	 * bhaskar Email id : Comments :
	 */
	protected boolean mouseHover(By by, WebDriver driver) // line 318
	{
		boolean stepStatus;
		try {
			Actions a1 = new Actions(driver);
			a1.moveToElement(driver.findElement(by));
			a1.build().perform();
			stepStatus = true;
		} catch (Exception e) {
			stepStatus = false;
		}
		return stepStatus;

	}

	/**
	 * Description : To perform right click on a element Method Name : rightClick
	 * Input parameters : 1) WebElement xpath 2) driver instance Created By :
	 * bhaskar Created On : 8-May-2017 LastUpdated On : 9-May-2017 Last Updated By :
	 * bhaskar Email id : Comments :
	 */

	protected boolean rightClick(By by, WebDriver driver) // line 348
	{
		boolean stepStatus;
		try {
			Actions a1 = new Actions(driver);
			a1.moveToElement(driver.findElement(by));
			a1.build().perform();
			stepStatus = true;
		} catch (Exception e) {
			stepStatus = false;
		}
		return stepStatus;

	}

	/**
	 * Description : To perform dragAndDrop action on an element Method Name :
	 * dragAndDrop Input parameters : 1) WebElement xpath 2) driver instance Created
	 * By : bhaskar Created On : 8-May-2017 LastUpdated On : 9-Sep-2018 Last Updated
	 * By : bhaskar Email id : Comments :
	 */

	protected static boolean dragAndDrop(String StrDragXpath, String StrDropXpath, WebDriver driver) // line 378
	{
		boolean stepStatus;
		try {
			WebElement drag = driver.findElement(By.xpath(StrDragXpath));
			WebElement drop = driver.findElement(By.xpath(StrDropXpath));
			Actions action = new Actions(driver);
			action.moveToElement(drag).dragAndDrop(drag, drop).build().perform();
			stepStatus = true;
		} catch (Exception e) {
			stepStatus = false;
		}
		return stepStatus;
	} // line 394

	/**
	 * Description : To switch to a frame based on element Method Name :
	 * switchToframe Input parameters : 1) frame Xpath 2) driver instance Created By
	 * : bhaskar Created On : 8-May-2017 LastUpdated On : 9-May-2017 Last Updated By
	 * : bhaskar Email id : balreddy. ankireddy@wellsfargo.com Comments :
	 */

	protected static WebDriver switchToframe(By by, WebDriver driver) // line 409
	{
		try {
			WebElement frameref = driver.findElement(by);
			driver.switchTo().frame(frameref);
		} catch (Exception e) {
		}
		return driver;
	}

	/**
	 * Description : To switch to a frame based on title Method Name : switchToTitle
	 * Input parameters : 1) frame Xpath 2) driver instance Created By : bhaskar
	 * Created On : 8-May-2017 LastUpdated On : 9-May-2017 Last Updated By : bhaskar
	 * Email id : Comments :
	 */

	protected static WebDriver switchToTitle(By by, WebDriver driver) // line 488
	{
		try {
			WebElement frameref = driver.findElement(by);
			driver.switchTo().frame(frameref);
		} catch (Exception e) {
		}
		return driver;
	}

	/**
	 * Description : To switch to window based on URL Method Name :
	 * switchToWindowByURL Input parameters : 1) frame Xpath 2) driver instance
	 * Created By : bhaskar Created On : 8-May-2017 LastUpdated On : 9-May-2017 Last
	 * Updated By : bhaskar Email id : Comments :
	 */

	protected static WebDriver switchToWindowByURL(String strURL, WebDriver driver) // line 465
	{

		try {
			Set<String> allhandles = driver.getWindowHandles();
			for (String h1 : allhandles) {
				driver.switchTo().frame(h1);
				String currentURl = driver.getCurrentUrl();
				if (currentURl.contains(strURL)) {
					break;
				}
			}
		} catch (Exception e) {

		}
		return driver;
	} // line 486

	/**
	 * Description : To close a window based on title Method Name :
	 * closeWindowByTitle Input parameters : 1) window Title 2) driver instance
	 * Created By : bhaskar Created On : 8-May-2017 LastUpdated On : 9-May-2017 Last
	 * Updated By : bhaskar Email id : Comments :
	 */
	protected static boolean closeWindowByTitle(String StrTitle, WebDriver driver) // line 499
	{
		boolean stepStatus = false;
		try {
			Set<String> allhandles = driver.getWindowHandles();
			for (String h1 : allhandles) {
				driver.switchTo().frame(h1);
				String currentURl = driver.getCurrentUrl();
				if (currentURl.contains(StrTitle)) {
					driver.close();
					stepStatus = true;
					break;
				}
			}
		} catch (Exception e) {
			stepStatus = false;
		}
		return stepStatus;
	} // line 522

	/**
	 * Description : To close a window based on URL Method Name : closeWindowByURL
	 * Input parameters : 1) window URL 2) driver instance Created By : bhaskar
	 * Created On : 8-May-2017 LastUpdated On : 9-May-2017 Last Updated By : bhaskar
	 * Email id : Comments :
	 */

	protected static boolean closeWindowByURL(String StrURL, WebDriver driver) // line 535
	{
		boolean stepStatus = false;
		try {
			Set<String> allhandles = driver.getWindowHandles();
			for (String h1 : allhandles) {
				driver.switchTo().frame(h1);
				String currentURl = driver.getCurrentUrl();
				if (currentURl.contains(StrURL)) {
					driver.close();
					stepStatus = true;
					break;
				}
			}
		} catch (Exception e) {
			stepStatus = false;
		}
		return stepStatus;
	} // line 558

	/**
	 * Description : to wait for specified time duration(Static wait) Method Name :
	 * wait Input parameters : 1) Time in Seconds Created By : bhaskar Created On :
	 * 8-May-2017 LastUpdated On : 9-May-2017 Last Updated By : bhaskar Email id :
	 * Comments :
	 */
	protected static WebDriver SetImplicitWait(int timeInSec, WebDriver driver) // LINE 572
	{
		try {
			driver.manage().timeouts().implicitlyWait(timeInSec, TimeUnit.SECONDS);
		} catch (Exception e) {

		}
		return driver;
	}

	/**
	 * Description : to wait for specified time duration(Static wait) Method Name :
	 * wait Input parameters : 1) Time in Seconds Created By : bhaskar Created On :
	 * 8-May-2017 LastUpdated On : 9-May-2017 Last Updated By : bhaskar Email id :
	 * Comments :
	 */
	protected static boolean wait(int timeInSec) // LINE 597
	{
		boolean stepStatus;
		try {
			Thread.sleep(timeInSec * 1000);
			stepStatus = true;
		} catch (Exception e) {
			stepStatus = false;
		}
		return stepStatus;
	}

	/**
	 * Description : To close a window based on URL xx Method Name :
	 * closeWindowByURL Input parameters : 1) window URL 2) driver instance Created
	 * By : bhaskar Created On : 8-May-2017 LastUpdated On : 9-May-2017 Last Updated
	 * By : bhaskar Email id : Comments :
	 */

	protected static boolean closeWindowByURLxx(String StrURL, WebDriver driver) // line 535
	{
		boolean stepStatus = false;
		try {
			Set<String> allhandles = driver.getWindowHandles();
			for (String h1 : allhandles) {
				driver.switchTo().frame(h1);
				String currentURl = driver.getCurrentUrl();
				if (currentURl.contains(StrURL)) {
					driver.close();
					stepStatus = true;
					break;
				}
			}
		} catch (Exception e) {
			stepStatus = false;
		}
		return stepStatus;
	} // line 558

	public boolean Exists(By by, WebDriver driver) {
		return false;

	}

	/**
	 * Description : to upload file based on .exe file Method Name :
	 * AutoITFileUpload Input parameters : 1).exe file path 2)Destination path &
	 * file name Created By : bhaskar Created On : 8-May-2017 LastUpdated On :
	 * 9-May-2017 Last Updated By : bhaskar Email id : Comments :
	 */
	protected static boolean AutoITFileUpload(String exeFileName, String Filepath) // line 669
	{
		Boolean stepStatus = true;
		try {
			Runtime.getRuntime().exec("AuotlTFiles\\" + exeFileName + ".exe" + "" + Filepath);
		} catch (Exception e) {
			stepStatus = false;
		}
		return stepStatus;
	} // line 681

	/**
	 * Description : to upload file based on string path Method Name :
	 * RobotKeysSetText Input parameters : NA Created By : bhaskar Created On :
	 * 8-May-2017 LastUpdated On : 9-May-2017 Last Updated By : bhaskar Email id :
	 * Comments :
	 */

	protected static boolean RobotKeysFileUpload(String Filepath) // LINE 693
	{
		boolean stepStatus;
		try {
			wait(1);
			StringSelection selection = new StringSelection(Filepath);
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(selection, selection);

			Robot robo = new Robot();
			robo.keyPress(java.awt.event.KeyEvent.VK_CONTROL);
			robo.keyPress(java.awt.event.KeyEvent.VK_V);
			robo.keyRelease(java.awt.event.KeyEvent.VK_V);
			robo.keyRelease(java.awt.event.KeyEvent.VK_CONTROL);

			wait(2);
			robo.keyPress(java.awt.event.KeyEvent.VK_ENTER);
			robo.keyRelease(java.awt.event.KeyEvent.VK_ENTER);
			wait(1);
			robo.keyPress(java.awt.event.KeyEvent.VK_ENTER);
			robo.keyRelease(java.awt.event.KeyEvent.VK_ENTER);

			stepStatus = true;
		} catch (Exception e) {
			stepStatus = false;
		}
		return stepStatus;

	}

	/**
	 * Description : to upload file based on string path Method Name :
	 * RobotKeysSetText Input parameters : NA Created By : bhaskar Created On :
	 * 8-May-2017 LastUpdated On : 9-May-2017 Last Updated By : bhaskar Email id :
	 * Comments :
	 */

	protected static boolean RobotKeysSetText(String Filepath) // LINE 736
	{

		boolean stepStatus;
		try {
			wait(1);
			StringSelection selection = new StringSelection(Filepath);
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(selection, selection);

			Robot robo = new Robot();
			robo.keyPress(java.awt.event.KeyEvent.VK_CONTROL);
			robo.keyPress(java.awt.event.KeyEvent.VK_V);
			wait(1);
			robo.keyRelease(java.awt.event.KeyEvent.VK_V);
			robo.keyRelease(java.awt.event.KeyEvent.VK_CONTROL);

			wait(1);
			stepStatus = true;
		} catch (Exception e) {
			stepStatus = false;
		}
		return stepStatus;
	}

	/**
	 * Description : to upload file based on string path Method Name : RobotKeysTab
	 * Input parameters : NA Created By : bhaskar Created On : 8-May-2017
	 * LastUpdated On : 9-May-2017 Last Updated By : bhaskar Email id : Comments :
	 */
	protected static boolean RobotKeysTab() // LINE 775
	{
		boolean stepStatus;
		try {
			wait(1);
			Robot robo = new Robot();
			robo.keyPress(java.awt.event.KeyEvent.VK_TAB);
			wait(1);
			robo.keyRelease(java.awt.event.KeyEvent.VK_TAB);
			wait(1);
			stepStatus = true;
		} catch (Exception e) {
			stepStatus = false;
		}
		return stepStatus;
	}

	/**
	 * Description : to upload file based on string path Method Name :
	 * RobotKeysEnter Input parameters : NA Created By : bhaskar Created On :
	 * 8-May-2017 LastUpdated On : 9-May-2017 Last Updated By : bhaskar Email id :
	 * Comments :
	 */
	protected static boolean RobotKeysEnter() // LINE 805
	{
		boolean stepStatus;
		try {
			wait(1);
			Robot robo = new Robot();
			robo.keyPress(java.awt.event.KeyEvent.VK_ENTER);
			wait(1);
			robo.keyRelease(java.awt.event.KeyEvent.VK_ENTER);
			wait(1);
			stepStatus = true;
		} catch (Exception e) {
			stepStatus = false;
		}
		return stepStatus;
	}

	/**
	 * Description : to verify if Alert Exists Method Name : AlertExists Input
	 * parameters : 1) driver instance Created By : bhaskar Created On : 8-May-2017
	 * LastUpdated On : 9-Sep-2018 Last Updated By : bhaskar Email id : Comments :
	 */
	protected static boolean AlertExists(WebDriver driver) // line 835
	{
		boolean stepStatus;
		try {
			driver.switchTo().alert();
			stepStatus = true;
		} catch (Exception e) {
			stepStatus = false;
		}
		return stepStatus;
	}

	/**
	 * Description : to accept the alert Method Name : alertGetText Input parameters
	 * : 1) driver instance Created By : bhaskar Created On : 8-May-2017 LastUpdated
	 * On : 9-Sep-2018 Last Updated By : bhaskar Email id : Comments :
	 */

	protected static String alertGetText(WebDriver driver) // line 861
	{
		String StrAlertText = "";
		try {
			Alert a1 = driver.switchTo().alert();
			StrAlertText = a1.getText();
		} catch (Exception e) {

		}
		return StrAlertText;
	} // line 874

	/**
	 * Description : to accept the alert Method Name : acceptAlert Input parameters
	 * : 1) driver instance Created By : bhaskar Created On : 8-May-2017 LastUpdated
	 * On : 9-May-2017 Last Updated By : bhaskar Email id : Comments :
	 */
	protected boolean acceptAlert(WebDriver driver) // line 886
	{
		boolean stepStatus;
		try {
			Alert a1 = driver.switchTo().alert();
			a1.accept();
			stepStatus = true;
		} catch (Exception e) {
			stepStatus = false;
		}
		return stepStatus;
	}

	/**
	 * Description : to Dismiss the alert Method Name : dismissAlert Input
	 * parameters : 1) driver instance Created By : bhaskar Created On : 8-May-2017
	 * LastUpdated On : 9-May-2017 Last Updated By : bhaskar Email id : Comments :
	 */
	protected boolean dismissAlert(WebDriver driver) // line 912
	{
		boolean stepStatus;
		try {
			Alert a1 = driver.switchTo().alert();
			a1.dismiss();
			stepStatus = true;
		} catch (Exception e) {
			stepStatus = false;
		}
		return stepStatus;
	}

	/**
	 * Description : To log events to Extent Reports and Console Method Name :
	 * logEvent Input parameters : 1) StepStatus 2) Pass Log Message 3) Fail Log
	 * Message 4) driver instance 5) takeScrrenshot flag Created By : bhaskar
	 * Created On : 8-May-2017 LastUpdated On : 9-May-2017 Last Updated By : bhaskar
	 * Email id : Comments :
	 */
	@SuppressWarnings("static-access")
	public static void logEvent(boolean stepStatus, String plog, String flog, WebDriver driver,
			boolean flagTakeScreenshot) {
		if (flagTakeScreenshot == false) {
			if (stepStatus) {

				TestReport.log(LogStatus.INFO.PASS, plog);
				System.out.println("   << Pass >> " + plog);
			} else {
				TestReport.log(LogStatus.INFO.FAIL, flog);
				System.out.println("   << Fail >> " + flog);
			}
		} else {
			wait(4);
			if (stepStatus) {
				String ScreenShotPath = getscreenshot(driver);
				String LoggerScreenShotPath = TestReport.addScreenCapture(ScreenShotPath);
				TestReport.log(LogStatus.INFO.PASS, plog + LoggerScreenShotPath);
				System.out.println("   << Pass >> " + plog);
			} else {
				String ScreenShotPath = getscreenshot(driver);
				String LoggerScreenShotPath = TestReport.addScreenCapture(ScreenShotPath);
				TestReport.log(LogStatus.INFO.FAIL, plog + LoggerScreenShotPath);
				System.out.println("   << Fail >> " + plog);
			}
		}
		Assert.assertTrue(stepStatus);
	} // line 977

	/**
	 * Description : get current Date and TimeStamp in the form of String Method
	 * Name : getTimeStamp Input parameters : 1) StepStatus Created By : bhaskar
	 * Created On : 8-May-2017
	 *
	 *
	 *
	 *
	 * LastUpdated On : 9-May-2017 Last Updated By : bhaskar Email id : Comments :
	 */
	public static String getTimeStamp() {
		DateFormat dateTimelnstance = SimpleDateFormat.getDateTimeInstance();
		String DateTimeStamp = dateTimelnstance.format(Calendar.getInstance().getTime());
		DateTimeStamp = DateTimeStamp.replace(",", "");
		DateTimeStamp = DateTimeStamp.replace(" ", "_");
		DateTimeStamp = DateTimeStamp.replace(":", "-");
		return DateTimeStamp;
	} // line 1001

	/**
	 * Description : get getNewRelationShipName with current Date and TimeStamp in
	 * the form of String Method Name : getNewRelationShipName Input parameters : 1)
	 * StepStatus 2) Pass Log Message 3) Fail Log Message 4) driver instance 5)
	 * takeScrrenshot flag Created By : bhaskar Created On : 8-May-2017 LastUpdated
	 * On : 9-May-2017 Last Updated By : bhaskar Email id : Comments :
	 */
	public static String getNewRelationShipName() {
		DateFormat dateTimelnstance = SimpleDateFormat.getDateTimeInstance();
		String DateTimeStamp = dateTimelnstance.format(Calendar.getInstance().getTime());
		DateTimeStamp = DateTimeStamp.replace(",", "");
		DateTimeStamp = DateTimeStamp.replace(" ", "_");
		DateTimeStamp = DateTimeStamp.replace(":", "-");
		DateTimeStamp = "Aauto " + DateTimeStamp;
		return DateTimeStamp;
	} // line 1026

	/**
	 * Description : take screenshot of current driver instance Method Name :
	 * getscreenshot Input parameters : 1) driver instance Created By : bhaskar
	 * Created On : 8-May-2017 LastUpdated On : 9-May-2017 Last Updated By : bhaskar
	 * Email id : Comments :
	 */
	public static String getscreenshot(WebDriver driver) // line 1038
	{
		try {
			StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
			StackTraceElement stackTraceElement = stackTraceElements[3];
			String CurrentTestCase = stackTraceElement.getMethodName();
			CurrentTestCase = CurrentTestCase.replace(".java", "");

			String ScreenshotName;
			DateFormat dateTimelnstance = SimpleDateFormat.getDateTimeInstance();
			String DateTimeStamp = dateTimelnstance.format(Calendar.getInstance().getTime());
			DateTimeStamp = DateTimeStamp.replace(",", "");
			DateTimeStamp = DateTimeStamp.replace(" ", "_");
			DateTimeStamp = DateTimeStamp.replace(":", "-");
			ScreenshotName = CurrentTestCase + "_" + DateTimeStamp;
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			String Dest = ScreenshotsFoLderPath + "\\" + ScreenshotName + ".png";
			File destination = new File(Dest);
			FileUtils.copyFile(source, destination);
			return Dest;
		} catch (Exception e) {
			e.getMessage();
		}
		return CurrentDateAndTime; // juvo
	} // line 1066

	/**
	 * Description : Create a folder in results with date and time stamp Method Name
	 * : createresultsfolder Input parameters : NA Created By : bhaskar Created On :
	 * 8-May-2017 LastUpdated On : 9-May-2017 Last Updated By : bhaskar Email id :
	 * Comments :
	 */
	public static String createresultsfolder() // line1078
	{
		DateFormat dateTimelnstance = SimpleDateFormat.getDateTimeInstance();
		String DateTimeStamp = dateTimelnstance.format(Calendar.getInstance().getTime());
		DateTimeStamp = DateTimeStamp.replace(",", "");
		DateTimeStamp = DateTimeStamp.replace(" ", "_");
		DateTimeStamp = DateTimeStamp.replace(":", "-");
		ResultsFolderPath = System.getProperty("user.dir") + "\\" + ResultsFolderPath;
		File dir = new File((ResultsFolderPath) + "\\" + DateTimeStamp);
		dir.mkdir();
		File dir2 = new File(ResultsFolderPath + "\\" + DateTimeStamp + "\\Screenshots");
		dir2.mkdir();
		CurrentDateAndTime = DateTimeStamp;
		return ResultsFolderPath + "\\" + DateTimeStamp;
	}

	public static String getDriver(WebDriver driver) {

		return null;
	}

	/**
	 * Description : Used to click on a webelement Method Name : jsClickElement
	 * Input parameters : (1) WebElement (2) WebDriver Created By : bhaskar Created
	 * On : 8-May-2017 LastUpdated On : 9-May-2017 Last Updated By : bhaskar Email
	 * id : Comments : @return -- True/False
	 */
	protected static boolean jsClickElement(By by, WebDriver driver) // line 106 of radhika
	{
		boolean stepstatus;
		try {
			highlight(by, driver);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeAsyncScript("arguments[0].click();", driver.findElement(by));
			stepstatus = true;
		} catch (Exception e) {
			stepstatus = false;
		}
		return stepstatus;
	}

	protected static boolean getText(By by, WebDriver driver) // line 68
	{
		boolean stepstatus;
		try {
			driver.findElement(by).getText();
			stepstatus = true;
		} catch (Exception e) {
			stepstatus = false;
		}
		return stepstatus;
	}

	protected static boolean verifyText(By by, String VerificationText, WebDriver driver) // line 68
	{
		boolean stepstatus = false;
		try {
			if (VerificationText == driver.findElement(by).getText()) {
				stepstatus = true;
			}
		} catch (Exception e) {
			stepstatus = false;
		}
		return stepstatus;
	}

//--added by bhaskar

	protected static boolean verifyElementPresent(By by, WebDriver driver) // line 68
	{
		boolean stepstatus = false;
		try {
			if (driver.findElement(by).isDisplayed()) {
				stepstatus = true;
			}
		} catch (Exception e) {
			stepstatus = false;
		}
		return stepstatus;
	}

	/**
	 * Description : set Text in elemwnt And press Enter Method Name :
	 * setTextAndEscape Input parameters : 1) driver instance Created By : bhaskar
	 * Created On : 8-May-2017 LastUpdated On : 9-May-2017 Last Updated By : bhaskar
	 * Email id : Comments :
	 */

	protected static boolean setTextAndEnter(By by, String Value, WebDriver driver) // line 283
	{
		boolean status = false;
		try {
			highlight(by, driver);
			driver.findElement(by).click();
			driver.findElement(by).clear();
			driver.findElement(by).sendKeys(Value);
			// String webValue = driver.findElement(by).getAttribute("value");
			driver.findElement(by).sendKeys(Keys.ENTER);
			Thread.sleep(500);

			status = true;

		} catch (Exception e) {
			status = false;
		}
		return status;
	}

	protected static boolean getTextandStore(By by, int col, WebDriver driver) // line 68
	{
		boolean stepstatus;
		try {
			String StoreText = driver.findElement(by).getText();
			System.out.println(StoreText);
			putActualResult(col, StoreText,1);//1 is rownum

			stepstatus = true;
		} catch (Exception e) {
			stepstatus = false;
		}
		return stepstatus;

	}

	protected static boolean getTextandcompare(By by, int ExpectedCol, int ActualCol, WebDriver driver) // line 68
	{
		boolean stepstatus = false;
		try {
			String StoreText = driver.findElement(by).getText();
			System.out.println("---1235---" + StoreText);
			putActualResult(ActualCol, StoreText,1);// 1 is rownum
			if (getExpectedResult(ExpectedCol) == StoreText) {
				stepstatus = true;
				System.out.println("---1240---MATCH-- " + StoreText);
			}
		} catch (Exception e) {
			stepstatus = false;
		}
		return stepstatus;
	}

	protected static boolean getTextlist(By by, WebDriver driver) // line 68
	{
		boolean stepstatus = false;
		try {
			By mySelector = By.xpath("//div[@class ='bubble-message sc-fjdhpX eqkGDJ']");
			List<WebElement> myElements = driver.findElements(mySelector);
			for (WebElement e : myElements) {
				System.out.println("ele 1 is -   "+myElements.get(1).getText());
				System.out.println(e.getText());
			}
			stepstatus = true;
		} catch (Exception e) {
			stepstatus = false;
		}
		return stepstatus;
	}
	protected static boolean getTextlistentry(By by, WebDriver driver, int responseNo,String StrValue) // line 68
	{
		boolean stepstatus = false;
		try {int lastelementsize = 0;
			By mySelector = By.xpath("//div[@class ='bubble-message sc-fjdhpX eqkGDJ']");
			
			List<WebElement> myElements = driver.findElements(mySelector);
			String s = "";
			for (WebElement e : myElements) {
				
				System.out.println(responseNo+" "+e.getText());
				s = s+e.getText();
				
				putActualResult(5,s,responseNo);		
				
				if (e.getText().contains(StrValue)) {
					putActualResult(6,"PASS",responseNo);
				}
				else{
					putActualResult(6,"FAIL",responseNo);
				}
				
				System.out.println(responseNo+" 		"+s);
				}
				lastelementsize= lastelementsize+myElements.size();
				System.out.println("		last size is " +lastelementsize);
			 
			stepstatus = true;
		} catch (Exception e) {
			stepstatus = false;
		}
		return stepstatus;
	}
}