package com.api.tests;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import java.io.IOException;
import java.security.AuthProvider;

import org.testng.annotations.Test;

import static com.api.constant.Role.*;

import static com.utils.AuthTokenPovider.*;

import static com.utils.ConfigManager.*;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

public class UserDetailsAPITest {

	@Test
	
	public void userDetailsAPITest() throws IOException {
		
	//	ConfigManager configManager = new ConfigManager();
		
		Header authHeader = new Header("Authorization", getToken(FD));
				given()
				.baseUri(getProperty("BASE_URI"))
				.and()
				.header(authHeader)
				.accept(ContentType.JSON)
				.log().uri()
				.log().method()
				.log().body()
				.when()
				.get("userdetails")
				.then()
				.log().all()
				.statusCode(200)
				.time(lessThan(1000L))
				.and()
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/UserDetailResponseSchema.json"));
				
				
	}
	
}
