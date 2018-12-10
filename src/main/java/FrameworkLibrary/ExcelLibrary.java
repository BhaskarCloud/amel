package FrameworkLibrary;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelLibrary extends ListenerClass {

	public static String getdata(String FieldName) // line 17
	{
		String StrValue = null;
		try {
//			System.out.println("------------20---------" + FieldName + "-----------");

			/*
			 * if (Commondata.containsKey(FieldName.toLowerCase())) { return
			 * Commondata.get(FieldName.toLowerCase()); }
			 */
			StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
			StackTraceElement stackTraceElement = stackTraceElements[2];
			String ClassName = stackTraceElement.getMethodName();

//			System.out.println("---ClassName---------31---------" + ClassName);
			File fi = new File("C:\\eclipse-workspace\\openallstate\\RunManager.xlsx");
			FileInputStream fin = new FileInputStream(fi);

			XSSFWorkbook wrk = new XSSFWorkbook(fin);
			XSSFSheet shet = wrk.getSheet("TestData");

			Row rw = shet.getRow(1);
//			System.out.println("last row num is --39----" + shet.getLastRowNum());
			for (int i = 0; i < shet.getLastRowNum(); i++) {
				rw = shet.getRow(i + 1);
//				System.out.println("---last cell num-43---" + i + "-- " + shet.getRow(i + 1).getLastCellNum()); // we have 4 coll
				
				if (rw == null || rw.getCell(1) == null)
					continue;

				String testCaseName = rw.getCell(1).getStringCellValue();
//				System.out.println("---testCaseName------47---------" + testCaseName);
				if (testCaseName.equals(ClassName)) {
					for (int j = 1; j < rw.getLastCellNum(); j++) {
						Cell c1 = rw.getCell(j + 1);
						if (c1 == null)
							continue;
						String val = c1.getStringCellValue();
//						System.out.println(FieldName + "  ----- 55---" + val);
						String arrval[] = val.split(":=");

						String StrFieldName = arrval[0];
						if (StrFieldName.equalsIgnoreCase(FieldName)) {
							StrValue = arrval[1];
//							System.out.println("----63------StrFieldName---" + StrFieldName + "--------StrValue---" + StrValue);
							break;
						}
					}
				}
			}

			wrk.close();
			fin.close(); // line 69
		} catch (Exception e) {

		}
		return StrValue;
	}

	public static String putdata(String FieldName, String value) // line 77
	{
		String StrValue = "";
		try {
			StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
			StackTraceElement stackTraceElement = stackTraceElements[2];
			String ClassName = stackTraceElement.getMethodName();

			File fi = new File("RunManager.xlsx");
			FileInputStream fin = new FileInputStream(fi);

			XSSFWorkbook wrk = new XSSFWorkbook(fin);
			XSSFSheet shet = wrk.getSheet("TestData1");

			/*
			 * //Now create a row number and a cell where we want to enter a value. //Here
			 * im about to write my test data in the cell B2. It reads Column B as 1 and Row
			 * 2 as 1. Column and Row values start from 0. //The below line of code will
			 * search for row number 2 and column number 2 (i.e., B) and will create a
			 * space. //The createCell() method is present inside Row class. Row row =
			 * sheet.createRow(1); Cell cell = row.createCell(1);
			 */
			Row rw = shet.getRow(1);
			System.out.println("last row num is --39----" + shet.getLastRowNum());
			for (int i = 0; i < shet.getLastRowNum(); i++) {
				rw = shet.getRow(i + 1);
				System.out.println("---last cell num-43---" + i + "-- " + shet.getRow(i + 1).getLastCellNum()); // we
																												// have
																												// 4
																												// coll
				if (rw == null || rw.getCell(1) == null)
					continue;

				String testCaseName = rw.getCell(1).getStringCellValue();
				System.out.println("---testCaseName------47---------" + testCaseName);
				System.out.println("---testCaseName------47---------" + ClassName);
				if (testCaseName.equals(ClassName)) {
					for (int j = 1; j < rw.getLastCellNum(); j++) {
						Cell c1 = rw.getCell(j + 1);
						if (c1 == null)
							continue;
						String val = c1.getStringCellValue();
						System.out.println(FieldName + "  ----- 55---" + val);
						String arrval[] = val.split(":=");

						String StrFieldName = arrval[0];
						if (StrFieldName.equalsIgnoreCase(FieldName)) {
							StrValue = arrval[1];
							System.out.println(
									"----63------StrFieldName---" + StrFieldName + "--------StrValue---" + StrValue);
							break;
						}
					}
				}
			}

			fin.close();
			FileOutputStream fos = new FileOutputStream(fi);
			wrk.write(fos);
			wrk.close();
		} catch (Exception e) {

		}
		return StrValue;
	}

	public static boolean getExecuteStatus(String mthName) // line 132
	{
		boolean flag = false;

		try {
			XSSFWorkbook wrk;
			XSSFSheet shet;
			XSSFRow rww;
			XSSFCell cll;

			File fi = new File("RunManager.xlsx");
			FileInputStream fin = new FileInputStream(fi);

			wrk = new XSSFWorkbook(fin);
			shet = wrk.getSheet("ExecutionController");

			rww = shet.getRow(1);
			for (int i = 0; i <= shet.getLastRowNum(); i++) {
				rww = shet.getRow(i + 1);
				if (rww == null || rww.getCell(0) == null)
					continue;
				cll = rww.getCell(1);
				if (cll.getStringCellValue().equals(mthName))
					;
				{
					cll = rww.getCell(3);
					String result = cll.getStringCellValue();
					flag = Boolean.parseBoolean(result); // line 160
				}

			}
			wrk.close();
			fin.close(); // line 165
		} catch (Exception e) {

		}
		return flag;
	}

	public static void loadCammonTestData() {
		Commondata = new HashMap<String, String>();
		try {
			XSSFWorkbook wrk;
			XSSFSheet shet;
			XSSFRow rww;
			XSSFCell cl1;

			File fi = new File("RunManager.xlsx");
			FileInputStream fin = new FileInputStream(fi);

			wrk = new XSSFWorkbook(fin);
			shet = wrk.getSheet("CommonTestData");

			rww = shet.getRow(1);
			for (int i = 0; i <= shet.getLastRowNum(); i++) {
				rww = shet.getRow(i);
				if (rww == null || rww.getCell(0) == null)
					continue;
				cl1 = rww.getCell(0);
				Cell cl2 = rww.getCell(2);
				Commondata.put(cl1.getStringCellValue().toLowerCase(), cl2.getStringCellValue()); // line 197
				System.out.println(Commondata);
			}
			wrk.close();
			fin.close(); // line 200
		} catch (Exception e) {

		}
	}

	public static int getPriority(String mthName) {
		int priority = 0;
		try {
			XSSFWorkbook wrk;
			XSSFSheet shet;
			XSSFRow rww;
			XSSFCell cl1;

			File fi = new File("RunManager.xlsx");
			FileInputStream fin = new FileInputStream(fi);

			wrk = new XSSFWorkbook(fin);
			shet = wrk.getSheet("ExecutionController");

			rww = shet.getRow(1);
			for (int i = 0; i <= shet.getLastRowNum(); i++)

			{
				rww = shet.getRow(i + 1);
				if (rww == null || rww.getCell(4) == null)
					continue;
				cl1 = rww.getCell(1);
				if (cl1.getStringCellValue().equals(mthName))
					;
				{
					cl1 = rww.getCell(4);
					String result = cl1.getStringCellValue();
					priority = Integer.parseInt(result); // line 235
				}
			}

			wrk.close();
			fin.close(); // line 240
		} catch (Exception e) {

		}
		return priority; // chek karvu
	}

	public static String getDescription(String mthName) // line 250
	{
		String tcDdiscription = null;

		try {
			XSSFWorkbook wrk;
			XSSFSheet shet;
			XSSFRow rww;
			XSSFCell cl1;

			File fi = new File("RunManager.xlsx");
			FileInputStream fin = new FileInputStream(fi);

			wrk = new XSSFWorkbook(fin);
			shet = wrk.getSheet("ExecutionController");

			rww = shet.getRow(1);

			for (int i = 0; i <= shet.getLastRowNum(); i++)

			{
				rww = shet.getRow(i + 1);
				if (rww == null || rww.getCell(0) == null)
					continue;
				cl1 = rww.getCell(1);
				if (cl1.getStringCellValue().equals(mthName))
					;
				{
					cl1 = rww.getCell(2);
					tcDdiscription = cl1.getStringCellValue(); // line 279
				}

			}

			wrk.close();
			fin.close(); // line 286
		} catch (Exception e) {

		}

		return tcDdiscription;

	}

	public static String setExecuteStatus(String mthName, String TCStatus) // line 296
	{
		String StrValue = "";
		try {
			String ClassName = mthName;

			File fi = new File("RunManager.xlsx");
			FileInputStream fin = new FileInputStream(fi);

			XSSFWorkbook wrk = new XSSFWorkbook(fin);
			XSSFSheet shet = wrk.getSheet("ExecutionController");

			Row rw = shet.getRow(1);

			for (int i = 0; i <= shet.getLastRowNum(); i++) {
				rw = shet.getRow(i + 1);
				if (rw == null || rw.getCell(1) == null)
					continue;
				String TestCaseName = rw.getCell(1).getStringCellValue();

				if (TestCaseName.equals(ClassName))
					; // line 318
				{
					Cell cl = rw.getCell(5);
					if (cl == null) {
						cl = rw.createCell(5);
					}
					cl.setCellValue(TCStatus);
					break; // line 326
				}
			}
			fin.close(); // line 329 jovo
			FileOutputStream fos = new FileOutputStream(fi);
			wrk.write(fos);
			wrk.close();
		} catch (Exception e) {

		}
		return StrValue;
	} // line 339

	public static String setLastExecuted(String mthName) // line 340
	{
		String StrValue = "";
		try {
			String ClassName = mthName;

			File fi = new File("RunManager.xlsx");
			FileInputStream fin = new FileInputStream(fi);

			XSSFWorkbook wrk = new XSSFWorkbook(fin);
			XSSFSheet shet = wrk.getSheet("ExecutionController");

			Row rw = shet.getRow(1);

			for (int i = 0; i <= shet.getLastRowNum(); i++) {
				rw = shet.getRow(i + 1);
				if (rw == null || rw.getCell(1) == null)
					continue;

				String TestCaseName = rw.getCell(1).getStringCellValue();
				if (TestCaseName.equals(ClassName))
					; // line 362
				{
					Cell cl = rw.getCell(6);
					if (cl == null) {
						cl = rw.createCell(6);
					}
					cl.setCellValue(CurrentDateAndTime);
					break; // line 370
				}
			}
			fin.close(); // line 373
			FileOutputStream fos = new FileOutputStream(fi);
			wrk.write(fos);
			wrk.close();
		} catch (Exception e) {

		}
		return StrValue;
	} // line 383

	public static void putActualResult(int ColumNo, String value, int RowNo) // line 77 Column F
	{
		String StrValue = "";
		try {
			
			File fi = new File("C:\\eclipse-workspace\\openallstate\\RunManager.xlsx");
			FileInputStream fin = new FileInputStream(fi);

			XSSFWorkbook wrk = new XSSFWorkbook(fin);
			XSSFSheet shet = wrk.getSheet("TestData1");

			Row row = shet.getRow(RowNo);
			Cell cell = row.createCell(ColumNo);//row.getCell(Col);//

			cell.setCellType(cell.CELL_TYPE_STRING);
			cell.setCellValue(value);

			//System.out.println("excel write is--"+value);
			FileOutputStream fos = new FileOutputStream(fi);
			wrk.write(fos);
			wrk.close();
		} catch (Exception e) {

		}
		//return StrValue;
	}
	public static void putResult(int Col, String value, int i) // line 77 Column F
	{
		String StrValue = "";
		try {
			
			File fi = new File("RunManager.xlsx");
			FileInputStream fin = new FileInputStream(fi);

			XSSFWorkbook wrk = new XSSFWorkbook(fin);
			XSSFSheet shet = wrk.getSheet("TestData1");

			Row row = shet.getRow(i);
			Cell cell = row.createCell(Col);//row.getCell(Col);//

			cell.setCellType(cell.CELL_TYPE_STRING);
			cell.setCellValue(value);

			System.out.println("excel write is--"+value);
			FileOutputStream fos = new FileOutputStream(fi);
			wrk.write(fos);
			wrk.close();
		} catch (Exception e) {

		}
		//return StrValue;
	}
	public static String getExpectedResult(int Col) // line 77 Column E
	{
		String StrValue = "";
		try {
			StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
			StackTraceElement stackTraceElement = stackTraceElements[2];
			String ClassName = stackTraceElement.getMethodName();

			File fi = new File("RunManager.xlsx");
			FileInputStream fin = new FileInputStream(fi);

			XSSFWorkbook wrk = new XSSFWorkbook(fin);
			XSSFSheet shet = wrk.getSheet("TestData1");

			// I have added test data in the cell A1 as "SoftwareTestingMaterial.com"
			// Cell A1 = row 0 and column 0. It reads first row as 0 and Column A as 0.
			Row row = shet.getRow(1);
			Cell cell = row.getCell(Col);
			System.out.println("---436 " + cell);
			System.out.println(shet.getRow(0).getCell(Col));
			String cellval = cell.getStringCellValue();
			System.out.println(cellval);

		} catch (Exception e) {

		}
		return StrValue;
	}

	public static String getEntrypoint(String FieldName) // line 17
	{
		String StrValue = null;
		try {
			System.out.println("------------450---------" + FieldName + "-----------");

			/*
			 * if (Commondata.containsKey(FieldName.toLowerCase())) { return
			 * Commondata.get(FieldName.toLowerCase()); }
			 */
			StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
			StackTraceElement stackTraceElement = stackTraceElements[2];
			String ClassName = stackTraceElement.getMethodName();

			System.out.println("---ClassName---------460---------" + ClassName);
			File fi = new File("C:\\eclipse-workspace\\openallstate\\RunManager.xlsx");
			FileInputStream fin = new FileInputStream(fi);

			XSSFWorkbook wrk = new XSSFWorkbook(fin);
			XSSFSheet shet = wrk.getSheet("TestData1");

			Row rw = shet.getRow(1);
			System.out.println("last row num is --468----" + shet.getLastRowNum());
			for (int i = 0; i < shet.getLastRowNum(); i++) {
				rw = shet.getRow(i + 1);
				System.out.println("---last cell num-471---" + i + "-- " + shet.getRow(i + 1).getLastCellNum()); // we
																													// have
																													// 4
																													// coll
				if (rw == null || rw.getCell(1) == null)
					continue;

				String testCaseName = rw.getCell(1).getStringCellValue();
				// String StrValue =rw.getCell(3).getStringCellValue();
				System.out.println("---testCaseName------479---------" + testCaseName);
				if (testCaseName.equals(ClassName)) {
					for (int j = 1; j < rw.getLastCellNum(); j++) {
						Cell c1 = rw.getCell(j + 1);
						System.out.println("---------486---------" + c1);
					}
				}

				System.out.println("---486---" + rw.getCell(3) + "  ");

			}

			wrk.close();
			fin.close(); // line 69
		} catch (Exception e) {

		}
		return StrValue;
	}

	public static String getEntrypointMulti(String FieldName, int col  ) // line 17
	{
		String StrValue = null;
		try {
			//System.out.println("------------20---------" + FieldName + "-----------");

			/*
			 * if (Commondata.containsKey(FieldName.toLowerCase())) { return
			 * Commondata.get(FieldName.toLowerCase()); }
			 */

			StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
			StackTraceElement stackTraceElement = stackTraceElements[2];
			String ClassName = stackTraceElement.getMethodName();

		//	System.out.println("---ClassName---------31---------" + ClassName);
			File fi = new File("C:\\eclipse-workspace\\openallstate\\RunManager.xlsx");
			FileInputStream fin = new FileInputStream(fi);

			XSSFWorkbook wrk = new XSSFWorkbook(fin);
			XSSFSheet shet = wrk.getSheet("TestData1");

			Row rw = shet.getRow(1);
			System.out.println("last row num is --468----" + shet.getLastRowNum());
			for (int i = 0; i < shet.getLastRowNum(); i++) {
				rw = shet.getRow(i + 1);
				//System.out.println("---last cell num-471---" + i + "-- " + shet.getRow(i + 1).getLastCellNum()); // we
																												// have
																												// 8
																												// coll
				if (rw == null || rw.getCell(1) == null)
					continue;
			
				String testCaseName = rw.getCell(1).getStringCellValue();
				System.out.println("---testCaseName------47---------" + testCaseName);
				// if (testCaseName.equals(ClassName)) {
			//	for (int j = 1; j < rw.getLastCellNum(); j++) {
					Cell c1 = rw.getCell(col);
					//if (c1 == null)
						//continue;
					// String val = c1.getStringCellValue();
					// System.out.println(FieldName + " ----- 55---" + val);
					// String arrval = val.toString(); //plit(":=");

					// String StrFieldName = arrval;
					// if (StrFieldName.equalsIgnoreCase(FieldName)) {
					StrValue = c1.getStringCellValue();
					// StrValue = arrval;
					System.out.println("----63------StrFieldName--- --------StrValue---" + StrValue);
					// break;
					// }
				//}
			//}
			 }

			wrk.close();
			fin.close(); // line 69
		} catch (Exception e) {

		}
		return StrValue;
	}
	
	public static String getEntrypoint2(String FieldName) // line 17
	{
		String StrValue = null;
		try {
			System.out.println("------------450---------" + FieldName + "-----------");

			
			 /* if (Commondata.containsKey(FieldName.toLowerCase())) { return
			  Commondata.get(FieldName.toLowerCase()); }*/
			 
			StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
			StackTraceElement stackTraceElement = stackTraceElements[2];
			String ClassName = stackTraceElement.getMethodName();

			System.out.println("---ClassName---------460---------" + ClassName);
			File fi = new File("C:\\eclipse-workspace\\openallstate\\RunManager.xlsx");
			FileInputStream fin = new FileInputStream(fi);

			XSSFWorkbook wrk = new XSSFWorkbook(fin);
			XSSFSheet shet = wrk.getSheet("TestData1");

			Row rw = shet.getRow(1);
			System.out.println("last row num is --468----" + shet.getLastRowNum());
			for (int i = 0; i < shet.getLastRowNum(); i++) {
				rw = shet.getRow(i + 1);
				System.out.println("---last cell num-471---" + i + "-- " + shet.getRow(i + 1).getLastCellNum()); // we
																													// have
																													// 4
																													// coll
				if (rw == null || rw.getCell(1) == null)
					continue;

				String testCaseName = rw.getCell(1).getStringCellValue();
				// String StrValue =rw.getCell(3).getStringCellValue();
				System.out.println("---testCaseName------479---------" + testCaseName);
				if (testCaseName.equals(ClassName)) {
					for (int j = 1; j < rw.getLastCellNum(); j++) {
						Cell c1 = rw.getCell(j + 1);
						System.out.println("---------486---------" + c1);
					}
				}

				System.out.println("---486---" + rw.getCell(3) + "  ");

			}

			wrk.close();
			fin.close(); // line 69
		} catch (Exception e) {

		}
		return StrValue;
	}
	/*public static void modifyExistingWorkbook() throws InvalidFormatException, IOException {
	    // Obtain a workbook from the excel file
	    Workbook workbook = WorkbookFactory.create(new File("RunManager.xlsx"));

	    // Get Sheet at index 0
	    Sheet sheet = workbook.getSheetAt(1);

	    // Get Row at index 1
	    Row row = sheet.getRow(1);
	    
	    // Get the Cell at index 2 from the above row
	    Cell cell = row.getCell(5);

	    // Create the cell if it doesn't exist
	    if (cell == null)
	        cell = row.createCell(5);

	    // Update the cell's value
	    cell.setCellType(CellType.STRING);
	    cell.setCellValue("Updated Value");

	    // Write the output to the file
	    FileOutputStream fileOut = new FileOutputStream("RunManager.xlsx");
	    workbook.write(fileOut);
	    fileOut.close();

	    // Closing the workbook
	    workbook.close();
	}*/
}
