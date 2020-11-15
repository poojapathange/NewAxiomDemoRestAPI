package MAF.Tests;

import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import MAF.BasePackage.ProjectBaseClass;
import MAF.Utils.ExcelUtility.ExcelUtilility;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@Listeners(MAF.Utils.ITestListenereBase.class)
public class AxiomDemoScenario2 extends ProjectBaseClass {

	Response response;
	String responseBody;

	//Main test Method
	@Test(priority=2)
	public void AxiomTestScenario2() throws Throwable {

		logger.info("--------------------------Scenario 2 ---------------------------");

		getEmployees();
		ValidateResponse_code();
		ValidateRsponsebodyNotNull();
		ValidateMessage();
	}

	//Reusable Methods
	public void getEmployees() throws Throwable {

		//Fetching Employee ID dynamically from Excel Sheet 

		MAF.Utils.ExcelUtility.ExcelUtilility excel =new ExcelUtilility(projectpath1+"/src/test/resources/ExcelInputFile/MAFCustomerRegistrationInputForm.xlsx","Sheet1");
		Double EmployeeID = excel.CopyStringNumeric(1, 2);
		double d = EmployeeID;
		int emp_ID = (int) (d);
		RestAssured.baseURI = URL +"/api/v1/employee/"+emp_ID;
		System.out.println("URL used to getch Employee details is : " +RestAssured.baseURI);

		//Hitting URI to get Response

		RequestSpecification httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET);
		Thread.sleep(5000);
		responseBody = response.getBody().asString();
		System.out.println("Response Body is =>  " + responseBody);
		String jsonAsString = response.jsonPath().prettify();
		System.out.println(jsonAsString);
		Thread.sleep(3000);
	}

	public void ValidateResponse_code() throws Throwable {
		try {
			int statusCode = response.getStatusCode();
			System.out.println("Response Status Code Is - "+statusCode);	
			Assert.assertEquals(statusCode, 200);
			System.out.println("Scenario2 : Test 1 : Status Code 200-OK is Validated");
			Thread.sleep(3000);} 
		
		catch (Exception e) {
				int statusCode = response.getStatusCode();
				// TODO: handle exception
				e.printStackTrace();
				System.out.println("Scenario2 :Error - 429 - Too Many Requests");
				Assert.assertEquals(statusCode, 200);
			}
	}	

	public void ValidateRsponsebodyNotNull() throws ParseException, Throwable {

		Assert.assertTrue(responseBody!=null);
		System.out.println("Scenario2 : Test 2 :Response Body Is Not null");
		Thread.sleep(3000);
	}

	public void ValidateMessage() throws Throwable {
		JsonPath jsonPathEvaluator = response.jsonPath();
		String message = jsonPathEvaluator.get("message");
		System.out.println(message);
		Assert.assertEquals(message.contains("Successfully! Record has been fetched."), true);
		Thread.sleep(3000);
	}
}
