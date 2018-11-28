package testScriptDefinitions.TestSet1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import FrameworkLibrary.ExcelLibrary;

public class Forputdata extends ExcelLibrary{

	public static void main(String[] args) throws InvalidFormatException, IOException {
		// TODO Auto-generated method stub
		String StoreText ="ccccccccccccccccccccccccccccccccccccc";
		String BubbleMSGs = "BubbleMSGs";
		putdata(BubbleMSGs, StoreText);
		getExpectedResult(4);
		System.out.println("c..................................c");
		
		//modifyExistingWorkbook();
		//System.out.println("..............."+getEntrypointMulti("EntryPoint",3));
		//loadCammonTestData();
		//System.out.println("c................"+getEntrypoint2("EntryPoint"));
	}
	public static void modifyExistingWorkbook() throws InvalidFormatException, IOException {
	    // Obtain a workbook from the excel file
	    Workbook workbook = WorkbookFactory.create(new File("C:\\eclipse-workspace\\openallstate\\RunManager.xlsx"));

	    // Get Sheet at index 0
	    Sheet sheet = workbook.getSheetAt(0);

	    // Get Row at index 1
	    Row row = sheet.getRow(1);
	    
	    // Get the Cell at index 2 from the above row
	    Cell cell = row.getCell(2);

	    // Create the cell if it doesn't exist
	    if (cell == null)
	        cell = row.createCell(2);
System.out.println(cell);
	    // Update the cell's value
	    cell.setCellType(CellType.STRING);
	    cell.setCellValue("Updated Value");

	    // Write the output to the file
	    FileOutputStream fileOut = new FileOutputStream("C:\\eclipse-workspace\\openallstate\\RunManager.xlsx");
	    workbook.write(fileOut);
	    fileOut.close();

	    // Closing the workbook
	    workbook.close();
	}
}
