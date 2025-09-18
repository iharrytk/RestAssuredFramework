package com.qa.gorest.tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIHttpStatus;
import com.qa.gorest.pojo.User;
import com.qa.gorest.utils.StringUtils;

import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

public class GetUserTest extends BaseTest{
	
	@BeforeTest
	public void userSetUp() {
		
		restClient=new RestClient(prop, baseURI);
	}
	
	
	
	@Test(priority = 1)
	public void getAllUsersTest() {
		
		
		restClient.get("/public/v2/users",true, true)
					.then().log().all()
						.assertThat()
						.statusCode(APIHttpStatus.OK_200.getCode());
		
	}
	
	
	@Test(priority=2)
	public void getUserTest() {
		
		
		restClient.get("/public/v2/users/8121148",false, true)
					.then().log().all()
						.assertThat()
						.statusCode(APIHttpStatus.OK_200.getCode())
						.and()
						.body("id", equalTo(8121148));
		
	}
	
	@Test(priority=3)
	public void getUserWithQueryParamsTest() {
		
		
		Map<String,String> queryParams=new HashMap<String, String>();
		queryParams.put("name", "Sharda Asan PhD");
		restClient.get("/public/v2/users",false, false, null, queryParams)
					.then().log().all()
						.assertThat()
						.statusCode(APIHttpStatus.OK_200.getCode());
						
		
	}
	
	@Test(priority=4)
	public void createAUserTest() {
		//post a user
		User user=new User("harry", StringUtils.getRandonEmail(), "male", "active");
		 
		Integer userid=restClient.post("/public/v2/users",false, true, "json", user)
					.then().log().all()
						.assertThat()
						.statusCode(APIHttpStatus.CREATED_201.getCode())
						.and()
						.extract()
						.path("id");
		
		
		//get the user that is posted -verify
		
		restClient.get("/public/v2/users/"+userid,false, true)
					.then().log().all()
						.assertThat()
						.statusCode(APIHttpStatus.OK_200.getCode());
		
		
	}

}
