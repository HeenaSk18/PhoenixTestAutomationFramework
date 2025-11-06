package com.api.tests;

import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.request.model.CreateJobPayload;
import com.api.utils.FakerDataGenerator;

public class CreateJobAPITestWithFakerData {
//Creating the CreateJobPayload Object
	private CreateJobPayload CreateJobPayload ;
	private final static String COUNTRY ="India";

	@BeforeMethod(description ="Creating createjob api request payload")
	public void setup() {

	CreateJobPayload = FakerDataGenerator.generateFakeCreateJobData();
	}
	
	
	
	@Test(description ="Verify if the create job API is able to create Inwarranty job", groups= {"api","smoke","regression"})

	public void createJobAPITest() {
		
		
		
		
		given()
		.spec(requestSpecWithAuth(Role.FD,CreateJobPayload))
//		.baseUri(ConfigManager.getProperty("BASE_URI"))
//		.and()
//		.header("Authorization", AuthTokenPovider.getToken(Role.FD))
//		.contentType(ContentType.JSON)
//		.body(createjobpayload)
//		.log().all()
		.when()
		.post("/job/create")
		.then()
		//.statusCode(200)
		.spec(responseSpec_OK())
		.body(matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
		.body("message",equalTo("Job created successfully. "))
		.body("data.mst_service_location_id", equalTo(1))
		.body("data.job_number", startsWith("JOB_"));
		
	}

}
