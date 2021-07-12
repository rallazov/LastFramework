package api_automation.stepDefinition;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

import api_automation.RequestBuilder.TokenBuilder;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import ui_automation.utilities.ConfigurationReader;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;


public class TokenAPI{
	String requstbody;
	Scenario scenario;
	String response;
	Response resp;
	
	@Before
	public void keepScenario(Scenario scenario) {
		this.scenario=scenario;
	}
	
	@Given("User create request body with {string} , {string}")
	public void user_create_request_body_with(String userID, String password) throws JsonProcessingException {
		//create request body for Token API
		TokenBuilder reqBody=new TokenBuilder();
		reqBody.setUsernameOrEmailAddress(userID);
		reqBody.setPassword(password);
		//Convert reqBody object to string	
		ObjectMapper obMap = new ObjectMapper();
		requstbody=obMap.writerWithDefaultPrettyPrinter().writeValueAsString(reqBody);
		//write response to report
		scenario.write(requstbody);
		scenario.embed(requstbody.getBytes(), "application/json");
	}

	@When("User sumbits post request and gets response")
	public void user_sumbits_post_request_and_gets_response() {
		RestAssured.baseURI= ConfigurationReader.getProperty("api-config.properties","TOKEN_URL");
					resp=given().
						relaxedHTTPSValidation().
						contentType(ContentType.JSON).
						body(requstbody).
					 when().
					 	post();
	response=resp.prettyPrint();
	//write response to report
	scenario.write(response);
	   
	}

	@When("User validates if response status code is {int}")
	public void user_validates_if_response_status_code_is(int statusCode) {
	    assertEquals(statusCode, resp.statusCode());
	}

	@Then("User retrives token from response")
	public void user_retrives_token_from_response() {
		String token=JsonPath.read(response, "$.result.accessToken");
		scenario.write("Token: "+token);
		assertNotNull(token, "Token is null");
	}
	
	@Then("Validate if user gets {string}")
	public void validate_if_user_gets(String error) {		
		String errorMessage=JsonPath.read(response, "$.error.validationErrors[*].message").toString();
		scenario.write("Error Message: "+errorMessage);
		assertTrue(errorMessage.contains(error));
	}
	
}
