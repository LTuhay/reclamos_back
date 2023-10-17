package com.group1.dev.app.model.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group1.dev.app.model.entity.EntityUser;

public interface UserRepository extends JpaRepository<EntityUser,Integer>{

	//Query Method
	Optional<EntityUser> findByUsername(String username);
	
}
