package com.api.tests;

import static com.api.constant.Role.FD;
import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static org.hamcrest.Matchers.*;

import  org.testng.annotations.Test;

import com.api.utils.SpecUtil;

public class CountAPITest {
	
	@Test
	
	public void verifyCountAPIResponse() {
		given()
//		.baseUri(getProperty("BASE_URI"))
//		.and()
//		.header("Authorization",getToken(FD))
//		.log().uri()
//		.log().method()
//		.log().headers()
		.spec(SpecUtil.requestSpecWithAuth(FD))
		.when()
		.get("/dashboard/count")
		.then()
//		.log().all()
//		.statusCode(200)
		.spec(SpecUtil.responseSpec_OK())
		.body("message",equalTo("Success"))
		.time(lessThan(1000L))
		.body("data",notNullValue())
		.body("data.size()",equalTo(3))
		.body("data.count",everyItem(greaterThanOrEqualTo(0)))
		.body("data.label",everyItem(not(blankOrNullString())))
		.body("data.key", containsInAnyOrder("pending_fst_assignment","pending_for_delivery","created_today"))
		.body(matchesJsonSchemaInClasspath("response-schema/CountAPIResponseSchema-FD.json"));
		
	
	}
	
	@Test
	public void countAPITest_MissingAuthToken() {
		given()
//		.baseUri(getProperty("BASE_URI"))
//		.and()
//		.log().uri()
//		.log().method()
//		.log().headers()
		.spec(SpecUtil.requestSpec())
		.when()
		.get("/dashboard/count")
		.then()
		.spec(SpecUtil.responseSpec_TEXT(401));
	}
	
}
