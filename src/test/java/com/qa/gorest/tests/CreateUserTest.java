package com.qa.gorest.tests;

import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.pojo.User;
import com.qa.gorest.utils.StringUtils;

public class CreateUserTest extends BaseTest {
	
	
	//RestClient restClient;
	
	@Test(priority=5)
	public void createAUserTest() {
		//post a user
		User user=new User("harry", StringUtils.getRandonEmail(), "male", "active");
		 
		Integer userid=restClient.post("/public/v2/users",true, false, "json", user)
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
