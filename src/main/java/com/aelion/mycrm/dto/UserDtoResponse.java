package com.aelion.mycrm.dto;

import java.util.List;

public class UserDtoResponse { //ça je l'envoie vers le front (detoOut)
	
	private String token;
	
	private List<String> roles;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	} 
	
	

}
