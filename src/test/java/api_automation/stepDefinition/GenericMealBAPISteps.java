package api_automation.stepDefinition;
import api_automation.utils.ReusableMethods;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import com.jayway.jsonpath.JsonPath;
import ui_automation.utilities.ConfigurationReader;

public class GenericMealBAPISteps extends ReusableMethods {
	String requstbody;
	Scenario scenario;
	Response response;
	static String stringResponse;
	String token;
	String flag;
	
	/**
	 * this step keeps scenario alive, so we can validate get response once and 
     * validate it in multiple scenarios with scenario outline 
     **/
	@Before
	public void keepScenario(Scenario scenario) {
		this.scenario=scenario;
	}
	
	@Given("User gets token for {string} account when flag is {string}")
	public void user_gets_token_for_account_when_flag_is(String account, String flag) {
		this.flag=flag;
		String userID=null;
	    String password=null;
	    String password2=null;

		if(flag.equalsIgnoreCase("true")){
			
			if(account.equalsIgnoreCase("company")){
				 userID= ConfigurationReader.getProperty("api-config.properties","companyUserID");
				 password=ConfigurationReader.getProperty("api-config.properties","companyPassword");
			}else if(account.equalsIgnoreCase("employee")){
				 userID=ConfigurationReader.getProperty("api-config.properties","employeeUserID");
				 password=ConfigurationReader.getProperty("api-config.properties","employeePassword");
			}else{
				System.out.println("ACCOUNT NAME IS WRONG !!!!!");
			}
		//check if userID and password is not null;
		assertNotNull(userID, "userID is null !!!!");
		assertNotNull(password, "password is null !!!!");
		//submit request to TOKEN api and get response
		token=submitTokenRequest(userID,password);
		
		}
	    
	}

	@When("User submit GET request to {string}")
	public void user_submit_GET_request_to(String apiName) {
		 
		if(flag.equalsIgnoreCase("true")){
			
			//get URL from properties file
			  String URL=ConfigurationReader.getProperty("api-config.properties",apiName);
			  //submit GET request
			  response =given().
							header("Authorization","Bearer "+token).
						when().
						    get(URL);
			  //print response and assign string
			  stringResponse=response.prettyPrint();
			  //write response to cucumber report
			  scenario.write(stringResponse);
			  //attach response to cucumber report
			  scenario.embed(stringResponse.getBytes(), "application/json");
		
		}
	}

	@When("User validate if status code is {int}")
	public void user_validate_if_status_code_is(int statusCode) {
		if(flag.equalsIgnoreCase("true")){
			 assertEquals(statusCode, response.statusCode());
		}
	}

	@Then("User validates {string} in response")
	public void user_validates_in_response(String element) {
		//retrieve element value from response
		String value=JsonPath.read(stringResponse, "$.result."+element).toString();
		scenario.write("Element name: "+element+ "    Value:  "+value);
		assertNotNull(value);
	}
	
	@Then("User validates if {string} exists in response")
	public void user_validates_if_exists_in_response(String element) {		
		//validate if element exists in response
		assertTrue(stringResponse.contains(element));
	
	}

}
