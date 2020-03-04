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

	FileInputStream fs;
	XSSFWorkbook wb;
	XSSFSheet sh;

	@Test(dataProvider = "testdataprovider")
	public void testmethod(Hashtable<String, String> ht) {

		System.out.println(ht.get("EnterCardNum"));

	}

	@DataProvider(name = "testdataprovider")
	public Object[][] getExcelData() {
		String fileName = System.getProperty("user.dir") + "\\src\\test\\java\\com\\resources\\testdata\\TestData.xlsx";
		String sheetName = "Sheet1";
		Object[][] obj1 = null;
		try {
			fs = new FileInputStream(fileName);
			wb = new XSSFWorkbook(fs);
			sh = wb.getSheet(sheetName);
			int columncount = sh.getRow(0).getLastCellNum();
			int rowcount = sh.getPhysicalNumberOfRows();
			obj1 = new Object[rowcount][1];
			int i = 0;
			Iterator<Row> rowiterator = sh.iterator();
			while (rowiterator.hasNext()) {
				Row row = rowiterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				int j = 0;
				Hashtable<String, String> hashtable = new Hashtable<String, String>();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					if (i < rowcount-1) {
						if (cell.getCellType() == CellType.STRING) {
							hashtable.put(sh.getRow(0).getCell(j).getStringCellValue(),
									sh.getRow(i+1).getCell(j).getStringCellValue());
							System.out.println(
									cell.getCellType() + "--->" + sh.getRow(i+1).getCell(j).getStringCellValue());
						} else if (cell.getCellType() == CellType.NUMERIC) {
							hashtable.put(sh.getRow(0).getCell(j).getStringCellValue(),
									sh.getRow(i+1).getCell(j).getStringCellValue());
							System.out.println(
									cell.getCellType() + "--->" + sh.getRow(i+1).getCell(j).getStringCellValue());
						}
						j++;
					}

				}
				obj1[i][0] = hashtable;
				// System.out.println(cell.getStringCellValue());

				i++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			e.printStackTrace();
		}
		return obj1;

	}

	}
@AfterTest
public void teardown() throws Exception
{
	wb.close();
	fs.close();
	
}

}
