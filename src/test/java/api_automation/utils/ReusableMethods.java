package api_automation.utils;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

import api_automation.RequestBuilder.TokenBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import ui_automation.utilities.ConfigurationReader;

public abstract class ReusableMethods {
	
  public String submitTokenRequest(String userID, String password){

	String requstbody=createTokenRequest(userID,password);	
    Response resp=given().
					relaxedHTTPSValidation().
					contentType(ContentType.JSON).
					body(requstbody).
				 when().
				 	post(ConfigurationReader.getProperty("api-config.properties","TOKEN_URL"));
     String token=JsonPath.read(resp.asString(), "$.result.accessToken").toString();
	 System.out.println();
     return token;
	}
	
	
	
	/**
	 * @author Elshan
	 * @date 8/23/2020
	 * @param userID
	 * @param password
	 * @return String
	 * @description: this method is for create MealB token request body
	 */
	public String createTokenRequest(String userID, String password) {
		TokenBuilder reqBody = new TokenBuilder();
		reqBody.setUsernameOrEmailAddress(userID);
		reqBody.setPassword(password);
		// Convert reqBody object to string
		String requstbody = convertObjectToString(reqBody);
		System.out.println(requstbody);
		return requstbody;

	}
	
	
	/**
	 * @author Elshan
	 * @date 8/23/2020
	 * @param Object object
	 * @return String
	 * @description: this method is used to convert Java Object to String
	 */
	public String convertObjectToString(Object req) {
		String jsonStr=null;
		ObjectMapper mapper=new ObjectMapper();		
		try {		
			jsonStr=mapper.writerWithDefaultPrettyPrinter().writeValueAsString(req);		
		} catch (JsonProcessingException e) {			
			e.printStackTrace();
		}	
		return jsonStr;
		
	}
	

	
	/**
	 * @author Elshan R
	 * @param String filePath
	 * @return String 
	 * @date 8/8/2020
	 */
	
	public String readFile(String filePath){				
		String reqBody=null;
		File myFile=new File(filePath);
		
		try {				
			reqBody=FileUtils.readFileToString(myFile);	
		
		} catch (IOException e) {
				e.printStackTrace();
		}	
		return reqBody;
	}

	
	
	/**
	 * @author Elshan R
	 * @param String filePath
	 * @param String date
	 * @return void 
	 * @date 8/11/2020
	 */
	
	public void writeToFile(String filePath, String StrData) {
		
		File myFile=new File(filePath);
		
		try {
			FileUtils.writeStringToFile(myFile, StrData);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	
	
}
