package MAF.BasePackage;

import java.awt.AWTException;
import java.io.IOException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;

import MAF.Utils.ExcelUtility.ExcelUtilility;

public class ProjectBaseClass {


	//Defining Global Variables//
	public static String projectpath1;
	public static org.apache.logging.log4j.Logger logger;
	public static String URL;
	//public static Double EmployeeID;
	

	@BeforeTest
	public static void Setup() throws AWTException, InterruptedException, IOException {
			
			try {
				
				projectpath1 = System.getProperty("user.dir");
				
				//To Initiate Logger
				logger = org.apache.logging.log4j.LogManager.getLogger();
				logger.info("-------------------------------------New Iteration of Test execution Started-----------------------------------------------/");
				
				//Read URL From Property File
				URL = MAF.Utils.ReadConfig.readPropertiesFile();
				logger.info("URL Fetched :"+URL);
				
				//Read Customer Registration Details from Excel File
//				MAF.Utils.ExcelUtility.ExcelUtilility excel =new ExcelUtilility(projectpath1+"/src/test/resources/ExcelInputFile/MAFCustomerRegistrationInputForm.xlsx","Sheet1");
//				Double EmployeeID = excel.CopyStringNumeric(1, 2);
//				System.out.println("Employee ID Feteched is : " +EmployeeID);	

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				String msg = e.getMessage();
				System.out.println("Error in Base Class" +msg);	
			}

		}

	//TearDown Methods
	@AfterSuite

	public static void TearDown() throws AWTException, InterruptedException {
		MAF.Utils.ExtentReport.extent1.flush();
		System.out.println("AfterSuite Method executed successfully");

	}
}
