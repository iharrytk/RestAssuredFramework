package com.qa.gorest.tests;

import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;

public class CircuitTest extends BaseTest {
	
	@Test
	public void getAllUsersTest() {
		
		
		restClient.get("/api/users/2",false, true)
					.then().log().all()
						.assertThat()
						.statusCode(200);
		
	}

}
