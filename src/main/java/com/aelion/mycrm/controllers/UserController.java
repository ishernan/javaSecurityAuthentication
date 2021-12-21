package com.aelion.mycrm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aelion.mycrm.dto.SignupMessage;
import com.aelion.mycrm.dto.UserDtoRequest;
import com.aelion.mycrm.services.UserAuthService;

@RestController
@RequestMapping("/user") 
public class UserController {
	
	@Autowired
	public UserAuthService userAuthService; //doit tjrs etre injecté
	
	@RequestMapping(value="/signin", method=RequestMethod.POST)
	@CrossOrigin(origins = "http://localhost:4200")
	public String signin() {
		//System.out.println("testing1");
		return "signin";
	}
	
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	@CrossOrigin
	public ResponseEntity<SignupMessage> signup(@RequestBody UserDtoRequest request) {
		this.userAuthService.saveUser(request); 
		SignupMessage message = new SignupMessage("User was successfully registred ");
		return new ResponseEntity<SignupMessage>(message, HttpStatus.OK);
	
		
	}
}


	

