package com.api.tests;

import static org.hamcrest.Matchers.*;

import java.awt.Event;

import org.testng.annotations.Test;import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

import static com.api.constant.Role.*;
import static com.api.utils.AuthTokenPovider.*;
import static com.api.utils.ConfigManager.*;
import static io.restassured.RestAssured.*;

public class MasterAPITest {
	
	@Test
	public void  masterAPITest() {
		given()
		.spec(SpecUtil.requestSpecWithAuth(FD))
//		.baseUri(getProperty("BASE_URI"))
//		.and()
//		.header("Authorization",getToken(FD))
//		.and()
//		.contentType("")
//		.log().all()
		.when()
		.post("master") //default content-type application/url-formencoded
		.then()
		.spec(SpecUtil.responseSpec_OK())
//		.log().all()
//		.statusCode(200)
//		.time(lessThan(1000L))
		.body("message", equalTo("Success"))
		.body("data", notNullValue())
		.body("data", hasKey("mst_oem"))
		.body("data", hasKey("mst_model"))
		.body("$", hasKey("message"))
		.body("$", hasKey( "data"))
		.body("data.mst_oem.size()",equalTo(2))//Check the size of the JSON Array with Mathers
		.body("data.mst_model.size()", greaterThan(0))
		.body("data.mst_oem.id", everyItem(notNullValue()))
		.body("data.mst_oem.name", everyItem(notNullValue()))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/MasterAPIResponseSchema.json"));

	}
	
	@Test
	public void  invalidTokenMasterAPITest() {
		given()
		.spec(SpecUtil.requestSpec())
//		.baseUri(getProperty("BASE_URI"))
//		.and()
//		.header("Authorization","")
//		.and()
//		.contentType("")
//		.log().all()
		
		.when()
		.post("master") //default content-type application/url-formencoded
		.then()
		.spec(SpecUtil.responseSpec_TEXT(401));
	
	
	
	}
	
}
