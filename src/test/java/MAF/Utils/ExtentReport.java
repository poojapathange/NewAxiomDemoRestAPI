package MAF.Utils;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import MAF.BasePackage.ProjectBaseClass;

public class ExtentReport extends ProjectBaseClass{


	public static ExtentReports extent1 ;


	public static ExtentReports ExtentReport1() {
		
		SimpleDateFormat Formatter = new SimpleDateFormat("yyyymmddhhmmss");
		Date date = new Date(System.currentTimeMillis());
		String date1 = Formatter.format(date);
		String FileName = "MAF_HTML_Execution_Report"+date1+".html";
		//String FileName = "MAF_HTML_Execution_Report.html";
		ExtentReports extent1 = new ExtentReports();
		ExtentHtmlReporter html = new ExtentHtmlReporter(projectpath1+"/HTML_Report/"+FileName);
		html.config().setDocumentTitle("MAF TEST FRAMEWORK");
		html.config().setTheme(Theme.DARK);
		extent1.attachReporter(html);
		return extent1;
	}

	public static void ReportPass (String TestName1) {

		String TestName = TestName1;

		if(extent1==null) {

			extent1 = ExtentReport1();

		}
		//extent = extent1;
		extent1.createTest(TestName)
		.log(Status.PASS, TestName +"Is Successful");
	}


	public static void ReportFailed (String TestName1) {

		String TestName = TestName1;

		if(extent1==null) {

			extent1 = ExtentReport1();

		}

		extent1.createTest(TestName)
		.log(Status.FAIL, TestName +"Is Failed");
	}


}

