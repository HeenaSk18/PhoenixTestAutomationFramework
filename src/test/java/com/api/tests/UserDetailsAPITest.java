package com.api.tests;


import static com.api.constant.Role.FD;
import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;

import static com.api.utils.SpecUtil.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class UserDetailsAPITest {

	@Test(description ="Verify if the Userdetails API response is shown correctly", groups= {"api","smoke","regression"})
	public void userDetailsAPITest() throws IOException {
		
	//	ConfigManager configManager = new ConfigManager();
		
	//	Header authHeader = new Header("Authorization", getToken(FD));
				given()
//				.baseUri(getProperty("BASE_URI"))
//				.and()
//				.header(authHeader)
//				.accept(ContentType.JSON)
//				.log().uri()
//				.log().method()
//				.log().body()
				.spec(requestSpecWithAuth(FD))
				.when()
				.get("userdetails")
				.then()
//				.log().all()
//				.statusCode(200)
//				.time(lessThan(1000L))
				.spec(responseSpec())
				.and()
				.body(matchesJsonSchemaInClasspath("response-schema/UserDetailResponseSchema.json"));
				
				
	}
	
}
