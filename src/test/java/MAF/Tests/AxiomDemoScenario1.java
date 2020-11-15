package MAF.Tests;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import MAF.BasePackage.ProjectBaseClass;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@Listeners(MAF.Utils.ITestListenereBase.class)
public class AxiomDemoScenario1 extends ProjectBaseClass {

	Response response;

	@Test(priority=1)
	public void AxiomTestScenario1() throws Throwable {
		
		logger.info("--------------------------Scenario 1 ---------------------------");

		getEmployees();
		ValidateResponse_code();
		ValidateRsponse_body();
		ValidateRsponse_bodyFull();
	}

	public void getEmployees() throws Throwable {

		RestAssured.baseURI = URL +"/api/v1/employees";
		logger.info("URL used to getch Employee details is : " +RestAssured.baseURI);

		RequestSpecification httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET);
		Thread.sleep(2000);
		//String responseBody = response.getBody().asString();
		String jsonAsString = response.jsonPath().prettify();
		System.out.println(jsonAsString);
		Thread.sleep(3000);
	}

	public void ValidateResponse_code() throws Throwable {
		try { int statusCode = response.getStatusCode();
		System.out.println("Status Code Is - "+statusCode);	
		System.out.println("Scenario1 : Test 1 : Status Code 200-OK is Validated");	
		Assert.assertEquals(statusCode, 200);
		Thread.sleep(3000);}
		catch (Exception e) {
			int statusCode = response.getStatusCode();
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("Scenario1 :Error - 429 - Too Many Requests");
			Assert.assertEquals(statusCode, 200);
		}
	}	

	public void ValidateRsponse_body() throws ParseException, Throwable {			
		//Parsing the contents of the JSON file       
		JSONParser parse = new JSONParser();
		JSONObject jobj = (JSONObject)parse.parse(response.asString());
		JSONArray jsonarr_1 = (JSONArray) jobj.get("data");

		//Get data for Results array
		for(int i=0;i<jsonarr_1.size();i++)
		{
			//Store the JSON objects in an array & Get the index of the JSON object and print the values as per the index
			JSONObject jsonobj_1 = (JSONObject)jsonarr_1.get(i);
			String ProfileImage = (String) jsonobj_1.get("profile_image");
			Assert.assertEquals("", ProfileImage);
			System.out.println("Scenario1 : Test 2 : profile_image are blank for employee ID"+ jsonobj_1.get("profile_image") + jsonobj_1.get("id"));
		}
		Thread.sleep(3000);
	}		
	
	
	//------------------------Extra Verifications----------------------------------
	public void ValidateRsponse_bodyFull() throws ParseException, Throwable {			
		//Parsing the contents of the JSON file       
		JSONParser parse = new JSONParser();
		JSONObject jobj = (JSONObject)parse.parse(response.asString());
		JSONArray jsonarr_1 = (JSONArray) jobj.get("data");

		//Get data for Results array
		for(int i=0;i<jsonarr_1.size();i++)
		{
			//Store the JSON objects in an array & Get the index of the JSON object and print the values as per the index
			JSONObject jsonobj_1 = (JSONObject)jsonarr_1.get(i);
			
			System.out.println(" 'id' :" + jsonobj_1.get("id"));
			System.out.println(" 'employee_name' :" + jsonobj_1.get("employee_name"));
			System.out.println(" 'employee_salary' :" + jsonobj_1.get("employee_salary"));
			System.out.println(" 'employee_age' :" + jsonobj_1.get("employee_age"));
			System.out.println(" 'profile_image' :" +jsonobj_1.get("profile_image"));
			//String id = (String) jsonobj_1.get("id");
			//System.out.println("The Selected ID is" +id);
			
			if (jsonobj_1.get("id").equals("23")) {
				
				Assert.assertEquals(jsonobj_1.get("id"), "23");
				Assert.assertEquals(jsonobj_1.get("employee_name"), "Caesar Vance");
				Assert.assertEquals(jsonobj_1.get("employee_salary"), "106450");
				Assert.assertEquals(jsonobj_1.get("employee_age"), "21");
				Assert.assertEquals(jsonobj_1.get("profile_image"), "");
				System.out.println ("Employee :" +jsonobj_1.get("id") +"Data Va;idation is successful");	
			}
			}
			
		Thread.sleep(3000);
	}		
}
