package MAF.Utils;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import MAF.BasePackage.ProjectBaseClass;

public class ITestListenereBase extends ProjectBaseClass implements ITestListener {

	public void onTestStart(ITestResult result) {

		System.out.println("New Test Started - " +result.getName());

	}

	public void onTestSuccess(ITestResult result) {

		System.out.println("Test Successfully Finished - " +result.getName());
		MAF.Utils.ExtentReport.ReportPass(result.getName());

	}

	public void onTestFailure(ITestResult result) {

		System.out.println("Test Failed - " +result.getName());
		String TestName2 = result.getName();
		MAF.Utils.ExtentReport.ReportFailed(TestName2);
		
		logger.error(result.getName());


		//MAF.Utils.ExtentReport.extent1.flush();
	}

	public void onTestSkipped(ITestResult result) {

		System.out.println("Test Skipped - " +result.getName());

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

		System.out.println("Test Failed but within success percentage - " +result.getName());

	}

	public void onStart(ITestContext context) {


		System.out.println("This is onStart method - " +context.getOutputDirectory());

	}

	public void onFinish(ITestContext context) {

		System.out.println("This is onFinish method - " +context.getPassedTests());
		System.out.println("This is onFinish method - " +context.getFailedTests());
	}

}
