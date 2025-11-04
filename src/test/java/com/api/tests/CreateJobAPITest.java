package com.api.tests;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.pojo.CreateJobPayload;
import com.api.pojo.Customer;
import com.api.pojo.CustomerAddress;
import com.api.pojo.CustomerProduct;
import com.api.pojo.Problems;
import com.api.utils.SpecUtil;

public class CreateJobAPITest {
//Creating the CreateJobPayload Object
	
	
	@Test
	public void createJobAPITest() {
		//Creating the CreateJobPayload Object
		Customer customer = new Customer("Heena", "Shaikh", "9899898975", "", "heenashaikh1811@gmail.com", "");
		
		//System.out.println(customer.first_name());
		
		CustomerAddress customerAddress = new CustomerAddress("C 106 ", "Premier Road", "Kohinnor City", "Phoenix Mall", "Mumbai", "411011", "India", "Maharashtra");
		CustomerProduct customerProduct = new CustomerProduct("2025-04-06T18:30:00.000Z", "12343510352105", "12343510352105", "12343510352105", "2025-04-06T18:30:00.000Z", 1,1);
		Problems problems = new Problems(1,"Battery Issue");
		List<Problems> problemList = new ArrayList<Problems>();
		problemList.add(problems);
		
		CreateJobPayload createjobpayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemList);
		
		
		
		
		given()
		.spec(SpecUtil.requestSpecWithAuth(Role.FD,createjobpayload))
//		.baseUri(ConfigManager.getProperty("BASE_URI"))
//		.and()
//		.header("Authorization", AuthTokenPovider.getToken(Role.FD))
//		.contentType(ContentType.JSON)
//		.body(createjobpayload)
//		.log().all()
		.when()
		.post("/job/create")
		.then()
		.statusCode(200)
		.spec(SpecUtil.responseSpec_OK())
		.body(matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
		.body("message",equalTo("Job created successfully. "))
		.body("data.mst_service_location_id", equalTo(1))
		.body("data.job_number", startsWith("JOB_"));
		
	}

}
