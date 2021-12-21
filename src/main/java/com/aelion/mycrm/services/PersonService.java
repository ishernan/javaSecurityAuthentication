package com.aelion.mycrm.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aelion.mycrm.models.Person;
import com.aelion.mycrm.repositories.PersonRepository;



@Service
public class PersonService implements Iperson {
	
	@Autowired
	private PersonRepository repo;
	

	@Override
	public List<Person> findAllPerson() {
		
		return (List<Person>) repo.findAll();
		
	}


	
	

}







