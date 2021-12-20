package com.aelion.mycrm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aelion.mycrm.models.Company;
import com.aelion.mycrm.services.CompanyService;


@RestController
@RequestMapping("/companies") 
public class CompanyController {
	
	
	@Autowired
	private CompanyService companyService;

	@PostMapping()
	@CrossOrigin(origins="http://localhost:4200")
	public ResponseEntity<Company> addCompany(@RequestBody() Company company) {
		//System.out.println("Got "+ person.lastName);
		return new ResponseEntity<Company>(this.companyService.save(company), HttpStatus.CREATED);
		
	}

}
