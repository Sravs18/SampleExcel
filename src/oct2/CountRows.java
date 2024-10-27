package oct2;

import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CountRows {

	public static void main(String[] args) throws Throwable {
		//Read path of excel file
		FileInputStream fi = new FileInputStream("D:/Sample.xlsx");
		//get wb from file
		XSSFWorkbook wb = new XSSFWorkbook(fi);
		//get sheet from wb
		XSSFSheet ws = wb.getSheet("Emp");
		//get first row from emp sheet
		XSSFRow row = ws.getRow(0);
		//count no of rows in emp sheet
		int rc = ws.getLastRowNum();
		//count no of cells in first row
		int cc = row.getLastCellNum();
		System.out.println("no of rows are::"+rc+"        "+"no of cells are:::"+cc);
		fi.close();
		wb.close();
		
		
		
	}

}
