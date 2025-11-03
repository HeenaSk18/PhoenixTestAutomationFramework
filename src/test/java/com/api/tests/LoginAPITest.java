package com.api.tests;

import static com.api.utils.ConfigManager.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;
import com.api.utils.SpecUtil;

import io.restassured.http.ContentType;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class LoginAPITest {

	@Test
	public void loginAPITest() throws IOException {
		//Rest Assured Code!
		
	//	ConfigManager configManager = new ConfigManager();
		//ConfigManager configManager = null;
		
		
		//Read the property value that is gping to be passed from terminal!
		System.out.println(System.getProperty("env"));
		
		
		
		
		UserCredentials userCredentials = new UserCredentials("iamfd", "password");
		
	given()
		//.baseUri("http://64.227.160.186:9000/v1")
//	.baseUri(getProperty("BASE_URI"))
//	.and()
//	.contentType(ContentType.JSON)
//	.and()
//	.accept(ContentType.JSON)
//	.and()
	
//	.spec(SpecUtil.requestSpec(userCredentials))
//	.and()
	//.body(userCredentials)
//	.log().uri()
//	.log().method()
//	.log().headers()
//	.log().body()
	.spec(SpecUtil.requestSpec(userCredentials))
	.when()
	.post("login")
	.then()
	.spec(SpecUtil.responseSpec_OK())
//	.log().all()
//	.statusCode(200)
//	.time(lessThan(1000L))
//	.and()
	.body("message", equalTo("Success"))
	.and()
	.body(matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
	
	
}
}
