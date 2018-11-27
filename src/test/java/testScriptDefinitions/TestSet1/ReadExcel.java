package testScriptDefinitions.TestSet1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//How to read excel files using Apache POI
public class ReadExcel {
	public static void main (String [] args) throws IOException{
                        //I have placed an excel file 'Test.xlsx' in my D Driver 
			FileInputStream fis = new FileInputStream("RunManager.xlsx"); 
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(1);
                      
                        //Cell A1 = row 0 and column 0. It reads first row as 0 and Column A as 0.
			Row row = sheet.getRow(0);
			Cell cell = row.getCell(0);
                       	System.out.println(cell);
			System.out.println(sheet.getRow(0).getCell(0));
			//String cellval = cell.getStringCellValue();
			//System.out.println(cellval);
			for (int i=0;i<=sheet.getLastRowNum();i++)
			{
				System.out.print(i);
				for (int j=0;j<row.getLastCellNum();j++)
				{
					
					System.out.print("  "+sheet.getRow(i).getCell(j)+"\t ");
				}
				System.out.println(" -");
				System.out.println("--------------------");
			}
	}		
}