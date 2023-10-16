package com.group1.dev.app.model.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group1.dev.app.model.entity.User;

public interface UserRepository extends JpaRepository<User,Integer>{

	Optional<User> findByUsername(String username);
	
}
