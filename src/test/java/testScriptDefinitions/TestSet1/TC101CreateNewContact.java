package testScriptDefinitions.TestSet1;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import ObjectRepository.pgConversation;
import ObjectRepository.pgHome;
import ObjectRepository.pg_Contact;

@SuppressWarnings("unused")
public class TC101CreateNewContact extends testScriptDefinitions.UserLibrary.UserLibrary {

	static boolean stepstatus;
	static String plog;
	static String flog;

	@Parameters("browser")
	@Test
	public void TC101_CreateNewContact(String BrowserName) {
		WebDriver driver = launchBrowser(BrowserName);
		driver = SetImplicitWait(5, driver);

		stepstatus = OpenUrl(driver);
		plog = "Able to launch Application";
		flog = "Unable to launch Application";
		logEvent(stepstatus, plog, flog, driver, true); // false means don't take screenshot.
		wait(5);

		stepstatus = login(getdata("UserName"), getdata("Password"), driver);
		plog = "login is successful";
		flog = "No, login is not successful";
		logEvent(stepstatus, plog, flog, driver, true);
		wait(5);

		highlight(pgHome.btn_UnlicensedService, driver);
		stepstatus = verifyElementPresent(pgHome.btn_UnlicensedService, driver);
		plog = "Able to verify UnlicensedService button";
		flog = "Unable to verify UnlicensedService button";
		logEvent(stepstatus, plog, flog, driver, true); // True means take screenshot

		stepstatus = clickElement(pgHome.btn_UnlicensedService, driver);
		plog = "Able to click  UnlicensedService button";
		flog = "Unable to click  UnlicensedService button";
		logEvent(stepstatus, plog, flog, driver, true); // True means take screenshot
		wait(5);

		
		runEntrypointMulti("EntryPoint",3, driver  );
		
	
	}

	/**
	 * @param driver
	 */
	private static void pushEntryGetResponse(WebDriver driver, String StrValue,int responseNo) {
		String EntryPoint = StrValue;
		// highlight(pgHome.EntryPointinputText, driver);
		stepstatus = setTextAndEnter(pgConversation.EntryPointinputText, EntryPoint, driver);
		plog = "Able to Type Entry point '" + EntryPoint;
		flog = "Unable to Type Entry point '" + EntryPoint;
		logEvent(stepstatus, plog, flog, driver, false); // True means take screenshot
		wait(5);
		
		stepstatus = getTextlistentry(pgConversation.BubbleBox, driver,responseNo);
		plog = "Able to get BubbleBox Msg -";
		flog = "Unable to get BubbleBox Msg -";
		logEvent(stepstatus, plog, flog, driver, true); // True means take screenshot
		wait(5);
	}
	public static String runEntrypointMulti(String FieldName, int col,WebDriver driver  ) 
	{
		String StrValue = null;
		try {
			

			StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
			StackTraceElement stackTraceElement = stackTraceElements[2];
			String ClassName = stackTraceElement.getMethodName();

			File fi = new File("C:\\eclipse-workspace\\openallstate\\RunManager.xlsx");
			FileInputStream fin = new FileInputStream(fi);

			XSSFWorkbook wrk = new XSSFWorkbook(fin);
			XSSFSheet shet = wrk.getSheet("TestData1");

			Row rw = shet.getRow(1);
			System.out.println("	Total row in test case are ---------" + shet.getLastRowNum());
			for (int i = 0; i < shet.getLastRowNum(); i++) {
				rw = shet.getRow(i + 1);
				
				if (rw == null || rw.getCell(1) == null)
					continue;
			
				String testCaseName = rw.getCell(1).getStringCellValue();
//				System.out.println("	testCaseName--------------- " + testCaseName);
				
					Cell c1 = rw.getCell(col);
				
					StrValue = c1.getStringCellValue();
				
					System.out.println(i+"		EntryPoint (utterence) to test----------- " + StrValue);
					pushEntryGetResponse( driver, StrValue,i ) ;
	
			 }

			wrk.close();
			fin.close(); 
		} catch (Exception e) {

		}
		return StrValue;
	}
	/*

	String EntryPoint2 = "10";
	// highlight(pgHome.EntryPointinputText, driver);
	stepstatus = setTextAndEnter(pgConversation.EntryPointinputText, EntryPoint2, driver);
	plog = "Able to Type Entry point " + EntryPoint2;
	flog = "Unable to Type Entry point " + EntryPoint2;
	logEvent(stepstatus, plog, flog, driver, false); // True means take screenshot
	wait(5);

	stepstatus = getTextlist(pgConversation.BubbleBox, driver);
	plog = "Able to get BubbleBox Msg 2";
	flog = "Unable to get BubbleBox Msg 2";
	logEvent(stepstatus, plog, flog, driver, false); // True means take screenshot
	wait(5);

	String EntryPoint3 = "yes";
	// highlight(pgHome.EntryPointinputText, driver);
	stepstatus = setTextAndEnter(pgConversation.EntryPointinputText, EntryPoint3, driver);
	plog = "Able to Type Entry point " + EntryPoint3;
	flog = "Unable to Type Entry point " + EntryPoint3;
	logEvent(stepstatus, plog, flog, driver, false); // True means take screenshot
	wait(5);

	stepstatus = getTextlist(pgConversation.BubbleBox, driver);
	plog = "Able to get BubbleBox Msg 3";
	flog = "Unable to get BubbleBox Msg 3";
	logEvent(stepstatus, plog, flog, driver, false); // True means take screenshot
	wait(5);

	String EntryPoint4 = "IL";
	// highlight(pgHome.EntryPointinputText, driver);
	stepstatus = setTextAndEnter(pgConversation.EntryPointinputText, EntryPoint4, driver);
	plog = "Able to Type Entry point " + EntryPoint4;
	flog = "Unable to Type Entry point " + EntryPoint4;
	logEvent(stepstatus, plog, flog, driver, false); // True means take screenshot
	wait(5);

	stepstatus = getTextlist(pgConversation.BubbleBox, driver);
	plog = "Able to get BubbleBox Msg 4";
	flog = "Unable to get BubbleBox Msg 4";
	logEvent(stepstatus, plog, flog, driver, false); // True means take screenshot
	wait(5);
	/*
	 * stepstatus = quitDriver(driver); plog = "Able to quit browser"; flog =
	 * "Unable to quit browser"; logEvent(stepstatus, plog, flog, driver, false);
	 */

	/*
	 * stepstatus = getTextandcompare(pgConversation.BubbleBox, 4, 5, driver); plog
	 * = "Able to get BubbleBox Msg "; flog = "Unable to get BubbleBox Msg ";
	 * logEvent(stepstatus, plog, flog, driver, false); // True means take
	 * screenshot wait(5);
	 * 
	 * stepstatus = getTextandStore(pgConversation.BubbleBox, 5, driver); plog =
	 * "Able to get BubbleBox Msg "; flog = "Unable to get BubbleBox Msg ";
	 * logEvent(stepstatus, plog, flog, driver, false); // True means take
	 * screenshot wait(5);
	 */
}
