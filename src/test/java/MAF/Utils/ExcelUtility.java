package MAF.Utils;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ExcelUtility {
	

public static class ExcelUtilility {
	
		static String FilePath;
		static XSSFWorkbook WorkBook;
		//HSSFWorkbook	
		static XSSFSheet SheetNo;
		//asdasichasd
		
		//Creating Constructor
		public ExcelUtilility (String Filepath, String Sheet) throws IOException {
			
			FilePath = new String(Filepath);
			WorkBook = new XSSFWorkbook(FilePath) ;	
			SheetNo = WorkBook.getSheet(Sheet);
		}
		
		
		
		public static double CopyStringNumeric(int row,int col) throws IOException, InterruptedException {
			// TODO Auto-generated constructor stub
			
			double cellValNumeric = SheetNo.getRow(row).getCell(col).getNumericCellValue();
			return cellValNumeric;
		} 

		
		
		public static String CopyStringString(int row,int col) throws IOException {
			// TODO Auto-generated constructor stub
			
			String cellValString = SheetNo.getRow(row).getCell(col).getStringCellValue();
			return cellValString;

		} 

	}	

}
