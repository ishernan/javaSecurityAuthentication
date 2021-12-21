package com.aelion.mycrm.repositories;


//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

//import com.aelion.mycrm.models.Company;
import com.aelion.mycrm.models.Person;


public interface PersonRepository extends CrudRepository<Person, Long>{

	//Company save(Company company);
	
//	List<Person> findAllPerson();
	
	

}
