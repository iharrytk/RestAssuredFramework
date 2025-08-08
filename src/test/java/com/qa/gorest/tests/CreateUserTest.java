package com.qa.gorest.tests;

import org.testng.annotations.Test;

import com.qa.gorest.client.RestClient;
import com.qa.gorest.pojo.User;
import com.qa.gorest.utils.StringUtils;

public class CreateUserTest {
	
	
	RestClient restClient;
	
	@Test
	public void createAUserTest() {
		//post a user
		User user=new User("harry", StringUtils.getRandonEmail(), "male", "active");
		restClient=new RestClient();
		Integer userid=restClient.post("/public/v2/users", false, "json", user)
					.then().log().all()
						.assertThat()
						.statusCode(201)
						.and()
						.extract()
						.path("id");
		
		
		//get the user that is posted -verify
		//restClient=new RestClient();
		restClient.get("/public/v2/users/"+userid, true)
					.then().log().all()
						.assertThat()
						.statusCode(200);
		
		
	}

}
