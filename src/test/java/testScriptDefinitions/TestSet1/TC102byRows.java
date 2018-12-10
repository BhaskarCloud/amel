package testScriptDefinitions.TestSet1;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import ObjectRepository.pgConversation;
import ObjectRepository.pgHome;
import ObjectRepository.pg_Contact;
import testScriptDefinitions.UserLibrary.UserLibrary;

@SuppressWarnings("unused")
public class TC102byRows extends testScriptDefinitions.UserLibrary.UserLibrary {

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
		logEvent(stepstatus, plog, flog, driver, false); // false means don't take screenshot.
		wait(4);

		stepstatus = login(getdata("UserName"), getdata("Password"), driver);
		plog = "login is successful";
		flog = "No, login is not successful";
		logEvent(stepstatus, plog, flog, driver, false);
		wait(4);

		highlight(pgHome.btn_UnlicensedService, driver);
		stepstatus = verifyElementPresent(pgHome.btn_UnlicensedService, driver);
		plog = "Able to verify UnlicensedService button";
		flog = "Unable to verify UnlicensedService button";
		logEvent(stepstatus, plog, flog, driver, false); // True means take screenshot

		stepstatus = clickElement(pgHome.btn_UnlicensedService, driver);
		plog = "Able to click  UnlicensedService button";
		flog = "Unable to click  UnlicensedService button";
		logEvent(stepstatus, plog, flog, driver, false); // True means take screenshot
		wait(5);

		System.out.println(runMultipleEntrypoint(driver) + " is PASS");
		wait(5);
	}

	/**
	 * @param driver
	 * @return
	 */

	public static String runMultipleEntrypoint1(WebDriver driver) {
		String testCaseNo = null;
		String StrEntryPoint = null;
		String resp = null;

		try {
			StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
			StackTraceElement stackTraceElement = stackTraceElements[2];
			String ClassName = stackTraceElement.getMethodName();

			FileInputStream fis = new FileInputStream("RunManager.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet("TestData1");
			System.out.println("Total row in test case are ---------" + sheet.getLastRowNum());
		 
			Row row = sheet.getRow(0);
			testCaseNo = row.getCell(0).getStringCellValue();
			 System.out.println(" testCaseName--------------- " + testCaseNo);
			 
			for (int i = 0; i <= sheet.getLastRowNum(); i++) {
				row = sheet.getRow(i + 1);

				if (row == null || row.getCell(0) == null)
					continue;
				//System.out.print(row.getCell(0).getStringCellValue());

//testCaseNo = row.getCell(0).getStringCellValue();
String testCaseName = row.getCell(1).getStringCellValue();
				// System.out.println(" testCaseName--------------- " +
				// testCaseNo+testCaseName);

				Cell EntryPoint = row.getCell(3);
				StrEntryPoint = EntryPoint.getStringCellValue();

				Cell cellresp = row.getCell(4);
				resp = cellresp.getStringCellValue();

				System.out.println(StrEntryPoint);
				System.out.println(resp);
				/*
				 * for (int j=0;j<row.getLastCellNum();j++) {
				 * 
				 * System.out.print("  "+sheet.getRow(i).getCell(j)+"\t "); }
				 * System.out.println(" -");
				 */ System.out.println("--------------------");
			}
			/*
			 * File fi = new File("C:\\eclipse-workspace\\openallstate\\RunManager.xlsx");
			 * FileInputStream fin = new FileInputStream(fi); XSSFWorkbook wrk = new
			 * XSSFWorkbook(fin); XSSFSheet shet = wrk.getSheet("TestData1"); //Row rw =
			 * shet.getRow(1); System.out.println("Total row in test case are ---------" +
			 * shet.getLastRowNum()); for (int i = 0; i < shet.getLastRowNum(); i++) { Row
			 * rw = shet.getRow(i + 1); // //if (rw == null || rw.getCell(0) == null)
			 * //continue; testCaseNo = rw.getCell(0).getStringCellValue(); String
			 * testCaseName = rw.getCell(1).getStringCellValue(); //
			 * System.out.println("	testCaseName--------------- " +
			 * testCaseNo+testCaseName); /* Cell EntryPoint = rw.getCell(3); StrEntryPoint =
			 * EntryPoint.getStringCellValue();
			 * 
			 * Cell cellresp = rw.getCell(4); resp = cellresp.getStringCellValue();
			 * 
			 * System.out.println(i+1 +
			 * "---EntryPoint (utterence) from XL to test--------- " + StrEntryPoint);
			 */ // System.out.println(i+1 + "---Expected Result from XL to verify--------------
				// " + 1);
			/*
			 * stepstatus = setEntryPointAndEnter(pgConversation.EntryPointinputText,
			 * StrEntryPoint, driver); plog = "Able to Type Entry point.... '" +
			 * StrEntryPoint; flog = "Unable to Type Entry point.... '" + StrEntryPoint;
			 * logEvent(stepstatus, plog, flog, driver, false); // True means take
			 * screenshot wait(22);
			 * 
			 * stepstatus = getameliaMessagesList(pgConversation.BubbleBox, driver, i,
			 * resp); plog = "Able to get Amelia Response "+i+ " like.... ' "+resp; flog =
			 * "Unable to get Amelia Response like.... ' "+resp; logEvent(stepstatus, plog,
			 * flog, driver, true); // True means take screenshot
			 */
			// System.out.println("---EntryPoint TextBox is "+
			// verifyElementEnableStatus(pgConversation.EntryPointinputText, driver));
			// } // for loop end

			workbook.close();
			fis.close();
		} catch (Exception e) {
			// stepstatus = false;
		}
		return testCaseNo;
	} // end runEntrypointMulti

	/*
	 * public static String getExpectedResult(String FieldName, int col, WebDriver
	 * driver) // Expected Result { String StrValue = null; try {
	 * 
	 * StackTraceElement[] stackTraceElements =
	 * Thread.currentThread().getStackTrace(); StackTraceElement stackTraceElement =
	 * stackTraceElements[2]; String ClassName = stackTraceElement.getMethodName();
	 * 
	 * File fi = new File("C:\\eclipse-workspace\\openallstate\\RunManager.xlsx");
	 * FileInputStream fin = new FileInputStream(fi);
	 * 
	 * XSSFWorkbook wrk = new XSSFWorkbook(fin); XSSFSheet shet =
	 * wrk.getSheet("TestData1");
	 * 
	 * Row rw = shet.getRow(1); //
	 * System.out.println(" Total row in test case are ---------" + //
	 * shet.getLastRowNum()); for (int i = 0; i < shet.getLastRowNum(); i++) { rw =
	 * shet.getRow(i + 1);
	 * 
	 * if (rw == null || rw.getCell(1) == null) continue;
	 * 
	 * String testCaseName = rw.getCell(1).getStringCellValue(); //
	 * System.out.println("	testCaseName--------------- " + testCaseName);
	 * 
	 * Cell c1 = rw.getCell(col);
	 * 
	 * StrValue = c1.getStringCellValue();
	 * 
	 * System.out.println(i + "		Expected Result from XL----------- " +
	 * StrValue);
	 * 
	 * }
	 * 
	 * wrk.close(); fin.close(); } catch (Exception e) {
	 * 
	 * } return StrValue; }
	 * 
	 * private static void GetResponsePushResult(WebDriver driver, String strValue,
	 * int i) { // TODO Auto-generated method stub
	 * 
	 * }
	 * 
	 * /*
	 * 
	 * String EntryPoint2 = "10"; // highlight(pgHome.EntryPointinputText, driver);
	 * stepstatus = setTextAndEnter(pgConversation.EntryPointinputText, EntryPoint2,
	 * driver); plog = "Able to Type Entry point " + EntryPoint2; flog =
	 * "Unable to Type Entry point " + EntryPoint2; logEvent(stepstatus, plog, flog,
	 * driver, false); // True means take screenshot wait(5);
	 * 
	 * stepstatus = getTextlist(pgConversation.BubbleBox, driver); plog =
	 * "Able to get BubbleBox Msg 2"; flog = "Unable to get BubbleBox Msg 2";
	 * logEvent(stepstatus, plog, flog, driver, false); // True means take
	 * screenshot wait(5);
	 * 
	 * String EntryPoint3 = "yes"; // highlight(pgHome.EntryPointinputText, driver);
	 * stepstatus = setTextAndEnter(pgConversation.EntryPointinputText, EntryPoint3,
	 * driver); plog = "Able to Type Entry point " + EntryPoint3; flog =
	 * "Unable to Type Entry point " + EntryPoint3; logEvent(stepstatus, plog, flog,
	 * driver, false); // True means take screenshot wait(5);
	 * 
	 * stepstatus = getTextlist(pgConversation.BubbleBox, driver); plog =
	 * "Able to get BubbleBox Msg 3"; flog = "Unable to get BubbleBox Msg 3";
	 * logEvent(stepstatus, plog, flog, driver, false); // True means take
	 * screenshot wait(5);
	 * 
	 * String EntryPoint4 = "IL"; // highlight(pgHome.EntryPointinputText, driver);
	 * stepstatus = setTextAndEnter(pgConversation.EntryPointinputText, EntryPoint4,
	 * driver); plog = "Able to Type Entry point " + EntryPoint4; flog =
	 * "Unable to Type Entry point " + EntryPoint4; logEvent(stepstatus, plog, flog,
	 * driver, false); // True means take screenshot wait(5);
	 * 
	 * stepstatus = getTextlist(pgConversation.BubbleBox, driver); plog =
	 * "Able to get BubbleBox Msg 4"; flog = "Unable to get BubbleBox Msg 4";
	 * logEvent(stepstatus, plog, flog, driver, false); // True means take
	 * screenshot wait(5); /* stepstatus = quitDriver(driver); plog =
	 * "Able to quit browser"; flog = "Unable to quit browser"; logEvent(stepstatus,
	 * plog, flog, driver, false);
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
	public static String runMultipleEntrypoint(WebDriver driver) {
		String testCaseNo = null;
		String StrEntryPoint = null;
		String resp = null;

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

				if (rw == null || rw.getCell(0) == null)
					continue;
				testCaseNo = rw.getCell(0).getStringCellValue();
				String testCaseName = rw.getCell(1).getStringCellValue();
				System.out.println("	testCaseNo--------------- " +testCaseNo);
				System.out.println("	testCaseName--------------- " + testCaseName);

				Cell EntryPoint = rw.getCell(3);
				StrEntryPoint = EntryPoint.getStringCellValue();

				Cell cellresp = rw.getCell(4);
				resp = cellresp.getStringCellValue();

				System.out.println(EntryPoint);
				 System.out.println(resp);
				/*
				stepstatus = setEntryPointAndEnter(pgConversation.EntryPointinputText, StrEntryPoint, driver);
				plog = "Able to Type Entry point '" + StrEntryPoint;
				flog = "Unable to Type Entry point '" + StrEntryPoint;
				logEvent(stepstatus, plog, flog, driver, false); // True means take screenshot
				wait(5);

				stepstatus = getameliaMessagesList(pgConversation.BubbleBox, driver, i, resp);
				plog = "Able to get BubbleBox Msg ";
				flog = "Unable to get BubbleBox Msg ";
				logEvent(stepstatus, plog, flog, driver, false); // True means take screenshot
*/
				// System.out.println("---EntryPoint TextBox is "+
				// verifyElementEnableStatus(pgConversation.EntryPointinputText, driver));
			} // for lop end

			wrk.close();
			fin.close();
		} catch (Exception e) {
			// stepstatus = false;
		}
		return testCaseNo;
	} // end runEntrypointMulti
}
