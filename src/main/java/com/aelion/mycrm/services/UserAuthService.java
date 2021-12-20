package com.aelion.mycrm.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aelion.mycrm.dto.UserDtoRequest;
import com.aelion.mycrm.models.User;
import com.aelion.mycrm.models.UserRoles;
import com.aelion.mycrm.repositories.UserRepository;

@Service
public class UserAuthService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	private PasswordEncoder passwordEncoder;
	
	
	
	public void saveUser(UserDtoRequest request){ 
		Optional<User> optUser = this.userRepository.findByUserName(request.getUserName());
		
		if(optUser.isPresent())	{	//si optuser est present, error
			throw new RuntimeException("User already exists");
		}
	
		User user = new User();
		user.setUserName(request.getUserName());
		user.setUserPass(this.passwordEncoder.encode(request.getUserPass()));
		
		user.setUserRoles(request.getRoles().stream().map(r -> { //recupere une liste
			UserRoles userRole = new UserRoles();
			userRole.setRole(r);
			return userRole;
		}).collect(Collectors.toSet()));
		
		this.userRepository.save(user); 
	}


	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userRepository.findByUserName(username).get();
		List<UserRoles> roles = user.getUserRoles().stream().collect(Collectors.toList());

		List<GrantedAuthority> grantedAuthorities = roles.stream().map(r -> {
			return new SimpleGrantedAuthority(r.getRole());
		}).collect(Collectors.toList());

		// User specifique a UserDetails
		return new org.springframework.security.core.userdetails.User(username, user.getUserPass(), grantedAuthorities);

	}
	
	
	
	
	
}
