package com.api.tests;

import static io.restassured.RestAssured.given;

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
		CustomerAddress customerAddress = new CustomerAddress("C 106 ", "Premier Road", "Kohinnor City", "Phoenix Mall", "Mumbai", "411011", "India", "Maharashtra");
		CustomerProduct customerProduct = new CustomerProduct("2025-04-06T18:30:00.000Z", "11294600352105", "11294600352105", "11294600352105", "2025-04-06T18:30:00.000Z", 1,1);
		Problems problems = new Problems(1,"Battery Issue");
		Problems[] problemsArray = new Problems[1];
		problemsArray[0]= problems;
		
		CreateJobPayload createjobpayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemsArray);
		
		
		
		
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
		.spec(SpecUtil.responseSpec_OK());
		
		
	}

}
