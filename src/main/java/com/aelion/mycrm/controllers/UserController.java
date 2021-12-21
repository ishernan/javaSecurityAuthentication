package com.aelion.mycrm.controllers;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aelion.mycrm.components.JwtUtil;
import com.aelion.mycrm.dto.SignupMessage;
import com.aelion.mycrm.dto.UserDtoRequest;
import com.aelion.mycrm.dto.UserDtoResponse;
import com.aelion.mycrm.exceptions.DisabledUserException;
import com.aelion.mycrm.exceptions.InvalidUserCredentialsException;
import com.aelion.mycrm.services.UserAuthService;

@RestController
@RequestMapping("/user") 
public class UserController {
	
	@Autowired
	public UserAuthService userAuthService; //doit tjrs etre injecté
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtUtil jwtUtil;
	
    @PostMapping("/signin")
    public ResponseEntity<UserDtoResponse> generateJwtToken(@RequestBody UserDtoRequest request) {
        Authentication authentication = null;
        
        try {
            authentication = this.authenticationManager
                .authenticate(
                    new UsernamePasswordAuthenticationToken(
                        request.getUserName(),
                        request.getUserPass()
                    )
            );
            
        } catch (DisabledException e) {
            throw new DisabledUserException("User was disabled");
        } catch (BadCredentialsException e) {
            throw new InvalidUserCredentialsException("Invalid credentials");
        }
        
        // Got a Spring Security User
        User user = (User) authentication.getPrincipal();
        
        Set<String> roles = user
                .getAuthorities()
                .stream().map(r -> r.getAuthority())
                .collect(Collectors.toSet());
        
        // Make a token from "authentication" object
        String token = jwtUtil.generateToken(authentication);
        
        // Create a Response DTO to send to client
        UserDtoResponse response = new UserDtoResponse();
        response.setToken(token);
        response.setRoles(roles.stream().collect(Collectors.toList()));
        
        return new ResponseEntity<UserDtoResponse>(response, HttpStatus.OK);
    }
				
			
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	@CrossOrigin
	public ResponseEntity<SignupMessage> signup(@RequestBody UserDtoRequest request) {
		this.userAuthService.saveUser(request); 
		SignupMessage message = new SignupMessage("User was successfully registred ");
		return new ResponseEntity<SignupMessage>(message, HttpStatus.OK);
	
		
				
	}
}


	

