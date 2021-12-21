package com.aelion.mycrm.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aelion.mycrm.models.Person;
import com.aelion.mycrm.services.ContactService;


@RestController
@RequestMapping("/person") 
public class PersonController {
	
	@Autowired
	private ContactService contactService;
	
	@CrossOrigin //necessaire pour que les "entetes" puissent passer entre le backend et le front. Pour connecter le back et le front sans raler
	@GetMapping()
	public List<String> getDummy(){ //List<String> est le type d'info que doit etre retourne par la methode 
		ArrayList<String> persons = new ArrayList<String>(); 
		persons.add("Aubert");
		persons.add("Casper");
		persons.add("Isaac");
		
		return persons;
		
	}
	@CrossOrigin 
	@GetMapping("/all")
	public List<Person> getPerson() {
		ArrayList<Person> persons = new ArrayList<Person>();
		persons.add(new Person("Aubert", "Jean-Luc")); 
		persons.add(new Person("Latte", "Truddy")); 
		
		return persons;
	}
	
	@PostMapping()
	@CrossOrigin(origins="http://localhost:4200")
	public ResponseEntity<Person> addPerson(@RequestBody() Person person) {
		System.out.println("Got "+ person.lastName);
		return new ResponseEntity<Person>(this.contactService.save(person), HttpStatus.CREATED);
		
	}
}
