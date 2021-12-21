package com.aelion.mycrm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aelion.mycrm.models.Company;



public interface CompanyRepository extends JpaRepository<Company, Long>{
	
	

}
