package com.api.tests;

import static com.api.constant.Role.FD;
import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static org.hamcrest.Matchers.*;

import  org.testng.annotations.Test;

import static com.api.utils.SpecUtil.*;

public class CountAPITest {
	
	@Test(description ="Verify if the count API is giving correct response", groups= {"api","smoke","regression"})
	
	public void verifyCountAPIResponse() {
		given()
//		.baseUri(getProperty("BASE_URI"))
//		.and()
//		.header("Authorization",getToken(FD))
//		.log().uri()
//		.log().method()
//		.log().headers()
		.spec(requestSpecWithAuth(FD))
		.when()
		.get("/dashboard/count")
		.then()
//		.log().all()
//		.statusCode(200)
		.spec(responseSpec_OK())
		.body("message",equalTo("Success"))
		.time(lessThan(1000L))
		.body("data",notNullValue())
		.body("data.size()",equalTo(3))
		.body("data.count",everyItem(greaterThanOrEqualTo(0)))
		.body("data.label",everyItem(not(blankOrNullString())))
		.body("data.key", containsInAnyOrder("pending_fst_assignment","pending_for_delivery","created_today"))
		.body(matchesJsonSchemaInClasspath("response-schema/CountAPIResponseSchema-FD.json"));
		
	
	}
	
	@Test(description ="Verify if the count API is giving correct status code for invalid token", groups= {"api","negative","smoke","regression"})

	public void countAPITest_MissingAuthToken() {
		given()
//		.baseUri(getProperty("BASE_URI"))
//		.and()
//		.log().uri()
//		.log().method()
//		.log().headers()
		.spec(requestSpec())
		.when()
		.get("/dashboard/count")
		.then()
		.spec(responseSpec_TEXT(401));
	}
	
}
