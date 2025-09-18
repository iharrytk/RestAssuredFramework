package com.qa.gorest.tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIConstants;
import com.qa.gorest.constants.APIHttpStatus;
import com.qa.gorest.pojo.User;
import com.qa.gorest.utils.ExcelUtil;
import com.qa.gorest.utils.StringUtils;

public class CreateUserTest extends BaseTest {

	@DataProvider
	public Object[][] createUserTestData() {
		return new Object[][] {

				{ "Nidhi", "female", "active" }, 
			{ "Durga", "female", "active" },
			{ "Lalitha", "female", "active" }, };

	}

	@DataProvider
	public Object[][] createUserExcelSheetTestData(){
		
		return ExcelUtil.getTestData("user");
	}

	@BeforeTest
	public void creteuserSetUp() {

		restClient = new RestClient(prop, baseURI);
	}

	@DataProvider
	@Test(priority = 5, dataProvider = "createUserTestData")
	public void createAUserTest(String name, String gender, String status) {
		// post a user
		User user = new User(name, StringUtils.getRandonEmail(), gender, status);

		Integer userid = restClient.post("/public/v2/users", true, false, "json", user).then().log().all().assertThat()
				.statusCode(APIHttpStatus.CREATED_201.getCode()).and().extract().path("id");

		// get the user that is posted -verify

		// RestClient clientGet=new RestClient(prop, baseURI);

		restClient.get("/public/v2/users/" + userid, false, true).then().log().all().assertThat()
				.statusCode(APIHttpStatus.OK_200.getCode());

	}

}
