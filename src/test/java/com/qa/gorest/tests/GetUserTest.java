package com.qa.gorest.tests;

import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.pojo.User;
import com.qa.gorest.utils.StringUtils;

import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

public class GetUserTest extends BaseTest{
	
	
	
	@Test(priority = 1)
	public void getAllUsersTest() {
		
		
		restClient.get("/public/v2/users",true, true)
					.then().log().all()
						.assertThat()
						.statusCode(200);
		
	}
	
	
	@Test(priority=2)
	public void getUserTest() {
		
		
		restClient.get("/public/v2/users/8064664",false, true)
					.then().log().all()
						.assertThat()
						.statusCode(200)
						.and()
						.body("id", equalTo(8064664));
		
	}
	
	@Test(priority=3)
	public void getUserWithQueryParamsTest() {
		
		
		Map<String,String> queryParams=new HashMap<String, String>();
		queryParams.put("name", "Sharda Asan PhD");
		restClient.get("/public/v2/users",false, false, null, queryParams)
					.then().log().all()
						.assertThat()
						.statusCode(200);
						
		
	}
	
	@Test(priority=4)
	public void createAUserTest() {
		//post a user
		User user=new User("harry", StringUtils.getRandonEmail(), "male", "active");
		 
		Integer userid=restClient.post("/public/v2/users",false, true, "json", user)
					.then().log().all()
						.assertThat()
						.statusCode(201)
						.and()
						.extract()
						.path("id");
		
		
		//get the user that is posted -verify
		
		restClient.get("/public/v2/users/"+userid,false, true)
					.then().log().all()
						.assertThat()
						.statusCode(200);
		
		
	}

}
