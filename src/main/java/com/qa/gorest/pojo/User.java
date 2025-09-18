package com.qa.gorest.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
	
	@JsonProperty("id")
	Integer id;
	
	@JsonProperty("name")
	String name;
	
	@JsonProperty("email")
	String email;
	
	@JsonProperty("gender")
	String gender;
	
	@JsonProperty("status")
	String status;

	public User(String name, String email, String gender, String status) {
		
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.status = status;
	}
	
	
	

}
