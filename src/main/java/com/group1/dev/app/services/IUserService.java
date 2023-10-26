package com.group1.dev.app.services;

import java.util.List;
import java.util.Optional;

import com.group1.dev.app.dto.UserDTO;
import com.group1.dev.app.model.entity.EntityUser;

public interface IUserService {
	
	public List<EntityUser> findAll();
	
	public Optional<EntityUser> findById(int id);

	public void save(EntityUser user);

	public void deleteById(int id);
	
	public Optional<EntityUser> findByUsername(String username);

}
