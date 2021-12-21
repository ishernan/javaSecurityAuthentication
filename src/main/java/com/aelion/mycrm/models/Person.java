package com.aelion.mycrm.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="person")
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	
	public String lastName;
	public String firstName;
	public String occupation;
	public String company;
	public String zipCode;
	public String streetName;
	public String city;
	
//	public Person (String lastName, String firstName) {
//		this.lastName = lastName;
//		this.firstName = firstName;
//		
//	}
	
//	public void setId(Long id) {
//		this.setId(id);
//	}
	
	
	
	

}
