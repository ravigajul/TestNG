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
//@Test(dataProvider = "testdataprovider", dataProviderClass = DataReader.class)
	@Test(dataProvider = "testdataprovider")
	public void testmethod(Hashtable<String, String> ht) {

		System.out.println(ht.get("EnterCardNum"));

	}

		@DataProvider(name = "testdataprovider")
	public Object[][] getExcelData() {
		String fileName = System.getProperty("user.dir") + "\\src\\test\\java\\com\\resources\\testdata\\TestData.xlsx";
		String sheetName = "Sheet1";
		Object[][] obj1 = null;
		Hashtable<Integer, Object> ht = new Hashtable<Integer, Object>();
		try {
			fs = new FileInputStream(fileName);
			wb = new XSSFWorkbook(fs);
			sh = wb.getSheet(sheetName);
			int columncount = sh.getRow(0).getLastCellNum();
			int rowcount = sh.getPhysicalNumberOfRows();
			//Object obj1= new Object[rowcount-1][1]
			
			int i = 0;
			Iterator<Row> rowiterator = sh.iterator();

			while (rowiterator.hasNext()) {
				Row row = rowiterator.next();
				
				if (i != 0) {
					System.out.println(row.getRowNum());
					Iterator<Cell> cellIterator = row.cellIterator();
					int j = 0;
					Hashtable<String, String> hashtable = new Hashtable<String, String>();

					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						switch (cell.getCellType()) {
						case STRING:
							try{
								hashtable.put(sh.getRow(0).getCell(j).getStringCellValue(),
							
									sh.getRow(i).getCell(j,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
							}catch(Exception e)
							{
								e.getMessage();
							}
							System.out.println(
									cell.getCellType() + "--->" + sh.getRow(i).getCell(j).getStringCellValue());
							break;
						case NUMERIC:
							hashtable.put(sh.getRow(0).getCell(j).getStringCellValue(),
									sh.getRow(i).getCell(j,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
							System.out.println(
									cell.getCellType() + "--->" + sh.getRow(i).getCell(j).getStringCellValue());
						case BLANK:
							hashtable.put(sh.getRow(0).getCell(j).getStringCellValue(),
									sh.getRow(i).getCell(j,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
							System.out.println(
									cell.getCellType() + "--->" + sh.getRow(i).getCell(j).getStringCellValue());
						default:
							break;
						}
						j++;
					}
					//obj1[i - 1][0] = hashtable;
					ht.put(i,hashtable);

				}
				i++;

			}
			// System.out.println(cell.getStringCellValue());

		} catch (

		FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			e.printStackTrace();
		}
		
		//removing rows with run mode='N'
		
		Set<Integer> keys = ht.keySet();	
	    //Obtaining iterator over set entries
	    Iterator<Integer> itr = keys.iterator();
	    while (itr.hasNext()) {
			Integer integer = (Integer) itr.next();
			 System.out.println("Key: "+integer.toString()+" & Value: "+ht.get(integer));
			Hashtable tempht=(Hashtable) ht.get(integer);
			if(tempht.get("RunMode").toString().equalsIgnoreCase("N")) {
				itr.remove();
				//ht.remove(integer); //this will throw concurrent modification exception
				System.out.println("removed key" + integer);
			}
		}
	    System.out.println(ht.toString());
	    System.out.println(ht.size());
	    
	    obj1= new Object[ht.size()][1];
	    Iterator<Integer> itr1 = keys.iterator();
	    int k=0;
	    while (itr1.hasNext()) {
			Integer integer = (Integer) itr1.next();
			 System.out.println("Key: "+integer.toString()+" & Value: "+ht.get(integer));
			obj1[k][0]=ht.get(integer);
			k++;
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
