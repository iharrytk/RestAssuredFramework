package com.qa.gorest.tests;

import org.testng.annotations.Test;

import com.qa.gorest.client.RestClient;

public class GetUserTest {
	
	RestClient restClient;
	
	@Test
	public void getAllUsers() {
		
		restClient=new RestClient();
		restClient.get("/public/v2/users", true)
					.then().log().all()
						.assertThat()
						.statusCode(200);
		
	}

}
