package com.aelion.mycrm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.RegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.aelion.mycrm.services.UserAuthService;

@Configuration
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter{
	
	
	@Autowired
	private UserAuthService userAuthService;
	
	
	
//	@Autowired	
//	public RegistrationBean jwtAuthFilterRegister(JwtAuthenticationFilter){
//		
//	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManager();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
