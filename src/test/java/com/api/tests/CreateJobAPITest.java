package com.api.tests;

import static com.api.utils.DateTimeUtil.getTimeWithDaysAgo;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constant.Model;
import com.api.constant.OEM;
import com.api.constant.Platform;
import com.api.constant.Problem;
import com.api.constant.Product;
import com.api.constant.Role;
import com.api.constant.ServiceLocation;
import com.api.constant.Warranty_Status;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import static com.api.utils.SpecUtil.*;

public class CreateJobAPITest {
//Creating the CreateJobPayload Object
	private CreateJobPayload createjobpayload ;
	
	@BeforeMethod(description ="Creating createjob api request payload")
	public void setup() {
		//Creating the CreateJobPayload Object
				Customer customer = new Customer("Heena", "Shaikh", "9899898975", "", "heenashaikh1811@gmail.com", "");
				
				//System.out.println(customer.first_name());
				
				CustomerAddress customerAddress = new CustomerAddress("C 106 ", "Premier Road", "Kohinnor City", "Phoenix Mall", "Mumbai", "411011", "India", "Maharashtra");
				CustomerProduct customerProduct = new CustomerProduct(getTimeWithDaysAgo(10), "12363731112105", "12363731112105", "12363731112105", getTimeWithDaysAgo(10), 
						Product.NEXUS_2.getCode(),
						Model.NEXUS_2_BLUE.getCode());
				Problems problems = new Problems(Problem.SMARTPHONE_IS_RUNNING_SLOW.getCode(),"Battery Issue");
				List<Problems> problemList = new ArrayList<Problems>();
				problemList.add(problems);
				
				createjobpayload = new CreateJobPayload(ServiceLocation.SERVICE_LOCATION_A.getCode(), Platform.FRONT_DEST.getCode(), Warranty_Status.IN_WARRANTY.getCode(), OEM.GOOGLE.getCode(), customer, customerAddress, customerProduct, problemList);
				
	}
	
	
	
	@Test(description ="Verify if the create job API is able to create Inwarranty job", groups= {"api","smoke","regression"})

	public void createJobAPITest() {
		
		
		
		
		given()
		.spec(requestSpecWithAuth(Role.FD,createjobpayload))
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
		.spec(responseSpec_OK())
		.body(matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
		.body("message",equalTo("Job created successfully. "))
		.body("data.mst_service_location_id", equalTo(1))
		.body("data.job_number", startsWith("JOB_"));
		
	}

}
