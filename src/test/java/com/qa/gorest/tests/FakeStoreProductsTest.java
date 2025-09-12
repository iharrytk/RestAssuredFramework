package com.qa.gorest.tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIHttpStatus;

public class FakeStoreProductsTest extends BaseTest {
	
	@BeforeTest
	public void userSetUp() {
		
		restClient=new RestClient(prop, baseURI);
	}
	
	
	
	@Test(priority = 1)
	public void getAllProducts() {
		
		
		restClient.get("/products",false, true)
					.then().log().all()
						.assertThat()
						.statusCode(APIHttpStatus.OK_200.getCode());
		
	}

}
