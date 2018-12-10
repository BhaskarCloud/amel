package park;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;

import FrameworkLibrary.ExcelLibrary;
import ObjectRepository.pgConversation;

public class OpenUAT2 extends testScriptDefinitions.UserLibrary.UserLibrary {
	static boolean stepstatus;
	static String plog;
	static String flog;

	public static void main(String args[]) throws IOException {
		WebDriver driver = null ;
		driver = SetImplicitWait(5, driver);

		try{
		File f = new File("C:\\eclipse-workspace\\openallstate\\Amelia_Regression_TestCases.xls");
		// File f =
		// GenericFunctions.getFile(GenericFunctions.getLocalFolder().concat("/TestFiles/"+getTestCasesFileName()));

		String strFilePath = f.getAbsolutePath();

		// POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(strFilePath));
		FileInputStream fs = new FileInputStream(strFilePath);
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		// XSSFWorkbook wb = new XSSFWorkbook(fs);
		HSSFSheet sheet = wb.getSheetAt(0);
		// XSSFSheet shet = wb.getSheet("TestData1");
		HSSFRow row;
		// Row row = shet.getRow(1);
		HSSFCell cell;
		// XSSFCell cell;

		String curTestCase = "";
		int reqCol = 0;
		boolean currentTestCaseDone = false;
		boolean testCasesLeft = true;
		int rowNum = 1;
		String curQuestion = "";
		String curAnswer = "";
		String submitedQuestion = "";
		String receivedAnswer = "";
		while (testCasesLeft) {
			// get current row
			row = sheet.getRow(rowNum);
			// get first cell in that row
			cell = row.getCell(0);// TestCaseName is in the first colunm always.
			curTestCase = cell.getStringCellValue();
			currentTestCaseDone = false;
			if (curTestCase.length() > 1) {
				// report.logStart("Test case - '" + curTestCase);
				System.out.println("Test case - '" + curTestCase);
				// reset conversation
				// report.logStep("CURRENT ROW NUMBER: " + row.getRowNum());
				System.out.println("CURRENT ROW NUMBER: " + row.getRowNum());

				if (row.getRowNum() > 1) {
					// ameliaPage.jsClick(AmeliaPage.NEW_CONVERSATION_BUTTON);
				//	driver.findElement(pgConversation.NEW_CONVERSATION_BUTTON).click();
					// ameliaPage.hold(2000);
					System.out.println("...69...new TC");
					wait(2);
				}
				System.out.println("!currentTestCaseDone  " + !currentTestCaseDone);
				// start executing this test case
				while (!currentTestCaseDone) {
					// report.logStep("CURRENT ROW NUMBER in INNER LOOP: " + row.getRowNum());
					System.out.println("CURRENT ROW NUMBER in INNER LOOP: " + row.getRowNum());
					// get question (always col 2
					curQuestion = row.getCell(1).getStringCellValue();
					// report.logStep("Agent about to ask: " + curQuestion);
					System.out.println("Agent about to ask: " + curQuestion);
					// get answer (col 3)
					curAnswer = row.getCell(3).getStringCellValue();
					// report.logStep("Amelia expected to respond: " + curAnswer);
					System.out.println("Amelia expected to respond: " + curAnswer);

					if (curQuestion.length() > 0)// if question is not empty
					{
						// submit question
						// ameliaPage.setValue(AmeliaPage.TYPE_YOUR_MESSAGE_HERE_TEXTAREA, curQuestion);
						stepstatus = setEntryPointAndEnter(pgConversation.EntryPointinputText, curQuestion, driver);
						plog = "Able to Type Entry point '" + curQuestion;
						flog = "Unable to Type Entry point '" + curQuestion;
						logEvent(stepstatus, plog, flog, driver, false); // True means take screenshot
						wait(15);

						// ameliaPage.hold(500);

						// ameliaPage.click(AmeliaPage.SUBMIT_MESSAGE_BUTTON);//NOT WORKING using click
						// or jsClick
						// ameliaPage.jsClick(AmeliaPage.SUBMIT_MESSAGE_BUTTON);

						// pressENTERtoSubmit();// TEMP workaround for above line
						// ameliaPage.hold(1000);

						// verify question was submitted and posted to the chat area.
						// report.logStart("Submit - '" + curQuestion + "' question
 						// -------------------------------------166");
						System.out.println("Submit - '" + curQuestion + "' question -----------------------177");
 						if (wasThisUserMessageJustPosted(curQuestion, driver)) {
							System.out.println("SUCCESS...180");
							// report.logStop(StepStatus.SUCCESS);
							// ameliaPage.hold(2000);
							wait(2);
						} else {
							// throw new Exception("Failed to post users message");
 							// or if not critical failure -> // report.logStop(StepStatus.FAILURE);
							System.out.println("FAILURE...187");
							// report.logStop(StepStatus.FAILURE);
 							currentTestCaseDone = true;
							// get ready to go to next row
 							rowNum = rowNum + 1;
 							row = sheet.getRow(rowNum);
 							break;
						}

 						// report.logStart("Verify expected response/answer from Amelia received");
						System.out.println("Verify expected response/answer from Amelia received...197");
						if (wasThisAmeliaResponseJustReceived(curAnswer, driver)) {
							// report.logStop(StepStatus.SUCCESS);
							System.out.println("SUCCESS...200");
						} else {
							// Verify if received in multiple answers and not displayed as last one (typical
							// for final answer)
							if (wasThisAmeliaResponseReceivedInThisConversation(curAnswer, driver)) {
								// report.logStop(StepStatus.SUCCESS);
								System.out.println("SUCCESS...206");
							} else {
								// report.logStop(StepStatus.FAILURE);
								System.out.println("FAILURE...209");
								rowNum = rowNum + 1;
								row = sheet.getRow(rowNum);
								currentTestCaseDone = true;
								break;
							}
						}

						// ameliaPage.hold(5500);
						wait(6);
					} else // no question (but more than 1 answers from Amelia expected)
					{
						// wait a bit and verify next reply is posted
						// ameliaPage.hold(3000);
						wait(3);
						// report.logStart("Verify expected response/answer from Amelia received");
						System.out.println("Verify expected response/answer from Amelia received");
						if (wasThisAmeliaResponseJustReceived(curAnswer, driver)) {
							// report.logStop(StepStatus.SUCCESS);
							System.out.println("SUCCESS...228");
						} else {
							// Verify if received in multiple answers and not displayed as last one (typical
							// for final answer)
							if (wasThisAmeliaResponseReceivedInThisConversation(curAnswer, driver)) {
							// report.logStop(StepStatus.SUCCESS);
								System.out.println("SUCCESS...234");
							} else {
								// report.logStop(StepStatus.FAILURE);
								System.out.println("FAILURE...237");
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
					System.out.println("251...Next row number: " + rowNum);
//					System.out.println(
//							"252...row.getCell(0).getStringCellValue()  " + row.getCell(0).getStringCellValue());// if
																													// end of test case
																													// -
					if (row.getCell(0) != null && row.getCell(0).getCellType() != Cell.CELL_TYPE_BLANK)			{		// means cell has tc number		it is not blank																	// done
				//	if (row.getCell(0).getStringCellValue().length() > 1) {
						currentTestCaseDone = true;
						System.out.println("currentTestCaseDone ...255..." + currentTestCaseDone);
					} else// no test case. if no answer - done
					{ System.out.println(".....................193....");
//						System.out.println("257...row.getCell(1).getStringCellValue () "
//								+ row.getCell(1).getStringCellValue() + " row.getCell(1).getStringCellValue() "
//								+ row.getCell(3).getStringCellValue());
			//			if ((row.getCell(1).getStringCellValue().length() < 1)
				//				&& (row.getCell(3).getStringCellValue().length() < 1)) {
					
						if((row.getCell(1) == null && row.getCell(1).getCellType() == Cell.CELL_TYPE_BLANK)	&&
						(row.getCell(3) == null && row.getCell(3).getCellType() == Cell.CELL_TYPE_BLANK))	{// means cell has no utterence and answer
						currentTestCaseDone = true;
							System.out.println("...261...currentTestCaseDone " + currentTestCaseDone);
							testCasesLeft = false;
							System.out.println("testCasesLeft" + testCasesLeft);
						}
					}
				} // end loop for test steps
					// report.logStop(StepStatus.SUCCESS);// TODO we can have a flag to track if
					// failure happens and update
					// this step accordingly
				System.out.println("SUCCESS...265");

			} else // current test case empty go to next row
			{
				rowNum = rowNum + 1;
				row = sheet.getRow(rowNum);
				// if Test case, question and anser emplty - DONE
//*				if ((row.getCell(1).getStringCellValue().length() < 1)
	//					&& (row.getCell(3).getStringCellValue().length() < 1)
		//				&& (row.getCell(0).getStringCellValue().length() < 1)) {
//	*/		
					if((row.getCell(1) != null && row.getCell(1).getCellType() != Cell.CELL_TYPE_BLANK)	&&
							(row.getCell(3) != null && row.getCell(3).getCellType() != Cell.CELL_TYPE_BLANK) &&
							(row.getCell(0) != null && row.getCell(1).getCellType() != Cell.CELL_TYPE_BLANK)	)	{
					currentTestCaseDone = true;
					System.out.println("...280...currentTestCaseDone " + currentTestCaseDone);
					testCasesLeft = false;
					System.out.println("testCasesLeft" + testCasesLeft);
				}
			}

		} // end loop for test cases WHILE

	// TODO Logout can go here if needed
			
	}catch(Exception excp)
	{
		// close last open test step - it should do it only if not already closed?
		// abortTest(excp);
		System.out.println("abortTest");
	}finally
	{
		// endTest();
		System.out.println("endTest");
	}
	}
}
	
/**
 * @param driver
 * @return
 */



/*
 * // for (int i = 0; i < sheet.getLastRowNum(); i++) { Cell c0 =
 * row.getCell(0); row rro =1; while (rro.getCell(0) != null &&
 * rro.getCell(0).getCellType() != Cell.CELL_TYPE_BLANK) {
 * 
 * row = sheet.getRow(rro); // Cell c0 = row.getCell(0); Cell c1 =
 * row.getCell(1); Cell c2 = row.getCell(2); Cell c3 = row.getCell(3); //if (c0
 * * != null && c0.getCellType() != Cell.CELL_TYPE_BLANK) {
 * System.out.println(row + "    " + c0.getStringCellValue() + "    " +
 * c1.getStringCellValue() + "    " + c2.getStringCellValue() + "    " +
 * c3.getStringCellValue());// if end of test case - done } rro=rro+1; //} } }
 */