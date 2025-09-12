package com.qa.gorest.tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;

public class CircuitTest extends BaseTest {
	
	@BeforeTest
	public void creteuserSetUp() {
		
		restClient=new RestClient(prop, baseURI);
	}
	
	@Test
	public void getAllUsersTest() {
		
		
		restClient.get("/api/users/2",false, true)
					.then().log().all()
						.assertThat()
						.statusCode(200);
		
	}

}
