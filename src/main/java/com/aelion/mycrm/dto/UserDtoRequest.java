package com.aelion.mycrm.dto;

import java.util.List;

public class UserDtoRequest { //du front au back. Ce qui recoit le back (dtoIn)
	
	private String userName;
	private String userPass;
	private List <String> roles;
	
	
	
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	
	
}
