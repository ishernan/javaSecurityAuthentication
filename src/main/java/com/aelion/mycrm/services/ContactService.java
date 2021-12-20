package com.aelion.mycrm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aelion.mycrm.models.Person;

import com.aelion.mycrm.repositories.PersonRepository;

@Service
public class ContactService {
	
	@Autowired
	PersonRepository repository;
	public Person save(Person person) {
		return this.repository.save(person); //on laisse repository perstser les donnés dans person
	}

	
}
