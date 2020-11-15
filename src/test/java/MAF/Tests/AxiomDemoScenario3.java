package MAF.Tests;

import java.io.File;

import org.testng.Assert;

import MAF.BasePackage.ProjectBaseClass;
import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveAuthProvider;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.specification.RequestSpecification;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class AxiomDemoScenario3 extends ProjectBaseClass{
	
	public void AxiomTestScenari() throws Throwable {
	//--------------------------------------------------------------------------------
	RestAssured.baseURI = URL +"/api/v1/employees";
	RequestSpecification httpRequest = RestAssured.given();
	Response response = httpRequest.request(Method.GET);
	String responseBody = response.getBody().asString();
	
	//--------------------------------------------------------------------------------------
	//Validations
	//Status COde Validation
	
	int statusCode = response.getStatusCode();
	Assert.assertEquals (statusCode,200);
	
	//Status Line Validation
	String statusLine = response.getStatusLine();
	Assert.assertEquals (statusLine,"");
	
	//----------------------------------------------------------------------------------------
	//Header Validations
	String Header1 = response.getHeader("Cache-Control");
	
	//Full Header Display in the console
	Headers allHeaders=response.headers();
	
	for(Header header:allHeaders) {
		
		System.out.println(header.getName()+ "   "+header.getValue());
		
	}
	
	//----------------------------------------------------------------------------------------
	//Response Body Validation:
	String responseBody1 = response.getBody().asString();
	Assert.assertEquals(responseBody1.contains("Success"), true);
	
	JsonPath jasonpath = response.jsonPath();
	//System.out.println(jasonpath.get("id"));
	//System.out.println(jasonpath.get("employee_name"));
	//System.out.println(jasonpath.get("employee_salary"));
	//System.out.println(jasonpath.get("employee_age"));
	//System.out.println(jasonpath.get("profile_image"));
	
	//------------------------------------------------------------------
	
	//Bacis Authentication
	
	PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
	authScheme.setUserName("agn");
	authScheme.setPassword("agn123");
	
	RestAssured.authentication = authScheme;
	
	//-----------------------------------------------------------------------
	//FIle Upload & Download
	
	File file = new File ("C:\\Users\\LENOVO\\eclipse-workspace\\AxiomDemoProject\\test-output");
	
	RestAssured.baseURI = URL +"/api/v1/employee/";
	//RequestSpecification.httpRequest = RestAssured.given().multiPart(File, file);
	//RequestSpecification httpRequest = RestAssured.given();
	response = httpRequest.request(Method.GET);
	
	
	
	}
	
	
	

}
