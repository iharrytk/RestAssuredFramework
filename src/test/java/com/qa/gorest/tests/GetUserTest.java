package com.qa.gorest.tests;

import org.testng.annotations.Test;

import com.qa.gorest.client.RestClient;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

public class GetUserTest {
	
	RestClient restClient;
	
	@Test
	public void getAllUsersTest() {
		
		restClient=new RestClient();
		restClient.get("/public/v2/users", true)
					.then().log().all()
						.assertThat()
						.statusCode(200);
		
	}
	
	
	@Test
	public void getUserTest() {
		
		restClient=new RestClient();
		restClient.get("/public/v2/users/8057885", false)
					.then().log().all()
						.assertThat()
						.statusCode(200)
						.and()
						.body("id", equalTo(8057885));
		
	}
	
	@Test
	public void getUserWithQueryParamsTest() {
		
		restClient=new RestClient();
		Map<String,String> queryParams=new HashMap<String, String>();
		queryParams.put("name", "Sharda Asan PhD");
		restClient.get("/public/v2/users", false, null, queryParams)
					.then().log().all()
						.assertThat()
						.statusCode(200)
						.and()
						.body("id", equalTo("8057885"));
		
	}

}
