package DataProvider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ExcelTestDataProviderDemo {
	FileInputStream fs ;
	XSSFWorkbook wb;
	XSSFSheet sh;

	@Test(dataProvider ="testdataprovider")
	public void printExcel(String fName, String lName, String Email)
	{
			
		System.out.println(fName + " " +lName + " " + Email);
		
	}

	
	@DataProvider(name="testdataprovider")
	public String[][] getExcelData()
	{
		String fileName=System.getProperty("user.dir")+"\\src\\TestData\\TestData.xlsx";
		String sheetName="Sheet1";
		String[][] arrayExcelData= new String[3][3];
		try {
			fs = new FileInputStream(fileName);
			 wb= new XSSFWorkbook(fs);
			 sh= wb.getSheet(sheetName);
			int i=0;
			Iterator<Row> rowiterator = sh.iterator();
			while(rowiterator.hasNext())
			{
				Row row = rowiterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				int j=0;
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					
					
						 arrayExcelData[i][j]=cell.getStringCellValue();
						 j++;
					}
					//System.out.println(cell.getStringCellValue());
					
				i++;
				}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			e.printStackTrace();
		}
		
		return arrayExcelData;
	
	}
@AfterTest
public void teardown() throws Exception
{
	wb.close();
	fs.close();
	
}

}
