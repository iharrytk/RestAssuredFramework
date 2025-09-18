package com.qa.gorest.tests;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIHttpStatus;
import com.qa.gorest.utils.JsonPathValidator;

import io.restassured.response.Response;

public class FakeStoreProductsTest extends BaseTest {

	@BeforeTest
	public void userSetUp() {

		restClient = new RestClient(prop, baseURI);
	}

	@Test(priority = 1)
	public void getAllProducts() {

		Response response = restClient.get("/products", false, true);

		JsonPathValidator obj = new JsonPathValidator();
		List<Object> titles = obj.readList(response, "$[*].title");
		System.out.println("Validate details of product that has title from given list:" + titles);
		//Test1: Validate details of product that has title from given list
		Assert.assertTrue(titles.contains("Mens Cotton Jacket"));

		// Test2:Validate the get products response code is 200 OK
		Assert.assertEquals(response.getStatusCode(), APIHttpStatus.OK_200.getCode());
		
			
	}

}
