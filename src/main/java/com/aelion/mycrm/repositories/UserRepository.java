package com.aelion.mycrm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aelion.mycrm.models.User;

public interface UserRepository extends  JpaRepository<User, Long>{
	
	Optional<User> findByUserName(String userName); //fondBy doit toujours correspondre avec l'entité
}
