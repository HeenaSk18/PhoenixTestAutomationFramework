package com.api.utils;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.awt.Robot;

import static  com.api.constant.Role.*;

import com.api.constant.Role;
import com.api.pojo.UserCredentials;

import io.restassured.http.ContentType;

public class AuthTokenPovider {
	
	private AuthTokenPovider() {
		
	}

	//public static void main(String[] args) {
		public static String getToken( Role role) {
		//make the request for the login api and want to extract the token
		//print it on the console!
		
		UserCredentials userCredentials = null;
		//if(role.equalsIgnoreCase("FD"))
			if(role==FD)
		{
			userCredentials=new UserCredentials("iamfd", "password");
		}	
		
		else if(role==SUP)
		{
			userCredentials=new UserCredentials("iamsup", "password");
		}	
		else if(role==ENG)
		{
			userCredentials=new UserCredentials("iameng", "password");
		}	
		else if(role==QC)
		{
			userCredentials=new UserCredentials("iamqc", "password");
		}	
			
	String token =	given()
		.baseUri(ConfigManager.getProperty("BASE_URI"))
		.contentType(ContentType.JSON)
		//.body(new UserCredentials("iamfd", "password"))
		.body(userCredentials)
		.when()
		.post("login")
		.then()
		.log().ifValidationFails()
		.statusCode(200)
		.body("message",equalTo("Success"))
		.extract()
		.body()
		.jsonPath()
		.getString("data.token");
		
//	System.out.println("------------------------------------");
//	System.out.println(token);
	return token;
	}

}
