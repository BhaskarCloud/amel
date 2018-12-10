package park;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
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
public class TC101CreateNewContact extends testScriptDefinitions.UserLibrary.UserLibrary {

	static boolean stepstatus;
	static String plog;
	static String flog;

	@Parameters("browser")
	@Test
	public void Amelia_POC_(String BrowserName) //throws Exception 
	{ //TestContainer testContainer
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
		try {
			// startTest();
			// GenericFunctions genericFunctions = new GenericFunctions(report);
			File f = new File("C:\\eclipse-workspace\\openallstate\\Amelia_Regression_TestCases.xls");
			// File f =
			// GenericFunctions.getFile(GenericFunctions.getLocalFolder().concat("/TestFiles/"+getTestCasesFileName()));

			String strFilePath = f.getAbsolutePath();

			// POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(strFilePath));
			FileInputStream fs = new FileInputStream(strFilePath);
			 HSSFWorkbook wb = new HSSFWorkbook(fs);
			//XSSFWorkbook wb = new XSSFWorkbook(fs);
			 HSSFSheet sheet = wb.getSheetAt(0);
			//XSSFSheet shet = wb.getSheet("TestData1");
			 HSSFRow row;
			//Row row = shet.getRow(1);
			 HSSFCell cell;
			//XSSFCell cell;

			String curTestCase = "";
			int reqCol = 0;
			boolean currentTestCaseDone = false;
			boolean testCasesLeft = true;
			int rowNum = 1;
			String curQuestion = "";
			String curAnswer = "";
			String submitedQuestion = "";
			String receivedAnswer = "";

			// Load needed page objects from Page object model
			/*
			 * AmeliaPage ameliaPage = (AmeliaPage) createPage( AmeliaPage.class,
			 * getDeviceWebDriver() ); ameliaPage.setLog(report);
			 * 
			 * 
			 * //Navigate to URL navigateTo(getEnvironmentUrl()); ameliaPage.hold(4000);
			 * 
			 * 
			 * report.logStart("Login to Amelia");
			 * ameliaPage.setValue(AmeliaPage.USERNAME_TEXTBOX, strUserID); //coming from
			 * TestData.xml strUserID ameliaPage.hold(300);
			 * ameliaPage.setValue(AmeliaPage.PASSWORD_TEXTBOX, strUserPswrd); //coming from
			 * TestData.xml strUserPswrd ameliaPage.hold(1500);
			 * ameliaPage.waitForClickable(AmeliaPage.LOGIN_BUTTON, 15, 1000);
			 * ameliaPage.jsClick(AmeliaPage.LOGIN_BUTTON);
			 * 
			 * ameliaPage.hold(2000);
			 * ameliaPage.jsClick(AmeliaPage.UNLICENSEDSERVICE_BUTTON); // Click on
			 * unlicensed service. ameliaPage.hold(3000);
			 * 
			 * if(ameliaPage.waitForVisible(AmeliaPage.TYPE_YOUR_MESSAGE_HERE_TEXTAREA)) {
			 * report.logStop(StepStatus.SUCCESS); } else { throw new
			 * Exception("Not able to login and display textarea to star typing messages");
			 * }
			 */
			// Go through all test cases
			while (testCasesLeft) {
				// get current row
				row = sheet.getRow(rowNum);
				// get first cell in that row
				cell = row.getCell(0);// TestCaseName is in the first colunm always.
				curTestCase = cell.getStringCellValue();
				currentTestCaseDone = false;
				if (curTestCase.length() > 1) {
					//report.logStart("Test case - '" + curTestCase);
System.out.println("Test case - '" + curTestCase);
					// reset conversation
		//			report.logStep("CURRENT ROW NUMBER: " + row.getRowNum());
					System.out.println("CURRENT ROW NUMBER: " + row.getRowNum());
					
					if (row.getRowNum() > 1) {
						//ameliaPage.jsClick(AmeliaPage.NEW_CONVERSATION_BUTTON);
						driver.findElement(pgConversation.NEW_CONVERSATION_BUTTON).click();
						//ameliaPage.hold(2000);
					wait(2);
					}

					// start executing this test case
					while (!currentTestCaseDone) {
							//	report.logStep("CURRENT ROW NUMBER in INNER LOOP: " + row.getRowNum());
						System.out.println("CURRENT ROW NUMBER in INNER LOOP: " + row.getRowNum());
						// get question (always col 2
						curQuestion = row.getCell(1).getStringCellValue();
							//report.logStep("Agent about to ask: " + curQuestion);
						System.out.println("Agent about to ask: " + curQuestion);
						// get answer (col 3)
						curAnswer = row.getCell(3).getStringCellValue();
							//report.logStep("Amelia expected to respond: " + curAnswer);
						System.out.println("Amelia expected to respond: " + curAnswer);
						
						if (curQuestion.length() > 0)// if question is not empty
						{
							// submit question
								//ameliaPage.setValue(AmeliaPage.TYPE_YOUR_MESSAGE_HERE_TEXTAREA, curQuestion);
							stepstatus = setEntryPointAndEnter(pgConversation.EntryPointinputText, curQuestion, driver);
							plog = "Able to Type Entry point '" + curQuestion;
							flog = "Unable to Type Entry point '" + curQuestion;
							logEvent(stepstatus, plog, flog, driver, false); // True means take screenshot
							wait(2);
														
								//	ameliaPage.hold(500);

								// ameliaPage.click(AmeliaPage.SUBMIT_MESSAGE_BUTTON);//NOT WORKING using click
								// or jsClick
								//ameliaPage.jsClick(AmeliaPage.SUBMIT_MESSAGE_BUTTON);
	
								//	pressENTERtoSubmit();// TEMP workaround for above line
								//ameliaPage.hold(1000);

							// verify question was submitted and posted to the chat area.
								//report.logStart("Submit - '" + curQuestion + "' question -------------------------------------166");
							System.out.println("Submit - '" + curQuestion + "' question -----------------------177");
							if (wasThisUserMessageJustPosted(curQuestion, driver)) {
								System.out.println("SUCCESS" );
								//report.logStop(StepStatus.SUCCESS);
								//ameliaPage.hold(2000);
								wait(2);
							} else {
								// throw new Exception("Failed to post users message");
								// or if not critical failure -> // report.logStop(StepStatus.FAILURE);
								System.out.println("FAILURE" );
								//report.logStop(StepStatus.FAILURE);
								currentTestCaseDone = true;
								// get ready to go to next row
								rowNum = rowNum + 1;
								row = sheet.getRow(rowNum);
								break;
							}
							
							//report.logStart("Verify expected response/answer from Amelia received");
							System.out.println("Verify expected response/answer from Amelia received");
							if (wasThisAmeliaResponseJustReceived(curAnswer, driver)) {
								//report.logStop(StepStatus.SUCCESS);
								System.out.println("SUCCESS" );
							} else {
								// Verify if received in multiple answers and not displayed as last one (typical
								// for final answer)
								if (wasThisAmeliaResponseReceivedInThisConversation(curAnswer, driver)) {
									//report.logStop(StepStatus.SUCCESS);
									System.out.println("SUCCESS" );
								} else {
									//report.logStop(StepStatus.FAILURE);
									System.out.println("FAILURE" );
									rowNum = rowNum + 1;
									row = sheet.getRow(rowNum);
									currentTestCaseDone = true;
									break;
								}
							}

							//ameliaPage.hold(5500);
								wait(6);
						} else // no question (but more than 1 answers from Amelia expected)
						{
							// wait a bit and verify next reply is posted
							//ameliaPage.hold(3000);
							wait(3);
							//report.logStart("Verify expected response/answer from Amelia received");
							System.out.println("Verify expected response/answer from Amelia received");
							if (wasThisAmeliaResponseJustReceived(curAnswer, driver)) {
								//report.logStop(StepStatus.SUCCESS);
								System.out.println("SUCCESS" );
							} else {
								// Verify if received in multiple answers and not displayed as last one (typical
								// for final answer)
								if (wasThisAmeliaResponseReceivedInThisConversation(curAnswer, driver)) {
									//report.logStop(StepStatus.SUCCESS);
									System.out.println("SUCCESS" );
								} else {
									//report.logStop(StepStatus.FAILURE);
									System.out.println("FAILURE" );
									// get ready to go to next row
									rowNum = rowNum + 1;
									row = sheet.getRow(rowNum);
									currentTestCaseDone = true;
									break;
								}
							}

						}
						// get ready to go to next row
						rowNum = rowNum + 1;
						row = sheet.getRow(rowNum);
						// report.logStep("Next row number: "+rowNum);
						// if end of test case - done
						if (row.getCell(0).getStringCellValue().length() > 1) {
							currentTestCaseDone = true;
						} else// no test case. if no answer - done
						{
							if ((row.getCell(1).getStringCellValue().length() < 1)
									&& (row.getCell(3).getStringCellValue().length() < 1)) {
								currentTestCaseDone = true;
								testCasesLeft = false;
							}
						}
					} // end loop for test steps
					//report.logStop(StepStatus.SUCCESS);// TODO we can have a flag to track if failure happens and update
														// this step accordingly
					System.out.println("SUCCESS" );

				} else // current test case empty go to next row
				{
					rowNum = rowNum + 1;
					row = sheet.getRow(rowNum);
					// if Test case, question and anser emplty - DONE
					if ((row.getCell(1).getStringCellValue().length() < 1)
							&& (row.getCell(3).getStringCellValue().length() < 1)
							&& (row.getCell(0).getStringCellValue().length() < 1)) {
						currentTestCaseDone = true;
						testCasesLeft = false;
					}
				}

			} // end loop for test cases

			// TODO Logout can go here if needed

		} catch (Exception excp) {
			// close last open test step - it should do it only if not already closed?
			//abortTest(excp);
			System.out.println("abortTest" );
		} finally {
			//endTest();
			System.out.println("endTest" );
		}
	}

	// TODO move this somewhere else
	public void pressENTERtoSubmit() throws AWTException, InterruptedException {

		// Perform keystrokes
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

	}
/*
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

	}

	/**
	 * @param driver
	 * @return
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
//				System.out.println("	testCaseName--------------- " + testCaseName);

				Cell EntryPoint = rw.getCell(3);
				StrEntryPoint = EntryPoint.getStringCellValue();

				Cell cellresp = rw.getCell(4);
				resp = cellresp.getStringCellValue();

				// System.out.println(i+1 + "---EntryPoint (utterence) from XL to test---------
				// " + StrEntryPoint);
				// System.out.println(i+1 + "---Expected Result from XL to verify--------------
				// " + resp);

				stepstatus = setEntryPointAndEnter(pgConversation.EntryPointinputText, StrEntryPoint, driver);
				plog = "Able to Type Entry point '" + StrEntryPoint;
				flog = "Unable to Type Entry point '" + StrEntryPoint;
				logEvent(stepstatus, plog, flog, driver, false); // True means take screenshot
				wait(5);

				stepstatus = getameliaMessagesList(pgConversation.BubbleBox, driver, i, resp);
				plog = "Able to get BubbleBox Msg ";
				flog = "Unable to get BubbleBox Msg ";
				logEvent(stepstatus, plog, flog, driver, false); // True means take screenshot

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
}
