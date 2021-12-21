package com.aelion.mycrm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aelion.mycrm.services.UserAuthService;

@RestController
@RequestMapping("/user") 
public class UserController {
	
	@Autowired
	public UserAuthService userAuthService; 
	
	@RequestMapping(value="/signin", method=RequestMethod.POST)
	@CrossOrigin(origins = "http://localhost:4200")
	public String signin() {
		System.out.println("testing1");
		return "signin";
	}
	
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	@CrossOrigin
	public String register() {
		System.out.println("testing2");
		return "register";
	}
}


	

